package com.gardensimulation;

import com.almasb.fxgl.core.View;
import com.gardensimulation.Pests.*;
import com.gardensimulation.Plant.*;
import javafx.application.Platform;

import java.util.*;
import java.util.logging.Logger;

public class PestController {
    private boolean isRunning = true;
    private final Random random = new Random();
    private final List<Pest> allPests;
    private ViewController viewController;
    private WeatherController weatherController;
    Random randomSeverity = new Random();
    private volatile boolean isPesticideApplied = false;
    private static final Logger log = Logger.getLogger(PestController.class.getName());

    public PestController(ViewController viewController) {
        this.allPests = Arrays.asList(new Aphids(), new Beetles(), new BulbFly(), new CodlingMoth(),
                new Cutworms(), new HornWorms(), new SpiderMites(),
                new Whiteflies(), new LeafMiner(), new Caterpillars());
        this.viewController = viewController;
    }

    public PestController() {
        this.allPests = Arrays.asList(new Aphids(), new Beetles(), new BulbFly(), new CodlingMoth(),
                new Cutworms(), new HornWorms(), new SpiderMites(),
                new Whiteflies(), new LeafMiner(), new Caterpillars());
    }

    public synchronized void setPesticideApplied(boolean value) {
        isPesticideApplied = value;
    }

    public synchronized boolean getPesticideApplied() {
        return isPesticideApplied;
    }

    //    Method to set a plan of attack based on weather
    public void attackPlan(String weather) {
        List<Pest> selectedPests = new ArrayList<>();

        if (PesticideController.isPesticideApplied) {
            log.info("Pesticide in effect, no pest activity!");
            ViewController.appendLogToFile("Pesticide in effect, no pest activity!", "info");
            ViewController.addLogMessage("Pesticide in effect, no pest activity!", "info");
        } else {
            if (weather == "sunny") {
                log.info("(30% chance) pest activity due to Sunny weather.");
                ViewController.addLogMessage("(30% chance) pest activity", "info");
                ViewController.appendLogToFile("(30% chance) pest activity", "info");
                if (random.nextInt(10) < 3) {
                    selectedPests = selectRandomPests(weather);
                    System.out.println(selectedPests);
                    attackPlants(selectedPests);
                    ViewController.addLogMessage("Pest actvity: Mild", "warn");
                    ViewController.appendLogToFile("Pest actvity: Mild", "warn");
                } else {
                    log.info("No pest activity!");
                    ViewController.addLogMessage("No pest activity!", "info");
                    ViewController.appendLogToFile("No pest activity!", "info");
                }
            } else if (weather == "cloudy") {
                log.info("(40% chance) pest activity due to Cloudy weather.");
                ViewController.addLogMessage("(40% chance) pest activity", "info");
                ViewController.appendLogToFile("(40% chance) pest activity", "info");
                if (random.nextInt(10) < 4) {
                    selectedPests = selectRandomPests(weather);
                    System.out.println(selectedPests);
                    attackPlants(selectedPests);
                    ViewController.addLogMessage("Pest actvity: Moderate", "warn");
                    ViewController.appendLogToFile("Pest actvity: Moderate", "warn");
                } else {
                    System.out.println("No pest activity!");
                    log.info("No pest activity!");
                    ViewController.addLogMessage("No pest activity!", "info");
                    ViewController.appendLogToFile("No pest activity!", "info");
                }
            } else if (weather == "rainy") {
                log.warning("Increased pest activity due to high humid Rainy weather!");
                ViewController.addLogMessage("Increased pest activity due to high humid Rainy weather!!", "warn");
                ViewController.appendLogToFile("Increased pest activity due to high humid Rainy weather!!", "warn");
                selectedPests = selectRandomPests(weather);
                System.out.println(selectedPests);
                attackPlants(selectedPests);
                PesticideController.isPesticideApplied = false;
                log.warning("Pesticide is washed away due to rain.");
                ViewController.addLogMessage("Pesticide is washed away due to rain.", "warn");
                ViewController.appendLogToFile("Pesticide is washed away due to rain.", "warn");
            }
        }
    }

