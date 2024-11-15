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

import java.util.ArrayList;
import java.util.List;

public class ViewController {
    private GridPane grid;
    private List<Rectangle> cells;
    private int nextIndex;

    public StackPane createContent() {
        // Create the root layout
        StackPane stackPane = new StackPane();

        // Set background color for the root layout
        stackPane.setStyle("-fx-background-color: #008631;");

        // Create the 8x6 grid
        grid = createGrid();

//        Button to plant rose
        Button roseButton = new Button("Plant Rose");
        roseButton.setOnAction(e -> placePlant("rose"));

        // Align grid to the bottom center
//        StackPane.setAlignment(grid, Pos.BOTTOM_CENTER);
//        stackPane.getChildren().add(grid);
//
//        // Resize the grid to the upper half of the scene
//        stackPane.heightProperty().addListener((observable, oldValue, newValue) -> {
//            double gridHeight = newValue.doubleValue() / 2;
//            grid.setPrefHeight(gridHeight);
//        });

        VBox layout = new VBox(10, roseButton, grid);
        layout.setAlignment(Pos.TOP_CENTER);
        stackPane.getChildren().add(layout);

        return stackPane;
    }

    private GridPane createGrid() {
        grid = new GridPane();
        cells = new ArrayList<>();
        nextIndex = 0;
        grid.setGridLinesVisible(false);

        // Add cells with brown borders
        int numRows = 6;
        int numCols = 8;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Rectangle cell = new Rectangle(80, 80);
                cell.setFill(Color.TRANSPARENT);
                cell.setStroke(Color.BROWN);
                cells.add(cell);
                grid.add(cell, col, row);
            }
        }

        return grid;
    }

//    Function to place a plant
    private void placePlant(String plantType) {
        if(nextIndex >= cells.size()) {
            System.out.println("Garden is full!");
            return;
        }

//        Load image
        Image plantImage = null;
        if(plantType.equals("rose")) {
            plantImage = new Image("https://cdn.sanity.io/images/pn4rwssl/production/349d734442fdbcc734bd8060f126330fdf19e825-500x750.jpg?w=2880&q=75&auto=format");
        }

        ImageView plantView = new ImageView(plantImage);
        plantView.setFitHeight(80);
        plantView.setFitWidth(80);

        Rectangle cell = cells.get(nextIndex);
        grid.add(plantView, GridPane.getColumnIndex(cell), GridPane.getRowIndex(cell));
        nextIndex++;
    }
}