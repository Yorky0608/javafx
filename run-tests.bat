@echo off
echo Compiling and running tests...

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