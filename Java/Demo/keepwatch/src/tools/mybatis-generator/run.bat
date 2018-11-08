@echo off 
color 2

java -jar mybatis-generator-core-1.3.7.jar -configfile generator.xml -overwrite

echo Automatically shut down after 5 seconds...
choice /T 5 /C ync /CS /D y /n