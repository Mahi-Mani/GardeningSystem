package com.gardensimulation;


import com.gardensimulation.Pests.*;
import com.gardensimulation.Plant.*;
import com.gardensimulation.Pests.*;
import com.gardensimulation.Plant.*;
import javafx.application.Platform;
import javafx.scene.Node;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Pos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.*;

public class ViewController {
    private ExecutorService executor = Executors.newFixedThreadPool(5);
//    private SprinklerController sprinklerController;
//    private RainController rainController;
    private DaySimulator daySimulator;
    private GridPane grid;
    private List<Rectangle> cells;
    private String selectedPlant = "rose"; // Default plant type
    private static final Logger log = Logger.getLogger(ViewController.class.getName());
    private TemperatureController temperatureController;
    private LifeController life;
        private PestController pestController;
    private PesticideController pesticideController;
    private WeatherWidget weatherWidget;
    private Map<String, Node> gridNodeMap = new HashMap<>();

    public ViewController() {
        daySimulator = new DaySimulator();
//        sprinklerController = new SprinklerController();
//        rainController = new RainController();
//        temperatureController = new TemperatureController(45);

        pestController = new PestController(this);
        life = new LifeController(daySimulator, gridNodeMap);
        weatherWidget = new WeatherWidget(life.weatherController.getCurrentWeather());
        pesticideController = new PesticideController(new WeatherController());
//        executor.submit(sprinklerController);
//        executor.submit(temperatureController);
        executor.submit(life);
//        executor.submit(life.weatherController);
//        executor.submit(pestController);
        executor.submit(pesticideController);
    }

    // Get the DaySimulator UI
    BorderPane rootPane = new BorderPane();


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
//        Platform.runLater(() -> {
//        gridPane.add(weatherWidget, 3, 3);
//        });

        Button sprinkerBtn = new Button();
        Button rainBtn = new Button();
        sprinkerBtn.setOnAction(e -> {
//            sprinklerController.activateSprinklers(Plants.plantsList);
        });

        rainBtn.setOnAction(e -> {
//            rainController.generateRainfall(Plants.plantsList);
        });
        Image sprinklerImage = new Image("https://www.bankrate.com/2022/04/07090806/sprinkler-system-cost-667767602.jpg?auto=webp&optimize=high&crop=16:9"); // Replace with a real image URL or file path
        ImageView imageView = new ImageView(sprinklerImage);
        Image rainImage = new Image("https://static.vecteezy.com/system/resources/thumbnails/042/146/565/small/ai-generated-beautiful-rain-day-view-photo.jpg");
        ImageView rainImageView = new ImageView(rainImage);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        rainImageView.setFitWidth(150);
        rainImageView.setFitWidth(150);
        sprinkerBtn.setGraphic(imageView);
        rainBtn.setGraphic(rainImageView);

        // Button styling
        sprinkerBtn.setStyle(
                "-fx-background-color: transparent; -fx-border-color: transparent;"
        );
        sprinkerBtn.setOnMouseEntered(e -> sprinkerBtn.setStyle(
                "-fx-background-color: #003300; -fx-border-color: transparent; -fx-border-radius: 5;"
        ));
        sprinkerBtn.setOnMouseExited(e -> sprinkerBtn.setStyle(
                "-fx-background-color: transparent; -fx-border-color: transparent;"
        ));
        rainBtn.setStyle("-fx-background-color: transparent;");

        GridPane btnPane = new GridPane();
        btnPane.setHgap(20); // Horizontal gap between columns
        btnPane.setVgap(15);
        btnPane.add(sprinkerBtn, 0, 0);
        btnPane.add(rainBtn, 1, 0);

        VBox layout = new VBox(20);
        Platform.runLater(() -> {
            layout.getChildren().addAll(daySimulator.getDaySimulatorUI(), gridPane,
                    grid, btnPane, weatherWidget);
        });
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

