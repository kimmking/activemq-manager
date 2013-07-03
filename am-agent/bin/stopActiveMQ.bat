@echo off
set WORK_PATH=%1
set WORK_VERSION=%2

if "%WORK_PATH%" == "" set WORK_PATH=%cd%\mq
if "%WORK_VERSION%" == "" set WORK_VERSION=5.8.0

if not exist %WORK_PATH% goto NO_WORK_PATH
if not exist %WORK_PATH%\apache-activemq-%WORK_VERSION%\ goto NO_WORK_PATH2
rem stopmq
	@echo on
	echo stop mq...
	cd %WORK_PATH%\apache-activemq-%WORK_VERSION%\
	bin\activemq-admin.bat stop
	@echo off
	goto N
	
:NO_WORK_PATH
	echo "%WORK_PATH% does not exist"
	goto N
:NO_WORK_PATH2
	echo "%WORK_PATH%\apache-activemq-%WORK_VERSION%\ does not exist"
	goto N
		
:N
		 
	