# Minecraft Modpack Manager
A JavaFX application for managing Minecraft modpacks and tracking changes to mod collections.

## Project Overview
This project implements a modpack management system. It consists of three main classes that follow an inheritance hierarchy, allowing for comprehensive modpack and mod management with a user-friendly JavaFX interface.

## Class Architecture

### UML Class Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                     ModpackUpdate                           │
├─────────────────────────────────────────────────────────────┤
│ - addedMods: List<String>                                   │
│ - removedMods: List<String>                                 │
├─────────────────────────────────────────────────────────────┤
│ + ModpackUpdate()                                           │
│ + checkModpackMods(modpack: String): void                  │
│ + getAddedMods(): List<String>                              │
│ + getRemovedMods(): List<String>                            │
│ + addMod(mod: String): void                                 │
│ + removeMod(mod: String): void                              │
│ + clearChanges(): void                                      │
│ + hasChanges(): boolean                                     │
└─────────────────────────────────────────────────────────────┘
                              ▲
                              │ extends
                              │
┌─────────────────────────────────────────────────────────────┐
│                    ModpackManager                           │
├─────────────────────────────────────────────────────────────┤
│ - modpacks: List<String>                                    │
│ - modpackMods: Map<String, List<String>>                    │
│ - DATA_FILE: String                                         │
├─────────────────────────────────────────────────────────────┤
│ + ModpackManager()                                          │
│ + getModpacks(): List<String>                               │
│ + getModpackMods(modpack: String): List<String>             │
│ + setModpacks(modpacks: List<String>): void                 │
│ + setModpackMods(modpack: String, mods: List<String>): void │
│ + addModpack(name: String): void                            │
│ + removeModpack(name: String): void                         │
│ + addModToModpack(modpack: String, mod: String): void       │
│ + removeModFromModpack(modpack: String, mod: String): void  │
│ + saveToJson(): void                                        │
│ + loadFromJson(): void                                      │
│ + getSummary(): String                                      │
└─────────────────────────────────────────────────────────────┘
                              ▲
                              │ extends
                              │
┌─────────────────────────────────────────────────────────────┐
│                      ModpackGUI                             │
├─────────────────────────────────────────────────────────────┤
│ - primaryStage: Stage                                       │
│ - currentModpack: String                                    │
│ - modListView: ListView<String>                             │
│ - mainLayout: VBox                                          │
│ - statusLabel: Label                                        │
├─────────────────────────────────────────────────────────────┤
│ + ModpackGUI()                                              │
│ + createGUI(stage: Stage): void                             │
│ + showMainScreen(): void                                    │
│ + showModpackScreen(modpack: String): void                  │
│ + autoSave(): void                                          │
│ + selectModpack(): void                                     │
│ + addModButton(): void                                      │
│ + removeModButton(): void                                   │
│ + showChangesDialog(): void                                 │
└─────────────────────────────────────────────────────────────┘
                              │
                              │ uses
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                         App                                 │
├─────────────────────────────────────────────────────────────┤
│ - modpackGUI: ModpackGUI                                    │
├─────────────────────────────────────────────────────────────┤
│ + start(primaryStage: Stage): void                          │
│ + main(args: String[]): void                                │
└─────────────────────────────────────────────────────────────┘
```

### 1. ModpackUpdate (Base Class)
**Purpose**: Base class for tracking modpack changes

**Key Features**:
- Maintains lists of recently added and removed mods
- Provides methods to track changes in modpacks
- Implements change history functionality

**Fields**:
- `addedMods: List<String>` - List of mods recently added
- `removedMods: List<String>` - List of mods recently removed

**Methods**:
- `checkModpackMods(String modpack)` - Checks for changes in modpack
- `getAddedMods()` - Returns list of added mods
- `getRemovedMods()` - Returns list of removed mods
- `addMod(String mod)` - Adds mod to added list
- `removeMod(String mod)` - Adds mod to removed list
- `clearChanges()` - Clears all tracked changes
- `hasChanges()` - Returns true if there are any tracked changes

### 2. ModpackManager (Extends ModpackUpdate)
**Purpose**: Core data management for modpacks and their mods

**Key Features**:
- Stores modpack collections and their associated mods
- Manages mod assignments to specific modpacks
- Handles data persistence with auto-save functionality

**Fields**:
- `modpacks: List<String>` - List of all modpack names
- `modpackMods: Map<String, List<String>>` - Maps modpack name to its mods

**Methods**:
- `getModpacks()` - Returns all tracked modpacks
- `getModpackMods(String modpack)` - Returns mods in specific pack
- `setModpacks(List<String> modpacks)` - Sets the modpack list
- `setModpackMods(String modpack, List<String> mods)` - Sets mods for a modpack
- `addModpack(String name)` - Adds new modpack to tracking
- `removeModpack(String name)` - Removes modpack from tracking
- `addModToModpack(String modpack, String mod)` - Adds mod to specific modpack
- `removeModFromModpack(String modpack, String mod)` - Removes mod from modpack
- `saveToJson()` - Saves data to text file
- `loadFromJson()` - Loads data from text file
- `getSummary()` - Returns summary of modpack data

### 3. ModpackGUI (Extends ModpackManager)
**Purpose**: User interface layer using JavaFX

**Key Features**:
- Creates visual interface for modpack management
- Handles user interactions and events
- Provides automatic data persistence
- Displays modpack information and changes

**Fields**:
- `primaryStage: Stage` - Main JavaFX application stage
- `currentModpack: String` - Currently selected modpack
- `modListView: ListView<String>` - GUI list showing mods

**Methods**:
- `createGUI(Stage stage)` - Initializes the main interface
- `showMainScreen()` - Displays modpack selection
- `showModpackScreen(String modpack)` - Shows mod management screen
- `autoSave()` - Automatically saves changes when data is modified

## Testing
The project includes comprehensive test suite:

**SimpleTestRunner**
- Tests change tracking functionality
- Validates add/remove operations
- Tests modpack CRUD operations
- Validates mod management within modpacks
- Tests edge cases (null, empty inputs)
- Verifies data persistence
- Tests integration scenarios

## Running Tests
```bash
# Run all tests
./run-tests.bat
```

## Compilation and Execution
```bash
# Compile the project
./compile.bat

# Run the application
./run-gui.bat
```

## Usage
1. **Launch the Application**: Run the main App class
2. **Create Modpacks**: Use the interface to create new modpacks
3. **Select Modpack**: Choose a modpack to manage
4. **Manage Mods**: Add or remove mods using the interface
5. **Auto-Save**: All changes are automatically saved

## Data Storage
The application saves data to `modpack_data.txt` in a simple text format:
- Modpack names are listed under "MODPACKS:" section
- Mod mappings are listed under "MODPACK_MODS:" section
- Each modpack's mods are indented under the modpack name

## Class Relationships
```
ModpackUpdate (Base)
    ↑ inherits
ModpackManager (Data Management)
    ↑ inherits  
ModpackGUI (User Interface)
```

This inheritance structure allows:
- ModpackGUI to access all ModpackManager functionality
- ModpackManager to access all ModpackUpdate functionality