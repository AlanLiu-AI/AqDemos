@echo off

set DBName=aquarius
set ServerName=localhost
set /p DBName=Enter the name of the database (aquarius):
set /p ServerName=Enter the name of server running SQL Server (localhost):

if exist AiToNwisParms_err.txt del AiToNwisParms_err.txt

ECHO.

sqlcmd -S %ServerName% -X1 -v ThisDir="%CD%" DBName="%DBName%" InstallLog=AiToNwisParms_err.txt -i AiToNwisParms.sql
IF ERRORLEVEL 1 (
  ECHO ERROR: Updating of the Aquarius Database failed.
  GOTO ERREXIT
)


ECHO Updating of the Aquarius Database is complete.
GOTO:EOF

:ERREXIT
ECHO.
TYPE update_err.txt
ECHO.
ECHO UPDATE FAILED.
ECHO The database should not be used until the problem is fixed.
ECHO.

:EOF