                ArrayList<Pest> rosePests = new ArrayList<Pest>();
                rosePests.add(new Aphids());
                rosePests.add(new Beetles());
                Rose rose = new Rose("Rose", 100, 15, 100, 60, 80, 10, 10, 0, 2, 0, rosePests, true, row, col);

                // Create a StackPane for this cell
                StackPane roseCell = new StackPane();
                roseCell.getChildren().add(rose.getPlantView()); // Add plant image to the StackPane
                roseCell.setStyle("-fx-border-color: black; -fx-border-width: 1;");
                grid.add(roseCell, col, row);
                gridNodeMap.put(row + "," + col, roseCell);
                life.setGrid(grid);
                Plants.plantsList.add(rose);
                log.info("Planting Rose at Col: " + col + " Row: " + row);
                break;
            case "sunflower":
                ArrayList<Pest> sunflowerPests = new ArrayList<Pest>();
                sunflowerPests.add(new Aphids());
                sunflowerPests.add(new Beetles());
                sunflowerPests.add(new Cutworms());
                Sunflower sunflower = new Sunflower("Sunflower", 100, 100, 50, 25, 80, 20, 10, 0, 2, 0, sunflowerPests, true, row, col);

                StackPane sunflowerCell = new StackPane();
                sunflowerCell.getChildren().add(sunflower.getPlantView());
                sunflowerCell.setStyle("-fx-border-color: black; -fx-border-width: 1;");

                grid.add(sunflowerCell, col, row);
                gridNodeMap.put(row + "," + col, sunflowerCell);
                life.setGrid(grid);
                Plants.plantsList.add(sunflower);

                log.info("Planting Sunflower at Col: " + col + " Row: " + row);
                break;
            case "lily":
                ArrayList<Pest> lilyPests = new ArrayList<Pest>();
                lilyPests.add(new Aphids());
                lilyPests.add(new SpiderMites());
                lilyPests.add(new Beetles());
                Lily lily = new Lily("Lily", 100, 30, 95, 55, 60, 25, 8, 0, 3, 0, lilyPests, true, row, col);
                StackPane lilyCell = new StackPane();
                lilyCell.getChildren().add(lily.getPlantView());
                lilyCell.setStyle("-fx-border-color: black; -fx-border-width: 1;");

                grid.add(lilyCell, col, row);
                gridNodeMap.put(row + "," + col, lilyCell);
                life.setGrid(grid);
                Plants.plantsList.add(lily);

                log.info("Planting Lily at Col: " + col + " Row: " + row);
                break;
            case "tomato":
                ArrayList<Pest> tomatoPests = new ArrayList<Pest>();
                tomatoPests.add(new Aphids());
                tomatoPests.add(new Whiteflies());
                tomatoPests.add(new HornWorms());
                Tomato tomato = new Tomato("Tomato", 100, 12, 98, 60, 70, 8, 14, 0, 7, 0, tomatoPests, true, row, col);

                StackPane tomatoCell = new StackPane();
                tomatoCell.getChildren().add(tomato.getPlantView());
                tomatoCell.setStyle("-fx-border-color: black; -fx-border-width: 1;");

                grid.add(tomatoCell, col, row);
                gridNodeMap.put(row + "," + col, tomatoCell);
                life.setGrid(grid);
                Plants.plantsList.add(tomato);

                log.info("Planting Tomato at Col: " + col + " Row: " + row);
                break;
            case "tulip":
                ArrayList<Pest> tulipPests = new ArrayList<Pest>();
                tulipPests.add(new Aphids());
                tulipPests.add(new SpiderMites());
                tulipPests.add(new BulbFly());
                Tulip tulip = new Tulip("Tulip", 100, 18, 100, 56, 60, 14, 8, 0, 3, 0, tulipPests, true, row, col);


