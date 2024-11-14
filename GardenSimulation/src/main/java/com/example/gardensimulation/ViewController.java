package com.example.gardensimulation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ViewController {
    public StackPane createContent() {
        // 1. Create the root layout
        StackPane stackPane = new StackPane();

        // 2. Set background color for the root layout
        stackPane.setStyle("-fx-background-color: #008631;"); // Light Blue color for background

        // 3. Create the 8x6 grid
        GridPane grid = createGrid();

        // 4. Align grid to the bottom center
        StackPane.setAlignment(grid, Pos.BOTTOM_CENTER);
        stackPane.getChildren().add(grid);

        // 5. Resize the grid to the lower half of the scene
        stackPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            double gridHeight = newValue.doubleValue() / 2;
            grid.setPrefHeight(gridHeight);
        });

        return stackPane;
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false);

        // Add cells with brown borders
        int numRows = 6;
        int numCols = 8;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Rectangle cell = new Rectangle(80, 80);
                cell.setFill(Color.TRANSPARENT);
                cell.setStroke(Color.BROWN);
                grid.add(cell, col, row);
            }
        }

        return grid;
    }
}