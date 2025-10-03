package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simplified version of ModpackUpdate for testing without JavaFX dependencies
 * Base class for tracking modpack changes
 */
public class ModpackUpdateSimple {
    // List of mods recently added
    private final List<String> addedMods;
    // List of mods recently removed
    private final List<String> removedMods;

    /**
     * Constructor initializes empty lists for tracking changes
     */
    public ModpackUpdateSimple() {
        this.addedMods = new ArrayList<>();
        this.removedMods = new ArrayList<>();
    }

    /**
     * Checks for changes in modpack
     * @param modpack the name of the modpack to check
     */
    public void checkModpackMods(String modpack) {
        System.out.println("Checking modpack: " + modpack + " for changes...");
    }

    /**
     * Returns list of added mods
     * @return List of recently added mod names
     */
    public List<String> getAddedMods() {
        return new ArrayList<>(addedMods);
    }

    /**
     * Returns list of removed mods
     * @return List of recently removed mod names
     */
    public List<String> getRemovedMods() {
        return new ArrayList<>(removedMods);
    }

    /**
     * Adds mod to added list
     * @param mod the name of the mod that was added
     */
    public void addMod(String mod) {
        if (mod != null && !mod.trim().isEmpty()) {
            addedMods.add(mod);
            removedMods.remove(mod);
        }
    }

    /**
     * Adds mod to removed list
     * @param mod the name of the mod that was removed
     */
    public void removeMod(String mod) {
        if (mod != null && !mod.trim().isEmpty()) {
            removedMods.add(mod);
            addedMods.remove(mod);
        }
    }

    /**
     * Clears all tracked changes
     */
    public void clearChanges() {
        addedMods.clear();
        removedMods.clear();
    }

    /**
     * Returns true if there are any tracked changes
     * @return true if there are added or removed mods
     */
    public boolean hasChanges() {
        return !addedMods.isEmpty() || !removedMods.isEmpty();
    }
}

/**
 * Simplified version of ModpackManager for testing without JavaFX dependencies
 * Core data management for modpacks and their mods
 */
class ModpackManagerSimple extends ModpackUpdateSimple {
    // List of all modpack names
    private final List<String> modpacks;
    // Maps modpack name to its mods
    private final Map<String, List<String>> modpackMods;

    /**
     * Constructor initializes empty collections for modpack management
     */
    public ModpackManagerSimple() {
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