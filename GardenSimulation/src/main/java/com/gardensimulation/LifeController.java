package com.gardensimulation;

import com.gardensimulation.Plant.Plants;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

public class LifeController implements Runnable {
    private static final Logger log = Logger.getLogger(TemperatureController.class.getName());
    private DaySimulator currentDay = new DaySimulator();
    private boolean isRunning = true;
    private int tempDay = 1;
    private static GridPane grid;
    private Map<String, Node> gridNodeMap;
    WeatherController weatherController = new WeatherController();
    PestController pestController = new PestController();
    private ViewController viewController;

    public LifeController() {

    }

    public LifeController(DaySimulator daySimulator, Map<String, Node> gridNodeMap, ViewController viewController) {
        this.gridNodeMap = gridNodeMap;
        this.viewController = viewController.getViewController();

        daySimulator.setDayChangeListener(day ->
        {
            log.info("Morning! Day: " + day + " Garden Status Check!");
//            viewController.autoPlacePlant();
            weatherController.generateRandomWeather();
            weatherController.simulateDailyWeather();
            viewController.updateWeather(weatherController.getCurrentWeather(), weatherController.getWeatherWidget());
            weatherController.updateWeatherForNextDay();

            synchronized (Plants.plantsList) {
                Iterator<Plants> iterator = Plants.plantsList.iterator();
                while (iterator.hasNext()) {
                    Plants plant;
//                if (iterator.next() != null) {
                    plant = iterator.next();
                    if (plant.isAlive()) {
                        plant.setAge(plant.getAge() - 5);
                    }
                    if (plant.getAge() <= 0) {
                        plant.setAge(0);
                        plant.die();
//                    plant.setAlive(false);
//                    log.severe(plant.getName() + " is Dead!");
//                    removePlantFromGrid(plant.getRow(), plant.getCol());
                        iterator.remove();
                    }
//                }

                }
            }
//            for (Plants plant : Plants.plantsList) {
//                if (plant.isAlive()) {
//                    plant.setAge(plant.getAge() - 1);
//                }
//                if (plant.getAge() <= 0) {
//                    plant.setAlive(false);
//                    log.severe(plant.getName() + " is Dead!");
//                    removePlantFromGrid(plant.getRow(), plant.getCol());
//                    Plants.plantsList.remove(plant);
//                }
//            }
        });
    }

    public void initialize() {
        log.info("Morning! Day: 1 Garden Status Check!");
        weatherController.generateRandomWeather();
        weatherController.simulateDailyWeather();
        System.out.println("***************WEATHER UPDATE FROM LIFE FOR DAY 1");
        System.out.println(weatherController.getCurrentWeather());
        Platform.runLater(() -> {
            ViewController.updateWeather(weatherController.getCurrentWeather(), weatherController.getWeatherWidget());
        });
    }

    public void setGrid(GridPane grid) {
        this.grid = grid;
    }

    public void removePlantFromGrid(int row, int col) {

        System.out.println("Removing plant: " + row + col);
        String cellKey = row + "," + col;
        // Iterate over all children in the GridPane
        for (Node node : LifeController.grid.getChildren()) {
            Integer nodeRow = GridPane.getRowIndex(node); // Get row index
            Integer nodeCol = GridPane.getColumnIndex(node); // Get column index

            // Handle cases where row or column indices are not explicitly set (defaults to 0)
            nodeRow = (nodeRow == null) ? 0 : nodeRow;
            nodeCol = (nodeCol == null) ? 0 : nodeCol;

            // Check if the node is at the specified row and column
            if (nodeRow == row && nodeCol == col) {
                if (node instanceof ImageView) { // Check if the node is an ImageView
                    LifeController.grid.getChildren().remove(node);
//                    ImageView imageView = (ImageView) node;
//                    imageView.setImage(null); // Remove the image, but keep the ImageView node intact
//                    Plants plantToRemove = findPlant(row, col);
//                    if (plantToRemove != null) {
//                        Plants.plantsList.remove(plantToRemove); // Remove the plant from the list
//                    }

                    break; // Exit the loop once the image is removed
                }
            }
        }
//        viewController.setCurrentPlantCount(viewController.getCurrentPlantCount() - 1);
//        viewController.getOccupiedCells().remove(cellKey);

//        Rectangle emptyCell = new Rectangle(50, 50); // Set the size of the empty cell
//        emptyCell.setStroke(Color.BLACK);            // Set the border color
//        emptyCell.setFill(Color.TRANSPARENT);       // Make the cell transparent (empty)
//
//        // Set the position of the empty cell
//        GridPane.setRowIndex(emptyCell, row);
//        GridPane.setColumnIndex(emptyCell, col);
//
//        // Add the empty cell with borders to the grid
//        grid.getChildren().add(emptyCell);
    }

    private Plants findPlant(int row, int col) {
        for (Plants plant : Plants.plantsList) {
            if (plant.getRow() == row && plant.getCol() == col) {
                return plant;
            }
        }
        return null; // Return null if the plant is not found
    }

    public void adjustPlantHealth() {
//        System.out.println("Inside adjust plant health");

        for (Plants plant : Plants.plantsList) {
            if (!plant.isAlive()) {
                System.out.println("Age of this plant before death");
                System.out.println(plant.getAge());
                System.out.println(currentDay.getDayCounter());
                log.severe(plant.getName() + " is dead!");
            }
        }
    }

    @Override
    public void run() {
        log.info("Lifecycle thread is running!");
        this.initialize();
        while (isRunning) {
//            this.adjustPlantHealth();
            try {
//                Periodically check every 1 seconds
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                isRunning = false;
            }
        }
    }
}
