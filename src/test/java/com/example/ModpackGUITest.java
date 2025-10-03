package com.example;

/**
 * Test class for ModpackGUI functionality
 * Tests basic GUI component initialization and data management
 */
public class ModpackGUITest {
    
    private ModpackGUI gui;
    private int testsPassed = 0;
    private int testsTotal = 0;
    
    public ModpackGUITest() {
        this.gui = new ModpackGUI();
    }
    
    public void runAllTests() {
        System.out.println("Running ModpackGUI Tests...\n");
        
        testInitialState();
        testInheritedFunctionality();
        testModpackOperations();
        testModOperations();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ModpackGUI Tests Summary:");
        System.out.println("Tests passed: " + testsPassed + "/" + testsTotal);
        System.out.println("Success rate: " + (testsPassed * 100 / testsTotal) + "%");
        System.out.println("=".repeat(50));
    }
    
    private void testInitialState() {
        System.out.println("Testing GUI initial state...");
        testsTotal++;
        
        try {
            gui = new ModpackGUI();
            
            // Test inherited functionality from ModpackManager
            if (gui.getModpacks().isEmpty()) {
                System.out.println("✓ GUI initial state test passed");
                testsPassed++;
            } else {
                System.out.println("✗ GUI initial state test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ GUI initial state test failed with exception: " + e.getMessage());
        }
    }
    
    private void testInheritedFunctionality() {
        System.out.println("Testing inherited ModpackManager functionality...");
        testsTotal++;
        
        try {
            gui = new ModpackGUI();
            
            // Test that GUI can access ModpackManager methods
            gui.addModpack("TestPack");
            gui.addModToModpack("TestPack", "OptiFine");
            
            if (gui.getModpacks().contains("TestPack") && 
                gui.getModpackMods("TestPack").contains("OptiFine")) {
                System.out.println("✓ Inherited functionality test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Inherited functionality test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Inherited functionality test failed with exception: " + e.getMessage());
        }
    }
    
    private void testModpackOperations() {
        System.out.println("Testing modpack operations through GUI...");
        testsTotal++;
        
        try {
            gui = new ModpackGUI();
            
            // Add multiple modpacks
            gui.addModpack("Pack1");
            gui.addModpack("Pack2");
            gui.addModpack("Pack3");
            
            // Test removal
            gui.removeModpack("Pack2");
            
            if (gui.getModpacks().size() == 2 && 
                gui.getModpacks().contains("Pack1") && 
                gui.getModpacks().contains("Pack3") && 
                !gui.getModpacks().contains("Pack2")) {
                System.out.println("✓ Modpack operations test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Modpack operations test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Modpack operations test failed with exception: " + e.getMessage());
        }
    }
    
    private void testModOperations() {
        System.out.println("Testing mod operations through GUI...");
        testsTotal++;
        
        try {
            gui = new ModpackGUI();
            
            // Create a modpack and add mods
            gui.addModpack("TestPack");
            gui.addModToModpack("TestPack", "OptiFine");
            gui.addModToModpack("TestPack", "JEI");
            gui.addModToModpack("TestPack", "Biomes O' Plenty");
            
            // Remove a mod
            gui.removeModFromModpack("TestPack", "JEI");
            
            // Check change tracking (inherited from ModpackUpdate)
            boolean hasChanges = gui.hasChanges();
            
            if (gui.getModpackMods("TestPack").size() == 2 && 
                gui.getModpackMods("TestPack").contains("OptiFine") && 
                gui.getModpackMods("TestPack").contains("Biomes O' Plenty") && 
                !gui.getModpackMods("TestPack").contains("JEI") && 
                hasChanges) {
                System.out.println("✓ Mod operations test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Mod operations test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Mod operations test failed with exception: " + e.getMessage());
        }
    }
    
    // Note: GUI-specific methods like createGUI(), showMainScreen(), etc. 
    // require JavaFX Application Thread and Stage, so they cannot be easily 
    // tested in a unit test environment without setting up JavaFX toolkit.
    // In a real application, these would be tested with JavaFX testing frameworks
    // like TestFX.
    
    public static void main(String[] args) {
        System.out.println("Note: This test only covers the data management aspects of ModpackGUI.");
        System.out.println("GUI components would require JavaFX Application Thread for testing.\n");
        
        ModpackGUITest test = new ModpackGUITest();
        test.runAllTests();
    }
}