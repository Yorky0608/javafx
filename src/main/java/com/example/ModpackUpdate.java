package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for tracking modpack changes
 * Manages lists of recently added and removed mods
 */
public class ModpackUpdate {
    // List of mods recently added
    private final List<String> addedMods;
    // List of mods recently removed
    private final List<String> removedMods;

    /**
     * Constructor initializes empty lists for tracking changes
     */
    public ModpackUpdate() {
        this.addedMods = new ArrayList<>();
        this.removedMods = new ArrayList<>();
    }

    /**
     * Checks for changes in modpack
     * @param modpack the name of the modpack to check
     */
    public void checkModpackMods(String modpack) {
        // Implementation would compare current state with previous state
        // For now, this is a placeholder for future implementation
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
            // Remove from removed list if it was previously removed
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
            // Remove from added list if it was previously added
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