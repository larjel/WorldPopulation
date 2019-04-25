@echo off
rem Skip tests since they will fail, because of the shutdown of the World Population API
call mvn clean package -Dmaven.test.skip=true
if not "%ERRORLEVEL%" == "0" exit /b
copy /Y target\worldpopulation.war %WILDFLY_HOME%\standalone\deployments
