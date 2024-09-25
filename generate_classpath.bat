@echo off
setlocal enabledelayedexpansion

REM Define the directory where your JAR files are located
set JAR_DIR=lib

REM Start the .classpath file
echo ^<?xml version="1.0" encoding="UTF-8"^?^> > .classpath
echo ^<classpath^> >> .classpath
echo ^    ^<classpathentry kind="src" path="src"/^> >> .classpath

REM Iterate through each JAR file in the specified directory and add to .classpath
for %%f in (%JAR_DIR%\*.jar) do (
    echo ^    ^<classpathentry kind="libs" path="%%f"/^> >> .classpath
)

REM Add the JRE container and output folder
echo ^    ^<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER"/^> >> .classpath
echo ^    ^<classpathentry kind="output" path="bin"/^> >> .classpath
echo ^</classpath^> >> .classpath

echo .classpath file generated successfully.
