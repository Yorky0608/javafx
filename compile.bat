@echo off
REM Batch script to compile the entire project
echo Compiling Modpack Manager project...

echo Compiling App class...
javac -d target\classes --module-path "lib\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml -cp "lib\javafx-sdk-23.0.1\lib\*" src\main\java\com\example\App.java

echo Compiling Modpack classes...
javac -d target\classes --module-path "lib\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml -cp "lib\javafx-sdk-23.0.1\lib\*;target\classes" src\main\java\com\example\Modpack*.java

echo Compiling module-info...
javac -d target\classes --module-path "lib\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml -cp "lib\javafx-sdk-23.0.1\lib\*;target\classes" src\main\java\module-info.java

echo Copying FXML resources...
xcopy "src\main\resources\com\example\*.fxml" "target\classes\com\example\" /Y

REM Tests are compiled separately using run-tests.bat

echo.
echo Compilation complete!
echo Use run-gui.bat to start the application
echo Use run-tests.bat to run all tests
pause