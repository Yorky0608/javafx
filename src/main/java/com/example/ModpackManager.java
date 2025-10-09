package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Core data management for modpacks and their mods
 *     public void saveToJson() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            // Save modpacks
            writer.println("MODPACKS:");
            for (String modpack : modpacks) {
                writer.println(modpack);
            }
            
            // Save modpack-mod mappings
            writer.println("MODPACK_MODS:");
            for (Map.Entry<String, List<String>> entry : modpackMods.entrySet()) {
                writer.println(entry.getKey() + ":");
                for (String mod : entry.getValue()) {
                    writer.println("  " + mod);
                }
            }pdate to inherit change tracking functionality
 */
public class ModpackManager extends ModpackUpdate {
    // List of all modpack names
    private final List<String> modpacks;
    // Maps modpack name to its mods
    private final Map<String, List<String>> modpackMods;
    
    private static final String DATA_FILE = "modpack_data.txt";

    /**
     * Constructor initializes empty collections for modpack management
     */
    public ModpackManager() {
        super();
        this.modpacks = new ArrayList<>();
        this.modpackMods = new HashMap<>();
    }

    /**
     * Returns all tracked modpacks
     * @return List of modpack names
     */
    public List<String> getModpacks() {
        return new ArrayList<>(modpacks);
    }

    /**
     * Returns mods in specific pack
     * @param modpack the name of the modpack
     * @return List of mod names in the specified modpack
     */
    public List<String> getModpackMods(String modpack) {
        return new ArrayList<>(modpackMods.getOrDefault(modpack, new ArrayList<>()));
    }

    /**
     * Sets the modpack list
     * @param modpacks List of modpack names to set
     */
    public void setModpacks(List<String> modpacks) {
        if (modpacks != null) {
            this.modpacks.clear();
            this.modpacks.addAll(modpacks);
        }
    }

    /**
     * Sets mods for a modpack
     * @param modpack the name of the modpack
     * @param mods List of mods for the modpack
     */
    public void setModpackMods(String modpack, List<String> mods) {
        if (modpack != null && mods != null) {
            this.modpackMods.put(modpack, new ArrayList<>(mods));
        }
    }

    /**
     * Adds new modpack to tracking
     * @param name the name of the modpack to add
     */
    public void addModpack(String name) {
        if (name != null && !name.trim().isEmpty() && !modpacks.contains(name)) {
            modpacks.add(name);
            modpackMods.put(name, new ArrayList<>());
        }
    }

    /**
     * Removes modpack from tracking
     * @param name the name of the modpack to remove
     */
    public void removeModpack(String name) {
        if (name != null) {
            modpacks.remove(name);
            modpackMods.remove(name);
        }
    }

    /**
     * Adds a mod to a specific modpack
     * @param modpack the name of the modpack
     * @param mod the name of the mod to add
     */
    public void addModToModpack(String modpack, String mod) {
        if (modpack != null && mod != null && !mod.trim().isEmpty()) {
            List<String> mods = modpackMods.computeIfAbsent(modpack, k -> new ArrayList<>());
            if (!mods.contains(mod)) {
                mods.add(mod);
                addMod(mod); // Track this change
            }
        }
    }

    /**
     * Removes a mod from a specific modpack
     * @param modpack the name of the modpack
     * @param mod the name of the mod to remove
     */
    public void removeModFromModpack(String modpack, String mod) {
        if (modpack != null && mod != null) {
            List<String> mods = modpackMods.get(modpack);
            if (mods != null && mods.remove(mod)) {
                removeMod(mod); // Track this change
            }
        }
    }

    /**
     * Saves data to text file
     */
    public void saveToJson() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            // Save modpacks
            writer.println("MODPACKS:");
            for (String modpack : modpacks) {
                writer.println(modpack);
            }
            
            // Save modpack-mod mappings
            writer.println("MODPACK_MODS:");
            for (Map.Entry<String, List<String>> entry : modpackMods.entrySet()) {
                writer.println(entry.getKey() + ":");
                for (String mod : entry.getValue()) {
                    writer.println("  " + mod);
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Loads data from text file
     */
    public void loadFromJson() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return; // No existing data file found, start with empty data
        }

        // Clear existing data before loading
        modpacks.clear();
        modpackMods.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            String currentSection = "";
            String currentModpack = "";
            
            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim();
                
                if (trimmedLine.equals("MODPACKS:")) {
                    currentSection = "MODPACKS";
                } else if (trimmedLine.equals("MODPACK_MODS:")) {
                    currentSection = "MODPACK_MODS";
                } else if (currentSection.equals("MODPACKS") && !trimmedLine.isEmpty()) {
                    modpacks.add(trimmedLine);
                } else if (currentSection.equals("MODPACK_MODS")) {
                    if (trimmedLine.endsWith(":")) {
                        currentModpack = trimmedLine.substring(0, trimmedLine.length() - 1);
                        modpackMods.put(currentModpack, new ArrayList<>());
                    } else if (line.startsWith("  ") && !currentModpack.isEmpty()) {
                        String mod = line.substring(2).trim();
                        modpackMods.get(currentModpack).add(mod);
                    }
                }
            }
            
            // Data loaded successfully
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }

    /**
     * Returns a summary of current modpack data
     * @return String summary of modpacks and mod counts
     */
    public String getSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Modpack Summary:\n");
        summary.append("Total modpacks: ").append(modpacks.size()).append("\n");
        
        for (String modpack : modpacks) {
            List<String> mods = modpackMods.get(modpack);
            summary.append("- ").append(modpack).append(": ");
            summary.append(mods != null ? mods.size() : 0).append(" mods\n");
        }
        
        return summary.toString();
    }
}