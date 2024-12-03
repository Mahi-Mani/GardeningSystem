package com.gardensimulation;


import com.gardensimulation.Pests.*;
import com.gardensimulation.Plant.*;
import javafx.application.Platform;
import javafx.scene.Node;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
    private static final Logger log = Logger.getLogger(ViewController.class.getName());
    private ExecutorService executor = Executors.newFixedThreadPool(5);
    private SprinklerController sprinklerController;
    private RainController rainController;
    private DaySimulator daySimulator;
    private static GridPane grid;
    private List<Pane> cells;
    private static String selectedPlant = "rose"; // Default plant type
    private TemperatureController temperatureController;
    private static LifeController life;
    private PestController pestController;
    private PesticideController pesticideController;
    private static Map<String, Node> gridNodeMap = new HashMap<>();
    BorderPane root = new BorderPane();
    BorderPane root1 = new BorderPane();
    StackPane weatherPane;
    static WeatherCard weatherCard;
    private static final int MIN_PLANTS_THRESHOLD = 5; // Minimum allowed plants
    private int currentPlantCount = 0;
    private static Set<String> occupiedCells = new HashSet<>();
    private static int numRows;
    private static int numCols;
    private com.gardensimulation.ViewController viewController;
    private static PesticideCard pesticideCard;

    public ViewController() {
        daySimulator = new DaySimulator();
        sprinklerController = new SprinklerController();
        rainController = new RainController();
        temperatureController = new TemperatureController(45);

        pestController = new PestController(this);
        life = new LifeController(daySimulator, gridNodeMap, this);
//        weatherWidget = new WeatherWidget(life.weatherController.getCurrentWeather());
        pesticideController = new PesticideController(new WeatherController());
//        executor.submit(sprinklerController);
//        executor.submit(temperatureController);
        executor.submit(life);
        executor.submit(pesticideController);
        weatherCard = new WeatherCard();
        pesticideCard = new PesticideCard();

//        weatherPane = new StackPane();
//        weatherPane = new VBox(20);
//        weatherPane.setPrefWidth(1);
//        weatherPane.getChildren().add(weatherCard);
        root.setTop(weatherCard);
        root.setMaxWidth(100);
        root1.setTop(pesticideCard);
        root1.setMaxWidth(100);
    }

    public ViewController getViewController() {
        return viewController;
    }

    // Method to update the weather dynamically
    public static void updateWeather(String weatherCondition, String imageUrl) {
        weatherCard.updateWeather(weatherCondition, imageUrl);
    }

    public static void updatePesticideUI(Boolean status, String imageUrl) {
        pesticideCard.updatePesticideCard(status, imageUrl);
    }

    public void setCurrentPlantCount(int currentPlantCount) {
        this.currentPlantCount = currentPlantCount;
    }

    public int getCurrentPlantCount() {
        return this.currentPlantCount;
    }

    public Set<String> getOccupiedCells() {
        return this.occupiedCells;
    }

    // Get the DaySimulator UI
    BorderPane rootPane = new BorderPane();


    public StackPane createContent() {
        // Create the root layout
        StackPane stackPane = new StackPane();
        Image gardenBackgroundImage = new Image("https://media.istockphoto.com/id/1368553162/photo/wooden-table-and-spring-forest-background.jpg?b=1&s=612x612&w=0&k=20&c=AbipVomBmZW0uaJsypwT_fJa06RlmktwjtDXzDWQkh0=");
        BackgroundImage backgroundImage = new BackgroundImage(
                gardenBackgroundImage,
                BackgroundRepeat.NO_REPEAT, // How the image is repeated
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, // Position of the image
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        // Set the background to the Pane
        stackPane.setBackground(new Background(backgroundImage));
        // Set background color for the root layout
//        stackPane.setStyle("-fx-background-color: #008631;");

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
//        btnPane.add(sprinkerBtn, 0, 0);
//        btnPane.add(rainBtn, 1, 0);

        VBox layout = new VBox(20);
//        VBox cardLayout = new VBox(20);
        HBox cardLayout = new HBox(20);
//        VBox weatherLayout = new VBox(1);
//        weatherLayout.setAlignment(Pos.BASELINE_RIGHT);
        Platform.runLater(() -> {
            cardLayout.getChildren().addAll(root, root1);
            layout.getChildren().addAll(daySimulator.getDaySimulatorUI(),
                    grid, btnPane, cardLayout);

//            cardLayout.getChildren().addAll(layout1);
//            weatherLayout.getChildren().addAll(weatherPane);
        });
        layout.setStyle("-fx-padding: 20; -fx-border-color: #ccc; -fx-border-width: 1; -fx-border-radius: 5;");
//        weatherLayout.setPrefWidth(1);
//        weatherLayout.setStyle("-fx-padding: 500; -fx-border-color: #ccc; -fx-border-width: 10; -fx-border-radius: 5;");
//        layout.setAlignment(Pos.TOP_CENTER);

        stackPane.getChildren().addAll(layout);
//        stackPane.getChildren().add(weatherLayout);

        return stackPane;
    }

    private GridPane createGrid() {
        grid = new GridPane();
        cells = new ArrayList<Pane>();
        grid.setGridLinesVisible(false);

        // Add cells with brown borders
        numRows = 6;
        numCols = 8;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
//                Rectangle cell = new Rectangle(120, 120);
//                cell.setFill(Color.TRANSPARENT);
//                cell.setStroke(Color.BROWN);
                // Create a Pane for each cell
                Pane cell = new Pane();
                cell.setPrefSize(100, 100);

                // Set the soil background image for the cell
                BackgroundImage soilBackground = new BackgroundImage(
                        new Image("https://t3.ftcdn.net/jpg/02/57/58/20/360_F_257582025_LUf6zGRPA0x0OGaLFS1UJIgkRKrrZhAk.jpg"),
                        BackgroundRepeat.NO_REPEAT, // Repeat for seamless soil texture
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(100, 100, true, true, false, true) // Scale the image dynamically
                );
                cell.setBackground(new Background(soilBackground));
                cell.setStyle("-fx-border-color: saddlebrown; -fx-border-width: 2px;");

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

    //    Function to automatically place plants
    public static void autoPlacePlant() {
        if (Plants.plantsList.size() >= MIN_PLANTS_THRESHOLD) {
            return; // No need to add plants if the count is above the threshold
        }

        Random random = new Random();
        while (Plants.plantsList.size() < MIN_PLANTS_THRESHOLD) {
            int row = random.nextInt(numRows);
            int col = random.nextInt(numCols);

            if (!occupiedCells.contains(row + "," + col)) {
                placePlant(row, col); // Place a plant in the empty cell
            }
        }
    }

    //    Function to place a plant
    private static void placePlant(int row, int col) {
        // Check if there's already a plant in this cell
//        for (javafx.scene.Node node : grid.getChildren()) {
//            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col && !(node instanceof Rectangle)) {
//                System.out.println("Garden is full!");
//                return; // Exit if the cell already has a plant
//            }
//        }

        for (javafx.scene.Node node : grid.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                if (node instanceof Pane && ((Pane) node).getChildren().isEmpty()) {
                    // If the cell has a Pane but no children, it's empty and can have a plant
                    continue;
                } else {
                    System.out.println("Garden is full!");
                    return; // Exit if the cell already has a plant or is occupied
                }
            }
        }

        String[] plantChoices = {"rose", "sunflower", "lily", "tomato", "tulip", "lemon", "orange", "apple"};
        Random random = new Random();
        selectedPlant = plantChoices[random.nextInt(plantChoices.length)];

//        Load image
        Image plantImage = null;
        switch (selectedPlant) {
            case "rose":
                ArrayList<Pest> rosePests = new ArrayList<Pest>();
                rosePests.add(new Aphids());
                rosePests.add(new Beetles());
                rosePests.add(new Cutworms());
                Rose rose = new Rose("Rose", 100, 15, 100, 60, 80, 10, 10, 0, 2, 0, rosePests, true, row, col);
                grid.add(rose.getPlantView(), col, row);
                gridNodeMap.put(row + "," + col, rose.getPlantView());
                life.setGrid(grid);
                Plants.plantsList.add(rose);
                occupiedCells.add(row + "," + col);
                log.info("Planting Sunflower at Col: " + col + " Row: " + row);
                break;
            case "sunflower":
                ArrayList<Pest> sunflowerPests = new ArrayList<Pest>();
                sunflowerPests.add(new Aphids());
                sunflowerPests.add(new Beetles());
                sunflowerPests.add(new Cutworms());
                Sunflower sunflower = new Sunflower("Sunflower", 100, 100, 50, 25, 80, 20, 10, 0, 2, 0, sunflowerPests, true, row, col);
                grid.add(sunflower.getPlantView(), col, row);
                gridNodeMap.put(row + "," + col, sunflower.getPlantView());
                life.setGrid(grid);
                Plants.plantsList.add(sunflower);
                occupiedCells.add(row + "," + col);
                log.info("Planting Sunflower at Col: " + col + " Row: " + row);
                break;
            case "lily":
                ArrayList<Pest> lilyPests = new ArrayList<Pest>();
                lilyPests.add(new Aphids());
                lilyPests.add(new SpiderMites());
                lilyPests.add(new Beetles());
                Lily lily = new Lily("Lily", 100, 30, 95, 55, 60, 25, 8, 0, 3, 0, lilyPests, true, row, col);
                grid.add(lily.getPlantView(), col, row);
                gridNodeMap.put(row + "," + col, lily.getPlantView());
                life.setGrid(grid);
                Plants.plantsList.add(lily);
                occupiedCells.add(row + "," + col);
                log.info("Planting Lily at Col: " + col + " Row: " + row);
                break;
            case "tomato":
                ArrayList<Pest> tomatoPests = new ArrayList<Pest>();
                tomatoPests.add(new Aphids());
                tomatoPests.add(new Whiteflies());
                tomatoPests.add(new HornWorms());
                Tomato tomato = new Tomato("Tomato", 100, 12, 98, 60, 70, 8, 14, 0, 7, 0, tomatoPests, true, row, col);
                grid.add(tomato.getPlantView(), col, row);
                gridNodeMap.put(row + "," + col, tomato.getPlantView());
                life.setGrid(grid);
                Plants.plantsList.add(tomato);
                occupiedCells.add(row + "," + col);
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
                occupiedCells.add(row + "," + col);
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
                occupiedCells.add(row + "," + col);
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
                occupiedCells.add(row + "," + col);
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
                occupiedCells.add(row + "," + col);
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