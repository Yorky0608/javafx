package com.example;

import java.util.Arrays;
import java.util.List;

/**
 * Comprehen        System.out.println("\nTESTING MODPACK MANAGER FUNCTIONALITY");
        System.out.println("------------------------------------------");ve test suite for Modpack management system
 * Tests all core functionality without JavaFX dependencies
 */
public class ModpackTestSuite {
    
    private int totalTests = 0;
    private int passedTests = 0;
    
    public static void main(String[] args) {
        ModpackTestSuite suite = new ModpackTestSuite();
        suite.runAllTests();
    }
    
    public void runAllTests() {
        System.out.println("============================================================");
        System.out.println("    MINECRAFT MODPACK MANAGER TEST SUITE");
        System.out.println("============================================================");
        System.out.println();
        
        testModpackUpdate();
        testModpackManager();
        
        System.out.println("\n============================================================");
        System.out.println("              FINAL TEST RESULTS");
        System.out.println("============================================================");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (totalTests - passedTests));
        System.out.println("Success Rate: " + (passedTests * 100 / totalTests) + "%");
        System.out.println("============================================================");
    }
    
    private void testModpackUpdate() {
        System.out.println("TESTING MODPACK UPDATE FUNCTIONALITY");
        System.out.println("----------------------------------------");
        
        ModpackUpdateSimple update = new ModpackUpdateSimple();
        
        // Test 1: Initial state
        test("Initial state", 
             update.getAddedMods().isEmpty() && 
             update.getRemovedMods().isEmpty() && 
             !update.hasChanges());
        
        // Test 2: Add mod
        update.addMod("OptiFine");
        test("Add mod", 
             update.getAddedMods().size() == 1 && 
             update.getAddedMods().contains("OptiFine") && 
             update.hasChanges());
        
        // Test 3: Remove mod
        update = new ModpackUpdateSimple();
        update.removeMod("JEI");
        test("Remove mod", 
             update.getRemovedMods().size() == 1 && 
             update.getRemovedMods().contains("JEI") && 
             update.hasChanges());
        
        // Test 4: Add then remove same mod
        update = new ModpackUpdateSimple();
        update.addMod("OptiFine");
        update.removeMod("OptiFine");
        test("Add then remove same mod", 
             !update.getAddedMods().contains("OptiFine") && 
             update.getRemovedMods().contains("OptiFine"));
        
        // Test 5: Clear changes
        update.clearChanges();
        test("Clear changes", 
             update.getAddedMods().isEmpty() && 
             update.getRemovedMods().isEmpty() && 
             !update.hasChanges());
        
        // Test 6: Null/empty handling
        update = new ModpackUpdateSimple();
        update.addMod(null);
        update.addMod("");
        update.addMod("   ");
        test("Null/empty mod handling", 
             update.getAddedMods().isEmpty() && 
             !update.hasChanges());
        
        System.out.println();
    }
    
    private void testModpackManager() {
        System.out.println("TESTING MODPACK MANAGER FUNCTIONALITY");
        System.out.println("----------------------------------------");
        
        ModpackManagerSimple manager = new ModpackManagerSimple();
        
        // Test 1: Initial state
        test("Manager initial state", manager.getModpacks().isEmpty());
        
        // Test 2: Add modpack
        manager.addModpack("TestPack");
        test("Add modpack", 
             manager.getModpacks().size() == 1 && 
             manager.getModpacks().contains("TestPack"));
        
        // Test 3: Add mods to modpack
        manager.addModToModpack("TestPack", "OptiFine");
        manager.addModToModpack("TestPack", "JEI");
        List<String> mods = manager.getModpackMods("TestPack");
        test("Add mods to modpack", 
             mods.size() == 2 && 
             mods.contains("OptiFine") && 
             mods.contains("JEI"));
        
        // Test 4: Remove mod from modpack
        manager.removeModFromModpack("TestPack", "OptiFine");
        mods = manager.getModpackMods("TestPack");
        test("Remove mod from modpack", 
             mods.size() == 1 && 
             !mods.contains("OptiFine") && 
             mods.contains("JEI"));
        
        // Test 5: Multiple modpacks
        manager.addModpack("Pack2");
        manager.addModpack("Pack3");
        test("Multiple modpacks", manager.getModpacks().size() == 3);
        
        // Test 6: Remove modpack
        manager.removeModpack("Pack2");
        test("Remove modpack", 
             manager.getModpacks().size() == 2 && 
             !manager.getModpacks().contains("Pack2"));
        
        // Test 7: Set modpacks
        List<String> newPacks = Arrays.asList("NewPack1", "NewPack2");
        manager.setModpacks(newPacks);
        test("Set modpacks", 
             manager.getModpacks().size() == 2 && 
             manager.getModpacks().containsAll(newPacks));
        
        // Test 8: Set modpack mods
        manager.addModpack("TestSetMods");
        List<String> newMods = Arrays.asList("Mod1", "Mod2", "Mod3");
        manager.setModpackMods("TestSetMods", newMods);
        test("Set modpack mods", 
             manager.getModpackMods("TestSetMods").size() == 3 && 
             manager.getModpackMods("TestSetMods").containsAll(newMods));
        
        // Test 9: Summary generation
        String summary = manager.getSummary();
        test("Summary generation", 
             summary.contains("Total modpacks:") && 
             summary.contains("TestSetMods: 3 mods"));
        
        // Test 10: Change tracking inheritance
        manager = new ModpackManagerSimple();
        manager.addModpack("TrackPack");
        manager.addModToModpack("TrackPack", "TrackedMod");
        test("Change tracking inheritance", 
             manager.hasChanges() && 
             manager.getAddedMods().contains("TrackedMod"));
        
        System.out.println();
    }
    
    private void test(String testName, boolean condition) {
        totalTests++;
        if (condition) {
            passedTests++;
            System.out.println("✓ " + testName + " - PASSED");
        } else {
            System.out.println("✗ " + testName + " - FAILED");
        }
    }
}