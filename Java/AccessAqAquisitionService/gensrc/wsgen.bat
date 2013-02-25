@rem To run this batch code generation file, you need setup metro 2.2 to relative path "..\..\..\install\metro", it can be download from http://metro.java.net/2.2/

IF DEFINED JAVA_HOME (
	ECHO JAVA_HOME: %JAVA_HOME%
) else (
	set JAVA_HOME=D:\app\Java\jdk1.7.0_09
	set PATH="%JAVA_HOME%\bin;%PATH%"
)

..\..\..\install\metro\bin\wsimport.bat -extension -keep -Xnocompile -b AqAcquisitionService.xml http://10.10.222.100/AQUARIUS/AqAcquisitionService.svc?wsdl 


@pause

