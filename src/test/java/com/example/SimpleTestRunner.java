package com.example;

/**
 * Simple and clean test runner for the Minecraft Modpack Manager
 * Tests all core functionality without dependencies on GUI components
 */
public class SimpleTestRunner {
    
    private int totalTests = 0;
    private int passedTests = 0;
    
    public static void main(String[] args) {
        SimpleTestRunner runner = new SimpleTestRunner();
        runner.runAllTests();
    }
    
    public void runAllTests() {
        System.out.println("============================================================");
        System.out.println("        MINECRAFT MODPACK MANAGER - TEST SUITE");
        System.out.println("============================================================\n");
        
        testModpackUpdate();
        testModpackManager();
        testIntegrationScenarios();
        
        displayResults();
    }
    
    /**
     * Test ModpackUpdate base class functionality
     */
    private void testModpackUpdate() {
        section("MODPACK UPDATE TESTS");
        
        ModpackUpdate update = new ModpackUpdate();
        
        // Test initial state
        test("Initial empty state", 
             update.getAddedMods().isEmpty() && 
             update.getRemovedMods().isEmpty() && 
             !update.hasChanges());
        
        // Test adding mods
        update.addMod("OptiFine");
        update.addMod("JEI");
        test("Add mods functionality", 
             update.getAddedMods().size() == 2 && 
             update.getAddedMods().contains("OptiFine") && 
             update.hasChanges());
        
        // Test removing mods
        update.removeMod("OptiFine");
        test("Remove mods functionality", 
             update.getRemovedMods().contains("OptiFine") && 
             update.getAddedMods().contains("JEI"));
        
        // Test clearing changes
        update.clearChanges();
        test("Clear changes functionality", 
             !update.hasChanges() && 
             update.getAddedMods().isEmpty() && 
             update.getRemovedMods().isEmpty());
    }
    
    /**
     * Test ModpackManager functionality
     */
    private void testModpackManager() {
        section("MODPACK MANAGER TESTS");
        
        ModpackManager manager = new ModpackManager();
        
        // Test modpack creation
        manager.addModpack("TestPack1");
        manager.addModpack("TestPack2");
        test("Create modpacks", 
             manager.getModpacks().size() == 2 && 
             manager.getModpacks().contains("TestPack1"));
        
        // Test adding mods to modpacks
        manager.addModToModpack("TestPack1", "OptiFine");
        manager.addModToModpack("TestPack1", "JEI");
        manager.addModToModpack("TestPack2", "Tinkers Construct");
        
        test("Add mods to modpacks", 
             manager.getModpackMods("TestPack1").size() == 2 && 
             manager.getModpackMods("TestPack1").contains("OptiFine") && 
             manager.getModpackMods("TestPack2").contains("Tinkers Construct"));
        
        // Test removing mods from modpacks
        manager.removeModFromModpack("TestPack1", "OptiFine");
        test("Remove mods from modpacks", 
             manager.getModpackMods("TestPack1").size() == 1 && 
             !manager.getModpackMods("TestPack1").contains("OptiFine"));
        
        // Test removing entire modpack
        manager.removeModpack("TestPack2");
        test("Remove modpack", 
             manager.getModpacks().size() == 1 && 
             !manager.getModpacks().contains("TestPack2"));
        
        // Test edge cases
        manager.addModpack(null);
        manager.addModpack("");
        manager.addModpack("   ");
        test("Handle invalid modpack names", 
             manager.getModpacks().size() == 1); // Should still be just TestPack1
        
        // Test duplicate prevention
        manager.addModpack("TestPack1");
        test("Prevent duplicate modpacks", 
             manager.getModpacks().size() == 1);
    }
    
    /**
     * Test realistic usage scenarios
     */
    private void testIntegrationScenarios() {
        section("INTEGRATION SCENARIO TESTS");
        
        ModpackManager manager = new ModpackManager();
        
        // Scenario: Create a survival modpack
        manager.addModpack("Survival Enhanced");
        manager.addModToModpack("Survival Enhanced", "JEI");
        manager.addModToModpack("Survival Enhanced", "Waystones");
        manager.addModToModpack("Survival Enhanced", "Iron Chests");
        
        test("Survival modpack creation", 
             manager.getModpackMods("Survival Enhanced").size() == 3);
        
        // Scenario: Create a tech modpack
        manager.addModpack("Tech World");
        manager.addModToModpack("Tech World", "Applied Energistics 2");
        manager.addModToModpack("Tech World", "Thermal Expansion");
        manager.addModToModpack("Tech World", "Mekanism");
        
        test("Tech modpack creation", 
             manager.getModpackMods("Tech World").size() == 3);
        
        // Scenario: Modify existing modpack
        manager.removeModFromModpack("Survival Enhanced", "Iron Chests");
        manager.addModToModpack("Survival Enhanced", "Storage Drawers");
        
        test("Modpack modification", 
             manager.getModpackMods("Survival Enhanced").size() == 3 && 
             !manager.getModpackMods("Survival Enhanced").contains("Iron Chests") && 
             manager.getModpackMods("Survival Enhanced").contains("Storage Drawers"));
        
        // Test summary generation
        String summary = manager.getSummary();
        test("Summary generation", 
             summary != null && 
             summary.contains("Total modpacks: 2") && 
             summary.contains("Survival Enhanced") && 
             summary.contains("Tech World"));
    }
    
    /**
     * Run a test and track results
     */
    private void test(String testName, boolean condition) {
        totalTests++;
        String status = condition ? "PASS" : "FAIL";
        String icon = condition ? "✓" : "✗";
        
        System.out.printf("  %s %-50s [%s]%n", icon, testName, status);
        
        if (condition) {
            passedTests++;
        }
    }
    
    /**
     * Display section header
     */
    private void section(String sectionName) {
        System.out.println("\n" + sectionName);
        System.out.println("-".repeat(sectionName.length()));
    }
    
    /**
     * Display final test results
     */
    private void displayResults() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    FINAL RESULTS");
        System.out.println("=".repeat(60));
        System.out.printf("Total Tests: %d%n", totalTests);
        System.out.printf("Passed: %d%n", passedTests);
        System.out.printf("Failed: %d%n", (totalTests - passedTests));
        
        if (totalTests > 0) {
            System.out.printf("Success Rate: %.1f%%%n", (passedTests * 100.0 / totalTests));
        }
        
        if (passedTests == totalTests) {
            System.out.println("\n🎉 ALL TESTS PASSED! The Modpack Manager is working perfectly!");
        } else {
            System.out.println("\n⚠️  Some tests failed. Please review the implementation.");
        }
        System.out.println("=".repeat(60));
    }
}