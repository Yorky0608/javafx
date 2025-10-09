package com.example;

import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * User interface layer using JavaFX
 * Extends ModpackManager to inherit data management functionality
 */
public class ModpackGUI extends ModpackManager {
    // Main JavaFX application stage
    private Stage primaryStage;
    // Currently selected modpack
    private String currentModpack;
    // GUI list showing mods
    private ListView<String> modListView;
    
    private VBox mainLayout;
    private final Label statusLabel;

    /**
     * Constructor initializes GUI components
     */
    public ModpackGUI() {
        super();
        this.currentModpack = "";
        this.modListView = new ListView<>();
        this.statusLabel = new Label("Ready");
        
        // Load saved data once at startup
        loadFromJson();
    }
    
    /**
     * Auto-saves data whenever changes are made
     */
    private void autoSave() {
        saveToJson();
        statusLabel.setText("Auto-saved at " + java.time.LocalTime.now().toString().substring(0, 8));
    }

    /**
     * Initializes the main interface
     */
    public void createGUI(Stage stage) {
        this.primaryStage = stage;
        
        primaryStage.setTitle("Minecraft Modpack Manager");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        
        showMainScreen();
        primaryStage.show();
    }

    /**
     * Displays modpack selection screen
     */
    public void showMainScreen() {
        mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(20));
        
        // Title
        Label titleLabel = new Label("Minecraft Modpack Manager");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        
        // Modpack selection area
        Label selectLabel = new Label("Select or Create a Modpack:");
        selectLabel.setStyle("-fx-font-size: 16px;");
        
        ListView<String> modpackListView = new ListView<>();
        ObservableList<String> modpackItems = FXCollections.observableArrayList(getModpacks());
        modpackListView.setItems(modpackItems);
        modpackListView.setPrefHeight(200);
        
        // Refresh the list to show loaded data
        refreshModpackList(modpackItems);
        
        // Buttons
        HBox buttonBox = new HBox(10);
        Button selectButton = new Button("Select Modpack");
        Button createButton = new Button("Create New Modpack");
        Button deleteButton = new Button("Delete Modpack");
        Button saveButton = new Button("Manual Save");
        
        buttonBox.getChildren().addAll(selectButton, createButton, deleteButton, saveButton);
        
