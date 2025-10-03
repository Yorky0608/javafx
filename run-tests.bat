@echo off
REM Batch script to run all tests
echo Running comprehensive test suite...
echo.

echo ================================
echo    CORE FUNCTIONALITY TESTS
echo ================================
java -cp target\test-classes com.example.ModpackTestSuite

echo.
echo ================================
echo    JAVAFX GUI TESTS
echo ================================
java --module-path "lib\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml -cp "target\classes;target\test-classes;lib\javafx-sdk-23.0.1\lib\*" com.example.ModpackGUITestApp

echo.
echo All tests completed!
pause