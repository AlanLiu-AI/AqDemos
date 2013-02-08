using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Reflection;
using Excel = Microsoft.Office.Interop.Excel; 

namespace USGCExcelReader
{

    public class ExcelTable
    {
        private readonly string _name;
        private readonly string _startColumnTag;
        private readonly string _endColumnTag;
        public ExcelTable(string name, char startColumnTag, char endColumnTag)
        {
            _name = name;
            _startColumnTag = ""+startColumnTag;
            _endColumnTag = "" + endColumnTag;
        }

        public void Load(Excel.Workbook xlWorkBook)
        {
            if (xlWorkBook == null)
            {
                throw new ArgumentException("Input xlWorkBook is null!");
            }
            for (var i = 1; i <= xlWorkBook.Worksheets.Count; i++)
            {
                var xlWorkSheet = (Excel.Worksheet)xlWorkBook.Worksheets.Item[i];
                var sheetName = xlWorkSheet.Name;
                if (string.Compare(_name, sheetName, StringComparison.OrdinalIgnoreCase) != 0) continue;

                var rowIdx = 1;
                var range = xlWorkSheet.Range[_startColumnTag + rowIdx, _endColumnTag + rowIdx];
                var cells = (Array)range.Cells.Value;
                Columns = new List<string>();
                for (var colCnt = 1; colCnt <= cells.Length; colCnt++)
                {
                    var value = cells.GetValue(1, colCnt).ToString();
                    Columns.Add(value);
                }

                var rowCnt = xlWorkSheet.Rows.Count;
                Rows = new List<IList<string>>();
                for (rowIdx = 2; rowIdx <= rowCnt; rowIdx++)
                {
                    range = xlWorkSheet.Range[_startColumnTag + rowIdx, _endColumnTag + rowIdx];
                    var myvalues = (Array)range.Cells.Value2;
                    var row = new List<string>();
                    var nullCnt = 0;
                    for (var colCnt = 1; colCnt <= myvalues.Length; colCnt++)
                    {
                        var value = myvalues.GetValue(1, colCnt);
                        if (value == null) nullCnt++;
                        if (nullCnt == myvalues.Length) break;
                        row.Add(value == null ? string.Empty : value.ToString());
                    }
                    if (nullCnt == myvalues.Length)
                    {
                        //When all cells in current row are null, then we jump out the loop
                        break;
                    }
                    Rows.Add(row);
                    
                }
                return;
            }
            throw new ArgumentException("Cannot find excel sheet which name is '"+_name+"'!");
        }
        public IList<string> Columns;
        public IList<IList<String>> Rows;

        public override string ToString()
        {
            return String.Format("ExcelTable('{0}', '{1}', '{2}')[Columns: {3}, Rows: {4}]",  _name, _startColumnTag,
                _endColumnTag, Columns != null ? Columns.Count : 0, Rows != null ? Rows.Count : 0);
        }
    }

    public class MetadataTables
    {
        public ExcelTable ParamTable;
        public ExcelTable UnitsTable;
        public ExcelTable UnitsGroupTable;

        public MetadataTables()
        {
            ParamTable = new ExcelTable("NWIS to AI Map", 'A', 'J');
            UnitsTable = new ExcelTable("AI Units", 'A', 'I');
            UnitsGroupTable = new ExcelTable("AI Unit Groups", 'A', 'L');
        }

        public void Load(Excel.Workbook xlWorkBook)
        {
            ParamTable.Load(xlWorkBook);
            UnitsTable.Load(xlWorkBook);
            UnitsGroupTable.Load(xlWorkBook);
        }
    }


    class USGCExcelReaderApp
    {

        private static void ReleaseObject(object obj)
        {
            try
            {
                System.Runtime.InteropServices.Marshal.ReleaseComObject(obj);
            }
            catch (Exception ex)
            {
                Console.WriteLine("[Warn ] Unable to release the Object: " + ex);
            }
            finally
            {
                // ReSharper disable RedundantAssignment
                obj = null;
                // ReSharper restore RedundantAssignment
                GC.Collect();
            }
        }
        private static void ReadExcelToSql(string inputExcelFile, StringBuilder sb)
        {
            if (!File.Exists(inputExcelFile))
            {
                throw new ArgumentException("Input excel file '"+inputExcelFile+"' does not exist!");
            }

            object misValue = Missing.Value;
            var xlApp = new Excel.Application();
            var xlWorkBook = xlApp.Workbooks.Open(inputExcelFile, 0, true, 5, "", "", true, Excel.XlPlatform.xlWindows, "\t", false, false, 0, true, 1, 0);
            var cachedMetadataTables = new MetadataTables();
            if (xlWorkBook != null)
            {
                cachedMetadataTables.Load(xlWorkBook);
                xlWorkBook.Close(true, misValue, misValue);
                xlApp.Quit();
            }
            ReleaseObject(xlWorkBook);
            ReleaseObject(xlApp);

            Console.WriteLine("[Debug] MetadataTables is loadded.");
            Console.WriteLine("[Debug] ParamTable: {0}", cachedMetadataTables.ParamTable);
            Console.WriteLine("[Debug] UnitsTable: {0}", cachedMetadataTables.UnitsTable);
            Console.WriteLine("[Debug] UnitsGroupTable: {0}", cachedMetadataTables.UnitsGroupTable);
            ReadDataIntoSQL(cachedMetadataTables, sb);
        }

