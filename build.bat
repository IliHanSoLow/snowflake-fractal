@echo off
REM Compile the Java files
javac -cp "libs/*;libs/commons-math3-3.6.1/*" -d build src/Main.java
REM Run the compiled Java program
java -cp "libs/*;libs/commons-math3-3.6.1/*;build" main.Main
