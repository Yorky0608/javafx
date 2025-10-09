# Minecraft Modpack Manager

A JavaFX-based application for managing Minecraft modpacks and their mods with automatic data persistence.

## Features

✅ **Three-Class Inheritance Architecture**: ModpackGUI → ModpackManager → ModpackUpdate  
✅ **Auto-Save Functionality**: Changes are automatically saved without manual intervention  
✅ **Persistent Data Storage**: Text-based format with proper indentation handling  
✅ **Comprehensive GUI**: Complete JavaFX interface for all operations  
✅ **Robust Testing**: Full test suite covering all functionality  

## Project Overview

This project implements a production-ready modpack management system with clean architecture, comprehensive testing, and reliable data persistence.

## Class Architecture

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
- Handles data persistence (save/load functionality)

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
- Displays modpack information and changes

**Fields**:
- `primaryStage: Stage` - Main JavaFX application stage
- `currentModpack: String` - Currently selected modpack
- `modListView: ListView<String>` - GUI list showing mods

**Methods**:
- `createGUI(Stage stage)` - Initializes the main interface
- `showMainScreen()` - Displays modpack selection
- `showModpackScreen(String modpack)` - Shows mod management screen
- `addModButton()` - Handles add mod button click
- `removeModButton()` - Handles remove mod button
- `selectModpack()` - Handles modpack selection
- `showChangesDialog()` - Displays log of all changes

## Testing

The project includes comprehensive test suites for all classes:

### ModpackUpdateTest
- Tests change tracking functionality
- Validates add/remove operations
- Tests edge cases (null, empty inputs)
- Verifies change clearing

### ModpackManagerTest
- Tests modpack CRUD operations
- Validates mod management within modpacks
- Tests data persistence (save/load)
- Verifies summary generation

### Running Tests
```bash
# Run all tests
java -cp src/test/java:src/main/java com.example.TestRunner

# Run individual test classes
java -cp src/test/java:src/main/java com.example.ModpackUpdateTest
java -cp src/test/java:src/main/java com.example.ModpackManagerTest
java -cp src/test/java:src/main/java com.example.ModpackGUITest
```

### Compilation and Execution
```bash
# Compile the project
mvn clean compile

# Run the application
mvn javafx:run

# Or run directly
java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -cp target/classes com.example.App
```

## Usage

1. **Launch the Application**: Run the main App class
2. **Create Modpacks**: Use the "Create New Modpack" button
3. **Select Modpack**: Choose a modpack to manage
4. **Manage Mods**: Add or remove mods using the respective buttons
5. **View Changes**: Click "View Changes" to see modification history
6. **Save Data**: Use "Save Data" to persist changes to file
7. **Load Data**: Use "Load Data" to restore previous sessions

## Data Storage

The application saves data to `modpack_data.txt` in a simple text format:
- Modpack names are listed under "MODPACKS:" section
- Mod mappings are listed under "MODPACK_MODS:" section
- Each modpack's mods are indented under the modpack name

## Future Enhancements

- Integration with actual mod files
- Web-based modpack synchronization
- Mod version tracking
- Export/Import functionality
- Mod dependency management

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