        private static void ReadDataIntoSQL(MetadataTables cachedMetadataTables, StringBuilder sb)
        {
            sb.AppendLine(@"USE $(DBName)");
            sb.AppendLine(@"DECLARE @CNT INT;");
            sb.AppendLine();
            //first go through UnitsGroup 
            ReadUnitsGroup(cachedMetadataTables.UnitsGroupTable, sb);

            //Then go through Units
            ReadUnits(cachedMetadataTables.UnitsTable, sb);

            //Last go through Parameters
            ReadParameters(cachedMetadataTables.ParamTable, sb);
        }

        private static string CastStrTo(string value)
        {
            if (string.IsNullOrEmpty(value))
                return "''";
            if (value.Contains("'"))
            {
                value = value.Replace("'", "''");
            }
            return "'" + value + "'";
        }

        private static string CastStrTo(string value, int maxLen)
        {
            if (string.IsNullOrEmpty(value))
                return "''";
            if (value.Length > maxLen)
            {
                value = value.Substring(0, maxLen - 2);
            }
            if (value.Contains("'"))
            {
                value = value.Replace("'", "''");
            }
            return "'" + value + "'";
        }

        private static T CastTo<T>(string value, T defaultValue)
        {
            if (string.IsNullOrEmpty(value))
                return defaultValue;
            object retObj = null;
            var typeCode = Type.GetTypeCode(typeof (T));
            try
            {
                switch (typeCode)
                {
                    case TypeCode.String:
                        retObj = CastStrTo(value);
                        break;
                    case TypeCode.Boolean:
                        retObj = Convert.ToBoolean(value);
                        break;
                    case TypeCode.Char:
                        retObj = Convert.ToChar(value);
                        break;
                    case TypeCode.SByte:
                        retObj = Convert.ToSByte(value);
                        break;
                    case TypeCode.Byte:
                        retObj = Convert.ToByte(value);
                        break;
                    case TypeCode.Int16:
                        retObj = Convert.ToInt16(value);
                        break;
                    case TypeCode.UInt16:
                        retObj = Convert.ToUInt16(value);
                        break;
                    case TypeCode.Int32:
                        retObj = Convert.ToInt32(value);
                        break;
                    case TypeCode.UInt32:
                        retObj = Convert.ToUInt32(value);
                        break;
                    case TypeCode.Int64:
                        retObj = Convert.ToInt64(value);
                        break;
                    case TypeCode.UInt64:
                        retObj = Convert.ToUInt64(value);
                        break;
                    case TypeCode.Single:
                        retObj = Convert.ToSingle(value);
                        break;
                    case TypeCode.Double:
                        retObj = Convert.ToDouble(value);
                        break;
                    case TypeCode.Decimal:
                        retObj = Convert.ToDecimal(value);
                        break;

                    case TypeCode.DateTime:
                        retObj = Convert.ToDateTime(value);
                        break;

                    case TypeCode.Empty:
                    case TypeCode.DBNull:
                        break;
                    default:
                        Console.WriteLine("[WARN ] Not recognize type for ConvertUtil.To<{0}>({1}, {2}).", typeCode.ToString(),
                                        value.GetType().FullName, defaultValue);
                        retObj = value;
                        break;
                }
                if (retObj != null)
                {
                    return (T) retObj;
                }
                return defaultValue;
            }
            catch (Exception ex)
            {
                Console.WriteLine("[WARN ] SafeTo<{0}>('{1}') return defautValue <{2}>, reason: {3}", typeCode, value, defaultValue, ex.Message);
                return defaultValue;
            }
            
        }

        private const string LanuguageId = "'EN'";
        private const string CurrentDateTime = "GETDATE()";

