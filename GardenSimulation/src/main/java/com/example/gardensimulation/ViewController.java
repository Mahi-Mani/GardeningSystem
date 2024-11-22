package com.example.gardensimulation;

import com.almasb.fxgl.core.View;
import com.example.gardensimulation.Plant.*;
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
import java.util.logging.*;

import java.util.ArrayList;
import java.util.List;

public class ViewController {
    private SprinklerController sprinklerController;
    private GridPane grid;
    private List<Rectangle> cells;
    private String selectedPlant = "rose"; // Default plant type
    private static final Logger log = Logger.getLogger(ViewController.class.getName());

    public ViewController() {
        sprinklerController = new SprinklerController();
    }

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

        RadioButton tomatoRadio = new RadioButton("Tomato");
        tomatoRadio.setToggleGroup(plantGroup);

        RadioButton tulipRadio = new RadioButton("Tulip");
        tulipRadio.setToggleGroup(plantGroup);

        RadioButton lemonRadio = new RadioButton("Lemon");
        lemonRadio.setToggleGroup(plantGroup);

        RadioButton orangeRadio = new RadioButton("Orange");
        orangeRadio.setToggleGroup(plantGroup);

        RadioButton appleRadio = new RadioButton("Apple");
        appleRadio.setToggleGroup(plantGroup);

//        Style radio buttons
        String stylePlantGroup = "-fx-font-size: 14px; -fx-padding: 5; -fx-cursor: hand;";
        roseRadio.setStyle(stylePlantGroup);
        sunflowerRadio.setStyle(stylePlantGroup);
        lilyRadio.setStyle(stylePlantGroup);
        tomatoRadio.setStyle(stylePlantGroup);
        tulipRadio.setStyle(stylePlantGroup);
        lemonRadio.setStyle(stylePlantGroup);
        orangeRadio.setStyle(stylePlantGroup);
        appleRadio.setStyle(stylePlantGroup);
//        rb5.setStyle(stylePlantGroup);
//        rb6.setStyle(stylePlantGroup);
//        rb7.setStyle(stylePlantGroup);
//        rb8.setStyle(stylePlantGroup);

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
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20); // Horizontal gap between columns
        gridPane.setVgap(15);
        gridPane.add(roseRadio, 0, 0); // Column 0, Row 0
        gridPane.add(sunflowerRadio, 1, 0); // Column 1, Row 0
        gridPane.add(lilyRadio, 2, 0); // Column 2, Row 0
        gridPane.add(tomatoRadio, 3, 0); // Column 3, Row 0

        gridPane.add(tulipRadio, 0, 1); // Column 0, Row 1
        gridPane.add(lemonRadio, 1, 1); // Column 1, Row 1
        gridPane.add(orangeRadio, 2, 1); // Column 2, Row 1
        gridPane.add(appleRadio, 3, 1);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(gridPane,
                grid);
        layout.setStyle("-fx-padding: 20; -fx-border-color: #ccc; -fx-border-width: 1; -fx-border-radius: 5;");
//        layout.setAlignment(Pos.TOP_CENTER);
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
                Rose rose = new Rose("Rose", 10, 15, 25, 45, 80, 70, 10, 0, 2, 0, true);
                grid.add(rose.getPlantView(), col, row);
                Plants.plantsList.add(rose);
                log.info("Planting Rose at Col: " + col + " Row: " + row);
                break;
            case "sunflower":
                Sunflower sunflower = new Sunflower("Sunflower", 20, 30, 25, 40, 80, 70, 10, 0, 2, 0, true);
                grid.add(sunflower.getPlantView(), col, row);
                Plants.plantsList.add(sunflower);
                log.info("Planting Sunflower at Col: " + col + " Row: " + row);
                break;
            case "lily":
                Lily lily = new Lily("Lily", 25, 30, 20, 60, 60, 40, 8, 0, 3, 0, true);
                grid.add(lily.getPlantView(), col, row);
                Plants.plantsList.add(lily);
                log.info("Planting Lily at Col: " + col + " Row: " + row);
                break;
            case "tomato":
                Tomato tomato = new Tomato("Tomato", 35, 12, 35, 40, 70, 70, 14, 0, 7, 0, true);
                grid.add(tomato.getPlantView(), col, row);
                Plants.plantsList.add(tomato);
                log.info("Planting a Tomato at Col: " + col + " Row: " + row);
                break;
            case "tulip":
                Tulip tulip = new Tulip("Tulip", 25, 18, 30, 60, 60, 40, 8, 0, 3, 0, true);
                grid.add(tulip.getPlantView(), col, row);
                Plants.plantsList.add(tulip);
                log.info("Planting a Tulip at Col: " + col + " Row: " + row);
                break;
            case "lemon":
                Lemon lemon = new Lemon("Lemon", 5, 25, 40, 50, 90, 80, 18, 0, 4, 0, true);
                grid.add(lemon.getPlantView(), col, row);
                Plants.plantsList.add(lemon);
                log.info("Planting a Lemon at Col: " + col + " Row: " + row);
                break;
            case "orange":
                Orange orange = new Orange("Orange", 55, 15, 35, 50, 40, 30, 5, 0, 5, 0, true);
                grid.add(orange.getPlantView(), col, row);
                Plants.plantsList.add(orange);
                log.info("Planting an Orange at Col: " + col + " Row: " + row);
                break;
            case "apple":
                Apple apple = new Apple("Apple", 20, 15, 30, 60, 50, 40, 20, 0, 6, 0, true);
                grid.add(apple.getPlantView(), col, row);
                Plants.plantsList.add(apple);
                log.info("Planting an Apple at Col: " + col + " Row: " + row);
                break;
        }
//        System.out.println(Plants.plantsList);
//        if (plantView != null) {
//            ImageView plantView = new ImageView(plantImage);
//            plantView.setFitHeight(80);
//            plantView.setFitWidth(80);
//
        // Add the plant image to the clicked cell
//            grid.add(plantView, col, row);
//        } else {
//            System.out.println("Image not found for " + selectedPlant);
//        }
    }
}