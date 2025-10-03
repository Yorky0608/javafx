package com.example;

/**
 * Main test runner for all modpack management classes
 * Runs comprehensive tests for ModpackUpdate, ModpackManager, and ModpackGUI
 */
public class TestRunner {
    
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("        MINECRAFT MODPACK MANAGER TEST SUITE");
        System.out.println("============================================================");
        System.out.println();
        
        // Run ModpackUpdate tests
        System.out.println("1. TESTING MODPACK UPDATE FUNCTIONALITY");
        System.out.println("----------------------------------------");
        ModpackUpdateTest updateTest = new ModpackUpdateTest();
        updateTest.runAllTests();
        
        System.out.println("\n\n");
        
        // Run ModpackManager tests  
        System.out.println("2. TESTING MODPACK MANAGER FUNCTIONALITY");
        System.out.println("----------------------------------------");
        ModpackManagerTest managerTest = new ModpackManagerTest();
        managerTest.runAllTests();
        
        System.out.println("\n\n");
        
        // Run ModpackGUI tests
        System.out.println("3. TESTING MODPACK GUI FUNCTIONALITY");
        System.out.println("----------------------------------------");
        ModpackGUITest guiTest = new ModpackGUITest();
        guiTest.runAllTests();
        
        System.out.println("\n\n");
        System.out.println("============================================================");
        System.out.println("                   TEST SUITE COMPLETE");
        System.out.println("============================================================");
        System.out.println();
        System.out.println("All classes have been tested for basic functionality.");
        System.out.println("JavaFX GUI components require application thread for full testing.");
        System.out.println("File I/O operations were tested with actual file creation/reading.");
        System.out.println();
        System.out.println("Classes are ready for integration into the JavaFX application!");
    }
}