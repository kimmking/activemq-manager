set WORK_PATH=%1
set WORK_VERSION=%2

if "%WINRAR%" == "" set WINRAR="C:\Program Files\WinRAR\winrar.exe"

if not exist %WINRAR% goto er_winrar

if "%WORK_PATH%" == "" set WORK_PATH=%cd%\mq
if "%WORK_VERSION%" == "" set WORK_VERSION=5.8.0
if not exist %WORK_PATH% mkdir %WORK_PATH%
	
	echo with wscript:if .arguments.count^<2 then .quit:end if >dl.vbs
　　echo set aso=.createobject("adodb.stream"):set web=createobject("microsoft.xmlhttp") >>dl.vbs
　　echo web.open "get",.arguments(0),0:web.send:if web.status^>200 then quit >>dl.vbs
　　echo aso.type=1:aso.open:aso.write web.responsebody:aso.savetofile .arguments(1),2:end with >>dl.vbs
　　cscript dl.vbs http://192.168.1.201/apache-activemq-%WORK_VERSION%.zip %WORK_PATH%\apache-activemq-%WORK_VERSION%.zip
　　del dl.vbs

	"%WINRAR%"  e  -ibck -o+ -Y %WORK_PATH%\apache-activemq-%WORK_VERSION%.zip %WORK_PATH%\apache-activemq-%WORK_VERSION%\
	
exit

:er_winrar
echo "no WINRAR set"


