package com.example.gardensimulation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;
import java.util.List;

public class ViewController{
    private GridPane grid;
    private List<Rectangle> cells;
    private String selectedPlant = "rose"; // Default plant type


    public StackPane createContent() {
        // Create the root layout
        StackPane stackPane = new StackPane();

        // Set background color for the root layout
        stackPane.setStyle("-fx-background-color: #008631;");

        // Create the 8x6 grid
        grid = createGrid();

        // Radio buttons for plant selection
        ToggleGroup plantGroup = new ToggleGroup();

        RadioButton roseRadio = new RadioButton("Rose");
        roseRadio.setToggleGroup(plantGroup);
        roseRadio.setSelected(true); // Select Rose by default

        RadioButton sunflowerRadio = new RadioButton("Sunflower");
        sunflowerRadio.setToggleGroup(plantGroup);

        RadioButton lilyRadio = new RadioButton("Lily");
        lilyRadio.setToggleGroup(plantGroup);

        // Update selectedPlant when radio button selection changes
        plantGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (plantGroup.getSelectedToggle() != null) {
                RadioButton selectedRadioButton = (RadioButton) plantGroup.getSelectedToggle();
                selectedPlant = selectedRadioButton.getText().toLowerCase();
            }
        });

        // Align grid to the bottom center
//        StackPane.setAlignment(grid, Pos.BOTTOM_CENTER);
//        stackPane.getChildren().add(grid);
//
//        // Resize the grid to the upper half of the scene
//        stackPane.heightProperty().addListener((observable, oldValue, newValue) -> {
//            double gridHeight = newValue.doubleValue() / 2;
//            grid.setPrefHeight(gridHeight);
//        });

        VBox layout = new VBox(10,
                roseRadio,
                sunflowerRadio,
                lilyRadio,
                grid);
        layout.setAlignment(Pos.TOP_CENTER);
        stackPane.getChildren().add(layout);

        return stackPane;
    }

    private GridPane createGrid() {
        grid = new GridPane();
        cells = new ArrayList<>();
        grid.setGridLinesVisible(false);

        // Add cells with brown borders
        int numRows = 6;
        int numCols = 8;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Rectangle cell = new Rectangle(80, 80);
                cell.setFill(Color.TRANSPARENT);
                cell.setStroke(Color.BROWN);

                // Add click event to the cell
                final int cellRow = row;
                final int cellCol = col;
                cell.setOnMouseClicked(event -> placePlant(cellRow, cellCol));
                cells.add(cell);
                grid.add(cell, col, row);
            }
        }

        return grid;
    }

    //    Function to place a plant
    private void placePlant(int row, int col) {
        // Check if there's already a plant in this cell
        for (javafx.scene.Node node : grid.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col && !(node instanceof Rectangle)) {
                System.out.println("Garden is full!");
                return; // Exit if the cell already has a plant
            }
        }

//        Load image
        Image plantImage = null;
        switch (selectedPlant) {
            case "rose":
                plantImage = new Image("https://cdn.sanity.io/images/pn4rwssl/production/349d734442fdbcc734bd8060f126330fdf19e825-500x750.jpg?w=2880&q=75&auto=format");
                break;
            case "sunflower":
                plantImage = new Image("https://media.istockphoto.com/id/927047528/vector/sunflower-flower-isolated.jpg?s=2048x2048&w=is&k=20&c=ARVqqtW_PFKOrVmLYpzR24RByFaAfpcflTeb0IKj6aM=");
                break;
            case "lily":
                plantImage = new Image("https://media.istockphoto.com/id/183384405/photo/lily-isolated.jpg?s=1024x1024&w=is&k=20&c=a2ivn26nEzIla2icgb52uX2bMdGr7MUDkABkcaakEW4=");
                break;
        }

        if (plantImage != null) {
            ImageView plantView = new ImageView(plantImage);
            plantView.setFitHeight(80);
            plantView.setFitWidth(80);

            // Add the plant image to the clicked cell
            grid.add(plantView, col, row);
        } else {
            System.out.println("Image not found for " + selectedPlant);
        }
    }
}