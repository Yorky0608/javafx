package com.example;

/**
 * Test class for ModpackUpdate functionality
 * Tests change tracking for added and removed mods
 */
public class ModpackUpdateTest {
    
    private ModpackUpdate modpackUpdate;
    private int testsPassed = 0;
    private int testsTotal = 0;
    
    public ModpackUpdateTest() {
        this.modpackUpdate = new ModpackUpdate();
    }
    
    public void runAllTests() {
        System.out.println("Running ModpackUpdate Tests...\n");
        
        testInitialState();
        testAddMod();
        testRemoveMod();
        testAddMultipleMods();
        testRemoveMultipleMods();
        testAddThenRemoveSameMod();
        testRemoveThenAddSameMod();
        testClearChanges();
        testAddNullMod();
        testAddEmptyMod();
        testRemoveNullMod();
        testRemoveEmptyMod();
        testCheckModpackMods();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ModpackUpdate Tests Summary:");
        System.out.println("Tests passed: " + testsPassed + "/" + testsTotal);
        System.out.println("Success rate: " + (testsPassed * 100 / testsTotal) + "%");
        System.out.println("=".repeat(50));
    }
    
    private void testInitialState() {
        System.out.println("Testing initial state...");
        testsTotal++;
        
        try {
            modpackUpdate = new ModpackUpdate();
            
            if (modpackUpdate.getAddedMods().isEmpty() && 
                modpackUpdate.getRemovedMods().isEmpty() && 
                !modpackUpdate.hasChanges()) {
                System.out.println("✓ Initial state test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Initial state test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Initial state test failed with exception: " + e.getMessage());
        }
    }
    
    private void testAddMod() {
        System.out.println("Testing add mod...");
        testsTotal++;
        
        try {
            modpackUpdate = new ModpackUpdate();
            modpackUpdate.addMod("OptiFine");
            
            if (modpackUpdate.getAddedMods().size() == 1 && 
                modpackUpdate.getAddedMods().contains("OptiFine") && 
                modpackUpdate.hasChanges()) {
                System.out.println("✓ Add mod test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Add mod test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Add mod test failed with exception: " + e.getMessage());
        }
    }
    
    private void testRemoveMod() {
        System.out.println("Testing remove mod...");
        testsTotal++;
        
        try {
            modpackUpdate = new ModpackUpdate();
            modpackUpdate.removeMod("JEI");
            
            if (modpackUpdate.getRemovedMods().size() == 1 && 
                modpackUpdate.getRemovedMods().contains("JEI") && 
                modpackUpdate.hasChanges()) {
                System.out.println("✓ Remove mod test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Remove mod test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Remove mod test failed with exception: " + e.getMessage());
        }
    }
    
    private void testAddMultipleMods() {
        System.out.println("Testing add multiple mods...");
        testsTotal++;
        
        try {
            modpackUpdate = new ModpackUpdate();
            modpackUpdate.addMod("OptiFine");
            modpackUpdate.addMod("JEI");
            modpackUpdate.addMod("Biomes O' Plenty");
            
            if (modpackUpdate.getAddedMods().size() == 3 && 
                modpackUpdate.getAddedMods().contains("OptiFine") && 
                modpackUpdate.getAddedMods().contains("JEI") && 
                modpackUpdate.getAddedMods().contains("Biomes O' Plenty")) {
                System.out.println("✓ Add multiple mods test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Add multiple mods test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Add multiple mods test failed with exception: " + e.getMessage());
        }
    }
    
    private void testRemoveMultipleMods() {
        System.out.println("Testing remove multiple mods...");
        testsTotal++;
        
        try {
            modpackUpdate = new ModpackUpdate();
            modpackUpdate.removeMod("OptiFine");
            modpackUpdate.removeMod("JEI");
            
            if (modpackUpdate.getRemovedMods().size() == 2 && 
                modpackUpdate.getRemovedMods().contains("OptiFine") && 
                modpackUpdate.getRemovedMods().contains("JEI")) {
                System.out.println("✓ Remove multiple mods test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Remove multiple mods test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Remove multiple mods test failed with exception: " + e.getMessage());
        }
    }
    
    private void testAddThenRemoveSameMod() {
        System.out.println("Testing add then remove same mod...");
        testsTotal++;
        
        try {
            modpackUpdate = new ModpackUpdate();
            modpackUpdate.addMod("OptiFine");
            modpackUpdate.removeMod("OptiFine");
            
            if (!modpackUpdate.getAddedMods().contains("OptiFine") && 
                modpackUpdate.getRemovedMods().contains("OptiFine") && 
                modpackUpdate.getAddedMods().size() == 0 && 
                modpackUpdate.getRemovedMods().size() == 1) {
                System.out.println("✓ Add then remove same mod test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Add then remove same mod test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Add then remove same mod test failed with exception: " + e.getMessage());
        }
    }
    
    private void testRemoveThenAddSameMod() {
        System.out.println("Testing remove then add same mod...");
        testsTotal++;
        
        try {
            modpackUpdate = new ModpackUpdate();
            modpackUpdate.removeMod("JEI");
            modpackUpdate.addMod("JEI");
            
            if (modpackUpdate.getAddedMods().contains("JEI") && 
                !modpackUpdate.getRemovedMods().contains("JEI") && 
                modpackUpdate.getAddedMods().size() == 1 && 
                modpackUpdate.getRemovedMods().size() == 0) {
                System.out.println("✓ Remove then add same mod test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Remove then add same mod test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Remove then add same mod test failed with exception: " + e.getMessage());
        }
    }
    
    private void testClearChanges() {
        System.out.println("Testing clear changes...");
        testsTotal++;
        
        try {
            modpackUpdate = new ModpackUpdate();
            modpackUpdate.addMod("OptiFine");
            modpackUpdate.removeMod("JEI");
            
            boolean hadChanges = modpackUpdate.hasChanges();
            modpackUpdate.clearChanges();
            
            if (hadChanges && 
                modpackUpdate.getAddedMods().isEmpty() && 
                modpackUpdate.getRemovedMods().isEmpty() && 
                !modpackUpdate.hasChanges()) {
                System.out.println("✓ Clear changes test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Clear changes test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Clear changes test failed with exception: " + e.getMessage());
        }
    }
    
    private void testAddNullMod() {
        System.out.println("Testing add null mod...");
        testsTotal++;
        
        try {
            modpackUpdate = new ModpackUpdate();
            modpackUpdate.addMod(null);
            
            if (modpackUpdate.getAddedMods().isEmpty() && !modpackUpdate.hasChanges()) {
                System.out.println("✓ Add null mod test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Add null mod test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Add null mod test failed with exception: " + e.getMessage());
        }
    }
    
    private void testAddEmptyMod() {
        System.out.println("Testing add empty mod...");
        testsTotal++;
        
        try {
            modpackUpdate = new ModpackUpdate();
            modpackUpdate.addMod("");
            modpackUpdate.addMod("   ");
            
            if (modpackUpdate.getAddedMods().isEmpty() && !modpackUpdate.hasChanges()) {
                System.out.println("✓ Add empty mod test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Add empty mod test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Add empty mod test failed with exception: " + e.getMessage());
        }
    }
    
    private void testRemoveNullMod() {
        System.out.println("Testing remove null mod...");
        testsTotal++;
        
        try {
            modpackUpdate = new ModpackUpdate();
            modpackUpdate.removeMod(null);
            
            if (modpackUpdate.getRemovedMods().isEmpty() && !modpackUpdate.hasChanges()) {
                System.out.println("✓ Remove null mod test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Remove null mod test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Remove null mod test failed with exception: " + e.getMessage());
        }
    }
    
    private void testRemoveEmptyMod() {
        System.out.println("Testing remove empty mod...");
        testsTotal++;
        
        try {
            modpackUpdate = new ModpackUpdate();
            modpackUpdate.removeMod("");
            modpackUpdate.removeMod("   ");
            
            if (modpackUpdate.getRemovedMods().isEmpty() && !modpackUpdate.hasChanges()) {
                System.out.println("✓ Remove empty mod test passed");
                testsPassed++;
            } else {
                System.out.println("✗ Remove empty mod test failed");
            }
        } catch (Exception e) {
            System.out.println("✗ Remove empty mod test failed with exception: " + e.getMessage());
        }
    }
    
    private void testCheckModpackMods() {
        System.out.println("Testing check modpack mods...");
        testsTotal++;
        
        try {
            modpackUpdate = new ModpackUpdate();
            modpackUpdate.checkModpackMods("TestPack");
            System.out.println("✓ Check modpack mods test passed");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("✗ Check modpack mods test failed with exception: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        ModpackUpdateTest test = new ModpackUpdateTest();
        test.runAllTests();
    }
}