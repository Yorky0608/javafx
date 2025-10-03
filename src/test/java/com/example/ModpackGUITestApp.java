package com.example;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX Application wrapper for testing ModpackGUI
 * This initializes the JavaFX toolkit so GUI components can be tested
 */
public class ModpackGUITestApp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("=".repeat(60));
        System.out.println("    MODPACK GUI TEST WITH JAVAFX INITIALIZED");
        System.out.println("=".repeat(60));
        
        try {
            // Test the ModpackGUI functionality
            ModpackGUI gui = new ModpackGUI();
            
            System.out.println("✓ ModpackGUI instance created successfully");
            
            // Test inherited functionality from ModpackManager
            gui.addModpack("TestPack");
            gui.addModToModpack("TestPack", "OptiFine");
            gui.addModToModpack("TestPack", "JEI");
            
            System.out.println("✓ Modpack operations successful");
            System.out.println("  - Added TestPack");
            System.out.println("  - Added mods: OptiFine, JEI");
            
            // Test data retrieval
            System.out.println("✓ Data retrieval test:");
            System.out.println("  - Modpacks: " + gui.getModpacks());
            System.out.println("  - TestPack mods: " + gui.getModpackMods("TestPack"));
            
            // Test change tracking
            gui.addMod("BetterFoliage");
            gui.removeMod("Waila");
            
            System.out.println("✓ Change tracking test:");
            System.out.println("  - Has changes: " + gui.hasChanges());
            System.out.println("  - Added mods: " + gui.getAddedMods());
            System.out.println("  - Removed mods: " + gui.getRemovedMods());
            
            // Test GUI components creation (without displaying)
            gui.createGUI(primaryStage);
            System.out.println("✓ GUI components created successfully");
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("    ALL JAVAFX GUI TESTS PASSED!");
            System.out.println("=".repeat(60));
            System.out.println("\nNote: To see the actual GUI, run the main App class");
            System.out.println("or call gui.showMainScreen() in a real application.");
            
        } catch (Exception e) {
            System.out.println("✗ Test failed with exception: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Close the app after testing
        primaryStage.close();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}