@echo off

set OH_HOME=%~dps0
for %%i in (java.exe) do set JAVA=%%~s$PATH:i

IF NOT DEFINED JAVA (
	@echo Java not found
	EXIT /B
) ELSE (
	@Echo Java found at %JAVA%
)

cd /d %OH_HOME%\

REM Java.exe already in path at this moment
set JAVA=java.exe

@echo on
start /min %JAVA% -jar OH-webapi-2.0.1.jar --spring.config.location=rsc/application.properties