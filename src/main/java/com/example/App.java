package com.example;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App - Minecraft Modpack Manager
 * Main application entry point that launches the ModpackGUI
 */
public class App extends Application {

    private ModpackGUI modpackGUI;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the ModpackGUI instance
        modpackGUI = new ModpackGUI();
        
        // Set up the stage
        primaryStage.setTitle("Minecraft Modpack Manager");
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        
        // Create and show the GUI
        modpackGUI.createGUI(primaryStage);
        modpackGUI.showMainScreen();
        
        // Show the stage
        primaryStage.show();
        
        // Handle application close
        primaryStage.setOnCloseRequest(event -> {
            // Save any pending changes before closing
            try {
                modpackGUI.saveToJson();
                System.out.println("Modpack data saved successfully.");
            } catch (Exception e) {
                System.err.println("Error saving modpack data: " + e.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}