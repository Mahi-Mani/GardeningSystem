package com.example.gardensimulation;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Load the background image
//        Image backgroundImage = new Image("https://media.istockphoto.com/id/1217173487/photo/abstract-blurred-garden-and-green-leaf-with-wooden-table-counter-background-for-show-promote.jpg?s=1024x1024&w=is&k=20&c=m16sRHv7d5AMVzo5PVuuag8acffMcQ__3B0ia9lZ3Zc="); // Path to your image file

//        ImageView backgroundView = new ImageView(backgroundImage);

        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: lightgreen");
//        backgroundView.setPreserveRatio(true);
//        backgroundView.setFitWidth(Region.USE_COMPUTED_SIZE);
//        backgroundView.setFitHeight(Region.USE_COMPUTED_SIZE);
//        stackPane.getChildren().add(backgroundView);

//      Create grid
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false);

//        Add the grid cells with brown borders
        int numRows = 6;
        int numCols = 8;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                // Create a rectangle to represent each grid cell
                Rectangle cell = new Rectangle(100, 100); 
                cell.setFill(Color.TRANSPARENT);
                cell.setStroke(Color.BROWN);
                grid.add(cell, col, row);
            }
        }

        // Overlay the grid on top of the background
        stackPane.getChildren().add(grid);
        StackPane.setAlignment(grid, javafx.geometry.Pos.BOTTOM_CENTER);

        // Set up the scene and show the stage
        Scene scene = new Scene(stackPane, 800, 700);
        primaryStage.setTitle("Automated Gardening System");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            double gridHeight = newValue.doubleValue() / 2;
            grid.setPrefHeight(gridHeight);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}