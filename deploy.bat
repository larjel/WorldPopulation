@echo off
call mvn clean package
if not "%ERRORLEVEL%" == "0" exit /b
copy /Y target\worldpopulation.war %WILDFLY_HOME%\standalone\deployments
