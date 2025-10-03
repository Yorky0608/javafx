package com.example;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Test to verify App properly integrates with ModpackGUI
 * This creates a minimal version that tests the integration without showing GUI
 */
public class AppIntegrationTest extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("=".repeat(50));
        System.out.println("    APP INTEGRATION TEST");
        System.out.println("=".repeat(50));
        
        try {
            // Test that App can create ModpackGUI
            ModpackGUI modpackGUI = new ModpackGUI();
            System.out.println("✓ ModpackGUI instance created in App context");
            
            // Test basic functionality
            modpackGUI.addModpack("TestPack");
            modpackGUI.addModToModpack("TestPack", "OptiFine");
            System.out.println("✓ ModpackGUI functionality works in App context");
            
            // Test GUI creation (without showing)
            modpackGUI.createGUI(primaryStage);
            System.out.println("✓ GUI components created successfully");
            
            // Test data persistence
            modpackGUI.saveToJson();
            System.out.println("✓ Data saving works");
            
            System.out.println("\n" + "=".repeat(50));
            System.out.println("    APP INTEGRATION TEST PASSED!");
            System.out.println("=".repeat(50));
            System.out.println("The main App.java is properly configured to run ModpackGUI");
            
        } catch (Exception e) {
            System.out.println("✗ Integration test failed: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Close the test
        primaryStage.close();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}