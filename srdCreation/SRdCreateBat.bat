@ECHO OFF
set dir=%1
cd %dir%
d:
java -jar %dir%"/SRDCreation.jar" %dir% %2