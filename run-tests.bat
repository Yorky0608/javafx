@echo off
echo Compiling and running tests...

REM Clean up any existing test class files
if exist "target\test-classes" rmdir /s /q "target\test-classes"
mkdir "target\test-classes" 2>nul

REM Compile the test class with proper classpath
javac -cp "target\classes" -d "target\test-classes" "src\test\java\com\example\SimpleTestRunner.java"

if %ERRORLEVEL% NEQ 0 (
    echo Test compilation failed!
    pause
    exit /b 1
)

echo.
echo Running tests...
echo.

REM Run the tests
java -cp "target\classes;target\test-classes" com.example.SimpleTestRunner

echo.
pause