        // Event handlers
        selectButton.setOnAction(e -> {
            String selected = modpackListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selectModpack(selected);
            } else {
                showAlert("Please select a modpack first.");
            }
        });
        
        createButton.setOnAction(e -> createNewModpack(modpackItems));
        deleteButton.setOnAction(e -> deleteSelectedModpack(modpackListView, modpackItems));
        saveButton.setOnAction(e -> {
            saveToJson();
            statusLabel.setText("Data manually saved");
        });
        
        mainLayout.getChildren().addAll(titleLabel, selectLabel, modpackListView, buttonBox, statusLabel);
        
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
    }

    /**
     * Handles modpack selection
     */
    public void selectModpack() {
        // This method is called from showMainScreen with the selected modpack
    }
    
    private void selectModpack(String modpack) {
        this.currentModpack = modpack;
        showModpackScreen(modpack);
    }

    /**
     * Shows mod management screen for a specific modpack
     * @param modpack the name of the modpack to manage
     */
    public void showModpackScreen(String modpack) {
        VBox modpackLayout = new VBox(10);
        modpackLayout.setPadding(new Insets(20));
        
        // Title
        Label titleLabel = new Label("Managing: " + modpack);
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
        // Mod list
        Label modListLabel = new Label("Current Mods:");
        modListView = new ListView<>();
        ObservableList<String> modItems = FXCollections.observableArrayList(getModpackMods(modpack));
        modListView.setItems(modItems);
        modListView.setPrefHeight(300);
        
        // Buttons
        HBox buttonBox = new HBox(10);
        Button addButton = new Button("Add Mod");
        Button removeButton = new Button("Remove Mod");
        Button changesButton = new Button("View Changes");
        Button backButton = new Button("Back to Main");
        
        buttonBox.getChildren().addAll(addButton, removeButton, changesButton, backButton);
        
        // Event handlers
        addButton.setOnAction(e -> addModButton(modItems));
        removeButton.setOnAction(e -> removeModButton(modItems));
        changesButton.setOnAction(e -> showChangesDialog());
        backButton.setOnAction(e -> showMainScreen());
        
        modpackLayout.getChildren().addAll(titleLabel, modListLabel, modListView, buttonBox, statusLabel);
        
        Scene scene = new Scene(modpackLayout);
        primaryStage.setScene(scene);
    }

    /**
     * Handles add mod button click
     */
    public void addModButton() {
        // This method is called from showModpackScreen with the mod list
    }
    
    private void addModButton(ObservableList<String> modItems) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Mod");
        dialog.setHeaderText("Add a new mod to " + currentModpack);
        dialog.setContentText("Mod name:");
        
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(modName -> {
            if (!modName.trim().isEmpty()) {
                addModToModpack(currentModpack, modName);
                modItems.add(modName);
                autoSave(); // Auto-save after adding mod
                statusLabel.setText("Added mod: " + modName);
            }
        });
    }

    /**
     * Handles remove mod button click
     */
    public void removeModButton() {
        // This method is called from showModpackScreen with the mod list
    }
    
    private void removeModButton(ObservableList<String> modItems) {
        String selectedMod = modListView.getSelectionModel().getSelectedItem();
        if (selectedMod != null) {
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Remove Mod");
            confirmDialog.setHeaderText("Remove mod from " + currentModpack);
            confirmDialog.setContentText("Are you sure you want to remove: " + selectedMod + "?");
            
            Optional<ButtonType> result = confirmDialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                removeModFromModpack(currentModpack, selectedMod);
                modItems.remove(selectedMod);
                autoSave(); // Auto-save after removing mod
                statusLabel.setText("Removed mod: " + selectedMod);
            }
        } else {
            showAlert("Please select a mod to remove.");
        }
    }

    /**
     * Shows changes made to modpack
     */
    public void getUpdatedModpack() {
        showChangesDialog();
    }

    /**
     * Displays log of all changes
     */
    public void showChangesDialog() {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Modpack Changes");
        dialog.setHeaderText("Changes for " + currentModpack);
        
        StringBuilder content = new StringBuilder();
        
        List<String> added = getAddedMods();
        List<String> removed = getRemovedMods();
        
        if (added.isEmpty() && removed.isEmpty()) {
            content.append("No changes recorded.");
        } else {
            if (!added.isEmpty()) {
                content.append("Added Mods:\n");
                for (String mod : added) {
                    content.append("+ ").append(mod).append("\n");
                }
            }
            
            if (!removed.isEmpty()) {
                if (!added.isEmpty()) content.append("\n");
                content.append("Removed Mods:\n");
                for (String mod : removed) {
                    content.append("- ").append(mod).append("\n");
                }
            }
        }
        
        dialog.setContentText(content.toString());
        dialog.showAndWait();
        
        // Clear changes after viewing
        clearChanges();
    }
    
    // Helper methods
    private void createNewModpack(ObservableList<String> modpackItems) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create Modpack");
        dialog.setHeaderText("Create a new modpack");
        dialog.setContentText("Modpack name:");
        
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                addModpack(name);
                modpackItems.add(name);
                autoSave(); // Auto-save after creating modpack
                statusLabel.setText("Created modpack: " + name);
            }
        });
    }
    
    private void deleteSelectedModpack(ListView<String> listView, ObservableList<String> items) {
        String selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Delete Modpack");
            confirmDialog.setHeaderText("Delete modpack");
            confirmDialog.setContentText("Are you sure you want to delete: " + selected + "?");
            
            Optional<ButtonType> result = confirmDialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                removeModpack(selected);
                items.remove(selected);
                autoSave(); // Auto-save after deleting modpack
                statusLabel.setText("Deleted modpack: " + selected);
            }
        } else {
            showAlert("Please select a modpack to delete.");
        }
    }
    
    private void refreshModpackList(ObservableList<String> items) {
        items.clear();
        items.addAll(getModpacks());
    }
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}