    //    Method to attack plants using weather based pests
    public void attackPlants(List<Pest> selectedPests) {
        // Attack plants vulnerable to the selected pests
        Iterator<Pest> pestIterator = selectedPests.iterator();
        while (pestIterator.hasNext()) {
            Pest pest = pestIterator.next();

            synchronized (Plants.plantsList) {
                Iterator<Plants> plantIterator = Plants.plantsList.iterator();
                while (plantIterator.hasNext()) {
                    Plants plant = plantIterator.next();

                    if (plant.getParasites().contains(pest) && plant.getAge() > 0) {
                        int severity = randomSeverity.nextInt(pest.getSeverity() + 1);
                        plant.setAge(plant.getAge() - severity);  // Reduce health based on pest severity
                        log.warning(pest.getName() + " is attacking " + plant.getName() + " at (" + plant.getRow() + ", " + plant.getCol() + ")!");
                        ViewController.addLogMessage(pest.getName() + " is attacking " + plant.getName() + " at (" + plant.getRow() + ", " + plant.getCol() + ")! Health affected by " + pest.getSeverity() + " units", "severe");
                        ViewController.appendLogToFile(pest.getName() + " is attacking " + plant.getName() + " at (" + plant.getRow() + ", " + plant.getCol() + ")! Health affected by " + pest.getSeverity() + " units", "severe");
                        plant.addPest(pest.getName());

                        if (plant instanceof Apple) {
                            Apple apple = (Apple) plant;
                            Platform.runLater(apple::setAttackedImage);
                        } else if (plant instanceof Orange) {
                            Orange orange = (Orange) plant;
                            Platform.runLater(orange::setAttackedImage);
                        } else if (plant instanceof Lemon) {
                            Lemon lemon = (Lemon) plant;
                            Platform.runLater(lemon::setAttackedImage);
                        } else if (plant instanceof Lily) {
                            Lily lily = (Lily) plant;
                            Platform.runLater(lily::setAttackedImage);
                        } else if (plant instanceof Tulip) {
                            Tulip tulip = (Tulip) plant;
                            Platform.runLater(tulip::setAttackedImage);
                        } else if (plant instanceof Tomato) {
                            Tomato tomato = (Tomato) plant;
                            Platform.runLater(tomato::setAttackedImage);
                        } else if (plant instanceof Rose) {
                            Rose rose = (Rose) plant;
                            Platform.runLater(rose::setAttackedImage);
                        } else if (plant instanceof Sunflower) {
                            Sunflower sunflower = (Sunflower) plant;
                            Platform.runLater(sunflower::setAttackedImage);
                        }

                        if (plant.getAge() <= 0) {
                            // Handle plant death logic
                            ViewController.addLogMessage("Plant " + plant.getName() + " died due to pest attack!", "severe");
                            ViewController.appendLogToFile("Plant " + plant.getName() + " died due to pest attack!", "severe");
                            plant.die();
                            plantIterator.remove();
                        }
                        break; // Exit inner loop after attacking one plant
                    }
                }
            }
        }
    }

    //    Select pests based on weather
    private List<Pest> selectRandomPests(String weather) {
        List<Pest> potentialPests = new ArrayList<>();
        List<String> pestNames = new ArrayList<>();
        Iterator<Pest> iterator = allPests.iterator();

        while (iterator.hasNext()) {
            Pest nextPest = iterator.next();
            if (weather.equals(nextPest.getWeather())) {
                potentialPests.add(nextPest);
                pestNames.add(nextPest.getName());
            } else if (weather.equals(nextPest.getWeather())) {
                potentialPests.add(nextPest);
                pestNames.add(nextPest.getName());
            } else if (weather.equalsIgnoreCase(nextPest.getWeather())) {
                potentialPests.add(nextPest);
                pestNames.add(nextPest.getName());
            }
        }

        ViewController.addLogMessage("Today's weather attracts " + pestNames.toString(), "warn");
        log.warning("Today's weather attracts " + pestNames.toString());
        ViewController.appendLogToFile("Today's weather attracts " + pestNames.toString(), "warn");
        return potentialPests;
    }
}
