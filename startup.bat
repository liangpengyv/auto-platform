@echo off
:start
set port=""
set /p port=Please enter the boot port (The default is 8080) :

if not %port%=="" goto custom_port

java -jar auto-platform-0.0.1-SNAPSHOT.jar

:error_port
echo Error: Port number is out of range!
goto start

:custom_port
::front<back
if %port% lss 0 goto error_port
::front>back
if %port% gtr 65535 goto error_port
java -jar auto-platform-0.0.1-SNAPSHOT.jar --server.port=%port%

:end
