@echo off
set WORK_PATH=%1
set WORK_VERSION=%2

if "%WORK_PATH%" == "" set WORK_PATH=%cd%\mq
if "%WORK_VERSION%" == "" set WORK_VERSION=5.8.0

if not exist %WORK_PATH% mkdir %WORK_PATH%

if not exist %WORK_PATH%\apache-activemq-%WORK_VERSION%.zip goto download

:checkunzip
if not exist %WORK_PATH%\apache-activemq-%WORK_VERSION% goto unzip
goto startmq

:download
	@echo on
	echo download...
	@echo off
	if not exist dl.vbs del dl.vbs
    echo with wscript:if .arguments.count^<2 then .quit:end if >dl.vbs
　　echo set aso=.createobject("adodb.stream"):set web=createobject("microsoft.xmlhttp") >>dl.vbs
　　echo web.open "get",.arguments(0),0:web.send:if web.status^>200 then quit >>dl.vbs
　　echo aso.type=1:aso.open:aso.write web.responsebody:aso.savetofile .arguments(1),2:end with >>dl.vbs
　　cscript dl.vbs http://192.168.1.201/apache-activemq-%WORK_VERSION%.zip %WORK_PATH%\apache-activemq-%WORK_VERSION%.zip
　　del dl.vbs
	goto checkunzip
	
:unzip
	@echo on
	echo unzip...
	@echo off
	set CP=%cd%\..\target\classes;%cd%\..\target\am-agent-0.0.1.jar;%cd%\..\lib\am-agent-0.0.1.jar;.
	java -cp %CP% org.kimmking.am.agent.zip.ZipUtils %WORK_PATH%\apache-activemq-%WORK_VERSION%.zip %WORK_PATH%\ > unzip.log
	
:startmq
	@echo on
	echo start mq...
	cd %WORK_PATH%\apache-activemq-%WORK_VERSION%\
	bin\activemq 
	




