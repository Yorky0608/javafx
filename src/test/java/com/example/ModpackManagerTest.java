package com.example;

import java.util.Arrays;
import java.util.List;

/**
 * Test class for ModpackManager functionality
 * Tests modpack and mod management operations
 */
public class ModpackManagerTest {
    
    private ModpackManager manager;
    private int testsPassed = 0;
    private int testsTotal = 0;
    
    public ModpackManagerTest() {
        this.manager = new ModpackManager();
    }
    
    public void runAllTests() {
        System.out.println("Running ModpackManager Tests...\n");
        
        testInitialState();
        testAddModpack();
        testRemoveModpack();
        testSetModpacks();
        testSetModpackMods();
        testAddModToModpack();
        testRemoveModFromModpack();
        testGetModpackMods();
        testSaveAndLoad();
        testGetSummary();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ModpackManager Tests Summary:");
        System.out.println("Tests passed: " + testsPassed + "/" + testsTotal);
        System.out.println("Success rate: " + (testsPassed * 100 / testsTotal) + "%");
        System.out.println("=".repeat(50));
    }
    
    private void testInitialState() {
        System.out.println("Testing initial state...");
        testsTotal++;
        
        try {
            manager = new ModpackManager();
            
            if (manager.getModpacks().isEmpty()) {
                System.out.println("✓ Initial state test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Initial state test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Initial state test failed with exception: " + e.getMessage());
        }
    }
    
    private void testAddModpack() {
        System.out.println("Testing add modpack...");
        testsTotal++;
        
        try {
            manager = new ModpackManager();
            manager.addModpack("TestPack");
            
            List<String> modpacks = manager.getModpacks();
            if (modpacks.size() == 1 && modpacks.contains("TestPack")) {
                System.out.println("✓ Add modpack test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Add modpack test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Add modpack test failed with exception: " + e.getMessage());
        }
    }
    
    private void testRemoveModpack() {
        System.out.println("Testing remove modpack...");
        testsTotal++;
        
        try {
            manager = new ModpackManager();
            manager.addModpack("TestPack");
            manager.addModpack("TestPack2");
            manager.removeModpack("TestPack");
            
            List<String> modpacks = manager.getModpacks();
            if (modpacks.size() == 1 && !modpacks.contains("TestPack") && modpacks.contains("TestPack2")) {
                System.out.println("✓ Remove modpack test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Remove modpack test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Remove modpack test failed with exception: " + e.getMessage());
        }
    }
    
    private void testSetModpacks() {
        System.out.println("Testing set modpacks...");
        testsTotal++;
        
        try {
            manager = new ModpackManager();
            List<String> newModpacks = Arrays.asList("Pack1", "Pack2", "Pack3");
            manager.setModpacks(newModpacks);
            
            List<String> modpacks = manager.getModpacks();
            if (modpacks.size() == 3 && modpacks.containsAll(newModpacks)) {
                System.out.println("✓ Set modpacks test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Set modpacks test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Set modpacks test failed with exception: " + e.getMessage());
        }
    }
    
    private void testSetModpackMods() {
        System.out.println("Testing set modpack mods...");
        testsTotal++;
        
        try {
            manager = new ModpackManager();
            manager.addModpack("TestPack");
            List<String> mods = Arrays.asList("OptiFine", "JEI", "Biomes O' Plenty");
            manager.setModpackMods("TestPack", mods);
            
            List<String> retrievedMods = manager.getModpackMods("TestPack");
            if (retrievedMods.size() == 3 && retrievedMods.containsAll(mods)) {
                System.out.println("✓ Set modpack mods test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Set modpack mods test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Set modpack mods test failed with exception: " + e.getMessage());
        }
    }
    
    private void testAddModToModpack() {
        System.out.println("Testing add mod to modpack...");
        testsTotal++;
        
        try {
            manager = new ModpackManager();
            manager.addModpack("TestPack");
            manager.addModToModpack("TestPack", "OptiFine");
            manager.addModToModpack("TestPack", "JEI");
            
            List<String> mods = manager.getModpackMods("TestPack");
            if (mods.size() == 2 && mods.contains("OptiFine") && mods.contains("JEI")) {
                System.out.println("✓ Add mod to modpack test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Add mod to modpack test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Add mod to modpack test failed with exception: " + e.getMessage());
        }
    }
    
    private void testRemoveModFromModpack() {
        System.out.println("Testing remove mod from modpack...");
        testsTotal++;
        
        try {
            manager = new ModpackManager();
            manager.addModpack("TestPack");
            manager.addModToModpack("TestPack", "OptiFine");
            manager.addModToModpack("TestPack", "JEI");
            manager.removeModFromModpack("TestPack", "OptiFine");
            
            List<String> mods = manager.getModpackMods("TestPack");
            if (mods.size() == 1 && !mods.contains("OptiFine") && mods.contains("JEI")) {
                System.out.println("✓ Remove mod from modpack test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Remove mod from modpack test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Remove mod from modpack test failed with exception: " + e.getMessage());
        }
    }
    
    private void testGetModpackMods() {
        System.out.println("Testing get modpack mods for non-existent modpack...");
        testsTotal++;
        
        try {
            manager = new ModpackManager();
            List<String> mods = manager.getModpackMods("NonExistentPack");
            
            if (mods.isEmpty()) {
                System.out.println("✓ Get modpack mods test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Get modpack mods test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Get modpack mods test failed with exception: " + e.getMessage());
        }
    }
    
    private void testSaveAndLoad() {
        System.out.println("Testing save and load functionality...");
        testsTotal++;
        
        try {
            manager = new ModpackManager();
            manager.addModpack("TestPack");
            manager.addModToModpack("TestPack", "OptiFine");
            manager.addModToModpack("TestPack", "JEI");
            
            // Save data
            manager.saveToJson();
            
            // Create new manager and load data
            ModpackManager manager2 = new ModpackManager();
            manager2.loadFromJson();
            
            List<String> modpacks = manager2.getModpacks();
            List<String> mods = manager2.getModpackMods("TestPack");
            
            if (modpacks.contains("TestPack") && 
                mods.contains("OptiFine") && 
                mods.contains("JEI")) {
                System.out.println("✓ Save and load test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Save and load test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Save and load test failed with exception: " + e.getMessage());
        }
    }
    
    private void testGetSummary() {
        System.out.println("Testing get summary...");
        testsTotal++;
        
        try {
            manager = new ModpackManager();
            manager.addModpack("TestPack1");
            manager.addModpack("TestPack2");
            manager.addModToModpack("TestPack1", "OptiFine");
            manager.addModToModpack("TestPack1", "JEI");
            manager.addModToModpack("TestPack2", "Biomes O' Plenty");
            
            String summary = manager.getSummary();
            
            if (summary.contains("Total modpacks: 2") && 
                summary.contains("TestPack1: 2 mods") && 
                summary.contains("TestPack2: 1 mods")) {
                System.out.println("✓ Get summary test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Get summary test failed");
                System.out.println("Summary: " + summary);
            }
        } catch (Exception e) {
            System.out.println("✗ Get summary test failed with exception: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        ModpackManagerTest test = new ModpackManagerTest();
        test.runAllTests();
    }
}