@echo off
REM Batch script to run the Minecraft Modpack Manager
echo Starting Minecraft Modpack Manager...
echo This will open the ModpackGUI interface
echo.
java --module-path "lib\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml -cp target\classes com.example.App
echo.
echo Application closed. Data has been automatically saved.
pause