        private static void ReadUnitsGroup(ExcelTable unitsGroupTable, StringBuilder sb)
        {
            foreach (var row in unitsGroupTable.Rows)
            {
                var unitGroupId = CastStrTo(row[1], 50);
                var baseUnitId = CastStrTo(row[2], 50);
                var dimLength = CastTo(row[3], 0);
                var dimMass = CastTo(row[4], 0);
                var dimTime = CastTo(row[5], 0);
                var dimCurrent = CastTo(row[6], 0);
                var dimTemperature = CastTo(row[7], 0);
                var dimSubstance = CastTo(row[8], 0);
                var dimIntensity = CastTo(row[9], 0);
                var system = CastTo(row[10], 1);

                sb.AppendFormat(@"SELECT @CNT = COUNT(*) FROM UnitGroup WHERE UnitGroupID={0};
", unitGroupId);
                sb.AppendFormat(@"IF @CNT > 0 BEGIN
");
                sb.AppendFormat(@"  UPDATE UnitGroup SET BaseUnitId={1},DimLength={2},dimMass={3},dimTime={4},dimCurrent={5},DimTemperature={6},dimSubstance={7},dimIntensity={8},system={9},lastModified={10} WHERE UnitGroupID={0};
", unitGroupId, baseUnitId, dimLength, dimMass, dimTime, dimCurrent, dimTemperature, dimSubstance, dimIntensity, system, CurrentDateTime);

                sb.AppendFormat(@"END
ELSE BEGIN
");
                sb.AppendFormat(@"  INSERT INTO UnitGroup (UnitGroupID,BaseUnitId,DimLength,dimMass,dimTime,dimCurrent,DimTemperature,dimSubstance,dimIntensity,system,lastModified) VALUES ({0},{1},{2},{3},{4},{5},{6},{7},{8},{9},{10});
", unitGroupId, baseUnitId, dimLength, dimMass, dimTime, dimCurrent, dimTemperature, dimSubstance, dimIntensity, system, CurrentDateTime);
                sb.AppendFormat(@"END
");
                sb.AppendFormat(@"SELECT @CNT = COUNT(*) FROM UnitGroupLoc WHERE UnitGroupID={0} AND LanguageID={1}
", unitGroupId, LanuguageId);
                sb.AppendFormat(@"IF @CNT > 0 BEGIN
");
                sb.AppendFormat(@"  UPDATE UnitGroupLoc SET Name={2} WHERE UnitGroupID={0} AND LanguageID={1};
", unitGroupId, LanuguageId, unitGroupId);

                sb.AppendFormat(@"END
ELSE BEGIN
");
                sb.AppendFormat(@"  INSERT INTO UnitGroupLoc (UnitGroupID,LanguageID,Name) VALUES ({0},{1},{2});
", unitGroupId, LanuguageId, unitGroupId);
                sb.AppendFormat(@"END
");
            }
        }

        private static void ReadUnits(ExcelTable unitsTable, StringBuilder sb)
        {
            foreach (var row in unitsTable.Rows)
            {
                var unitId = CastStrTo(row[1], 50);
                var unitGroupId = CastStrTo(row[2], 50);
                var baseMultiplier = CastTo(row[3], 1.0);
                var baseOffset = CastTo(row[4], 0.0);
                var system = CastTo(row[5], 1);
                var @base = CastStrTo(row[7], 20);
                var comment = CastStrTo(row[8], 50);
                var symbol = @base.Length > 2 ? @base : unitId;
                var name = comment.Length>2 ? comment : symbol;

                sb.AppendFormat(@"SELECT @CNT = COUNT(*) FROM Unit WHERE unitId={0};
", unitId);
                sb.AppendFormat(@"IF @CNT > 0 BEGIN
");
                sb.AppendFormat(@"  UPDATE Unit SET unitGroupId={1},baseMultiplier={2},baseOffset={3},system={4},lastModified={5} WHERE unitId={0};
", unitId, unitGroupId, baseMultiplier, baseOffset, system, CurrentDateTime);

                sb.AppendFormat(@"END
ELSE BEGIN
");
                sb.AppendFormat(@"  INSERT INTO Unit (unitId,unitGroupId,baseMultiplier,baseOffset,system,lastModified) VALUES ({0},{1},{2},{3},{4},{5});
", unitId, unitGroupId, baseMultiplier, baseOffset, system, CurrentDateTime);
                sb.AppendFormat(@"END
");
                sb.AppendFormat(@"SELECT @CNT = COUNT(*) FROM UnitLoc WHERE UnitID={0} AND LanguageID={1}
", unitId, LanuguageId);
                sb.AppendFormat(@"IF @CNT > 0 BEGIN
");
                sb.AppendFormat(@"  UPDATE UnitLoc SET Symbol={2},SingularName={3},PluralName={4} WHERE UnitID={0} AND LanguageID={1};
", unitId, LanuguageId, symbol, name, name);

                sb.AppendFormat(@"END
ELSE BEGIN
");
                sb.AppendFormat(@"  INSERT INTO UnitLoc (UnitId,LanguageID,Symbol,SingularName,PluralName) VALUES ({0},{1},{2},{3},{4});
", unitId, LanuguageId, symbol, name, name);
                sb.AppendFormat(@"END
");
            }
        }

        private static void ReadParameters(ExcelTable paramTable, StringBuilder sb)
        {
            var idListDict = new Dictionary<string, string>();
            foreach (var row in paramTable.Rows)
            {
                var parameterId = row[0];
                var usgsId = row[4];
                if (idListDict.ContainsKey(parameterId))
                {
                    idListDict[parameterId] = idListDict[parameterId] + "," + usgsId;
                }
                else
                {
                    idListDict.Add(parameterId, usgsId);
                }
            }
            var didList = new List<string>();
            foreach (var row in paramTable.Rows)
            {
                var parameterId = CastStrTo(row[0], 50);
                if (didList.Contains(parameterId))
                {
                    //We choose the first parameterId if there are duplication in Excel Sheet
                    continue;
                }
                var displayId = CastStrTo(row[1], 256);
                var unitGroupId = CastStrTo(row[2], 50);
                var defaultUnitId = CastStrTo(row[3], 50);
                const int defaultInterpolationTypeId = 7;
                var usgsIds = idListDict.ContainsKey(row[0]) ? idListDict[row[0]] : row[0];
                var name = CastStrTo(usgsIds + " - " + row[9], 256);
                const int system = 1;

                sb.AppendFormat(@"SELECT @CNT = COUNT(*) FROM Parameter WHERE ParameterId={0};
", parameterId);
                sb.AppendFormat(@"IF @CNT > 0 BEGIN
");
                sb.AppendFormat(@"  UPDATE Parameter SET DisplayId={1},UnitGroupId={2},DefaultUnitId={3},DefaultInterpolationTypeId={4},System={5},lastModified={6} WHERE ParameterId={0};
", parameterId, displayId, unitGroupId, defaultUnitId, defaultInterpolationTypeId, system, CurrentDateTime);

                sb.AppendFormat(@"END
ELSE BEGIN
");
                sb.AppendFormat(@"  INSERT INTO Parameter (ParameterId,DisplayId,UnitGroupId,DefaultUnitId,DefaultInterpolationTypeId,System,lastModified) VALUES ({0},{1},{2},{3},{4},{5},{6});
", parameterId, displayId, unitGroupId, defaultUnitId, defaultInterpolationTypeId, system, CurrentDateTime);
                sb.AppendFormat(@"END
");
                sb.AppendFormat(@"SELECT @CNT = COUNT(*) FROM ParameterLoc WHERE ParameterId={0} AND LanguageID={1}
", parameterId, LanuguageId);
                sb.AppendFormat(@"IF @CNT > 0 BEGIN
");
                sb.AppendFormat(@"  UPDATE ParameterLoc SET Name={2} WHERE ParameterId={0} AND LanguageID={1};
", parameterId, LanuguageId, name);

                sb.AppendFormat(@"END
ELSE BEGIN
");
                sb.AppendFormat(@"  INSERT INTO ParameterLoc (ParameterId,LanguageID,Name) VALUES ({0},{1},{2});
", parameterId, LanuguageId, name);
                sb.AppendFormat(@"END
");
                didList.Add(parameterId);
            }
        }

        static void Main()
        {
            try
            {
                const string inputFile = @"AiToNwisParms V4_2013-02-05.xlsx";
                const string targetSqlFile = @"AiToNwisParms.sql";
                Console.WriteLine("[Debug] Current directory is: {0}", Environment.CurrentDirectory);
                var input = Environment.CurrentDirectory + @"\..\..\" + inputFile;
                var output = Environment.CurrentDirectory + @"\..\..\" + targetSqlFile;
                var sb = new StringBuilder();
                ReadExcelToSql(input, sb);
                File.WriteAllText(output, sb.ToString(), Encoding.UTF8);
                Console.WriteLine("[Info ] Succeeded.");
            }
            catch (Exception ex)
            {
                Console.WriteLine("[Error] " + ex);
            }
            Console.WriteLine("[Info ] Enter to quit...");
            Console.ReadLine();
        }
    }
}
