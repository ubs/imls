@ECHO OFF

ECHO Attempting to Start JADE Platform for IMLS


IF NOT DEFINED IMLS_HOME SET IMLS_HOME=%CD%

SET WEB_INF_LIB_PATH=%IMLS_HOME%\WebContent\WEB-INF\lib

SET JADE_JAR=%WEB_INF_LIB_PATH%\jade.jar

ECHO .
ECHO IMLS_HOME IS: %IMLS_HOME%

ECHO .
ECHO WEB_INF_LIB_PATH IS: %WEB_INF_LIB_PATH%

ECHO .
ECHO JADE_JAR IS: %JADE_JAR%


java -cp %JADE_JAR%;%WEB_INF_LIB_PATH% jade.Boot -gui


ECHO .

PAUSE