                grid.add(tulip.getPlantView(), col, row);
                gridNodeMap.put(row + "," + col, tulip.getPlantView());
                life.setGrid(grid);
                Plants.plantsList.add(tulip);
                log.info("Planting a Tulip at Col: " + col + " Row: " + row);
                break;
            case "lemon":
                ArrayList<Pest> lemonPests = new ArrayList<Pest>();
                lemonPests.add(new Aphids());
                lemonPests.add(new LeafMiner());
                Lemon lemon = new Lemon("Lemon", 100, 25, 104, 50, 90, 18, 18, 0, 4, 0, lemonPests, true, row, col);
                grid.add(lemon.getPlantView(), col, row);
                gridNodeMap.put(row + "," + col, lemon.getPlantView());
                life.setGrid(grid);
                Plants.plantsList.add(lemon);
                log.info("Planting a Lemon at Col: " + col + " Row: " + row);
                break;
            case "orange":
                ArrayList<Pest> orangePests = new ArrayList<Pest>();
                orangePests.add(new Aphids());
                orangePests.add(new Whiteflies());
                Orange orange = new Orange("Orange", 100, 15, 105, 53, 40, 10, 5, 0, 5, 0, orangePests, true, row, col);
                grid.add(orange.getPlantView(), col, row);
                gridNodeMap.put(row + "," + col, orange.getPlantView());
                life.setGrid(grid);
                Plants.plantsList.add(orange);
                log.info("Planting an Orange at Col: " + col + " Row: " + row);
                break;
            case "apple":
                ArrayList<Pest> applePests = new ArrayList<Pest>();
                applePests.add(new Aphids());
                applePests.add(new Caterpillars());
                applePests.add(new CodlingMoth());
                Apple apple = new Apple("Apple", 100, 15, 102, 50, 50, 15, 20, 0, 6, 0, applePests, true, row, col);
                grid.add(apple.getPlantView(), col, row);
                gridNodeMap.put(row + "," + col, apple.getPlantView());
                life.setGrid(grid);
                Plants.plantsList.add(apple);
                log.info("Planting an Apple at Col: " + col + " Row: " + row);
                break;
        }
    }

    //    Overlay Pest
    public void overlayPest(int row, int col) {
        System.out.println("Inside  overlay");
        String pestImagePath = "https://uwm.edu/field-station/wp-content/uploads/sites/380/2016/12/357x500-11.jpg";
//        ImageView pestImage = new ImageView(new Image(pestImagePath));
//        pestImage.setFitWidth(20);
//        pestImage.setFitHeight(20);
//        grid.add(pestImage, col, row);
//        grid.add(new ImageView(new Image("https://uwm.edu/field-station/wp-content/uploads/sites/380/2016/12/357x500-11.jpg")), col, row);

        String key = row + "," + col;
        if (gridNodeMap.containsKey(key)) {
            System.out.println("Inside if loop");
            StackPane cell = (StackPane) gridNodeMap.get(key);

            // Add pest's image to the StackPane
            ImageView pestImage = new ImageView(new Image(pestImagePath));
            pestImage.setFitWidth(20);
            pestImage.setFitHeight(20);
            pestImage.setTranslateX(15); // Offset for positioning
            pestImage.setTranslateY(-15);

            cell.getChildren().add(pestImage);
            //        for (Node node : grid.getChildren()) {
//            Integer nodeRow = GridPane.getRowIndex(node);
//            Integer nodeCol = GridPane.getColumnIndex(node);
//
//            if (Objects.equals(nodeRow, row) && Objects.equals(nodeCol, col)) {
//                System.out.println("Inside if loop of overlay");
////                StackPane cell = (StackPane) node;
//
//                // Add pest image
//                ImageView pestImage = new ImageView(new Image(pestImagePath));
//                pestImage.setFitWidth(20);
//                pestImage.setFitHeight(20);
//                pestImage.setTranslateX(15); // Offset pest position
//                pestImage.setTranslateY(-15);
//
//                grid.add(pestImage, col, row);
//                break;
//            }
//        }


        }


    }
}