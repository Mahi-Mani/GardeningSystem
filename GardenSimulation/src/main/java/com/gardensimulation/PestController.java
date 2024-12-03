package com.gardensimulation;

import com.gardensimulation.Pests.*;
import com.gardensimulation.Plant.*;
import javafx.application.Platform;

import java.util.*;

public class PestController {
    private boolean isRunning = true;
    private final Random random = new Random();
    //    private final List<Plants> plants;
    private final List<Pest> allPests;
    //    ViewController viewController = new ViewController();
    private ViewController viewController;
    private WeatherController weatherController;
    Random randomSeverity = new Random();
    private volatile boolean isPesticideApplied = false;

    public PestController(ViewController viewController) {
//        this.plants = plants;
        this.allPests = Arrays.asList(new Aphids(), new Beetles(), new BulbFly(), new CodlingMoth(),
                new Cutworms(), new HornWorms(), new SpiderMites(),
                new Whiteflies(), new LeafMiner(), new Caterpillars());
        this.viewController = viewController;
//        isPesticideApplied = false;
    }

    public PestController() {
        this.allPests = Arrays.asList(new Aphids(), new Beetles(), new BulbFly(), new CodlingMoth(),
                new Cutworms(), new HornWorms(), new SpiderMites(),
                new Whiteflies(), new LeafMiner(), new Caterpillars());
//        weatherController = new WeatherController();
    }

    public synchronized void setPesticideApplied(boolean value) {
        isPesticideApplied = value;
    }

    public synchronized boolean getPesticideApplied() {
        return isPesticideApplied;
    }

    //    Method to attack plants
    public void attackPlan(String weather) {
        System.out.println("PRINTING WEATHER FROM PEST: " + weather);
        List<Pest> selectedPests = new ArrayList<>();
        System.out.println("<>>>>>>>>>" + PesticideController.isPesticideApplied + "<<<<<<<<<>>>>");

        if (PesticideController.isPesticideApplied) {
            System.out.println("Pesticide in effect, no pest activity!");
        } else {
            if (weather == "sunny") {
                System.out.println("(30% chance) pest activity due to Sunny weather.");
                if (random.nextInt(10) < 3) {
                    System.out.println("Pest: Mild");
                    selectedPests = selectRandomPests(weather);
                    System.out.println(selectedPests);
                    attackPlants(selectedPests);
                } else {
                    System.out.println("No pest activity!");
                }
            } else if (weather == "cloudy") {
                System.out.println("(40% chance) pest activity due to Cloudy weather.");
                if (random.nextInt(10) < 4) {
                    System.out.println("Pest: Moderate");
                    selectedPests = selectRandomPests(weather);
                    System.out.println(selectedPests);
                    attackPlants(selectedPests);
                } else {
                    System.out.println("No pest activity!");
                }
            } else if (weather == "rainy") {
                System.out.println("Increased pest activity due to high humid Rainy weather!");
                selectedPests = selectRandomPests(weather);
                System.out.println(selectedPests);
                attackPlants(selectedPests);
                PesticideController.isPesticideApplied = false;
                System.out.println("Pesticide is washed away due to rain.");
            }
        }
    }

    public void attackPlants(List<Pest> selectedPests) {
        System.out.println("INSIDE ATTACK PLANTS METHODS");
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
                        plant.setAge(plant.getAge() - severity * 7);  // Reduce health based on pest severity
                        System.out.println(pest.getName() + " is attacking " + plant.getName() + " at (" + plant.getRow() + ", " + plant.getCol() + ")!");
                        System.out.println("Age of " + plant.getName() + " is: " + plant.getAge());
                        plant.addPest(pest.getName());
//                        System.out.println("!!!APPLE IS ATTACJED");
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

                        // If using UI updates
                        // Platform.runLater(() -> viewController.overlayPest(plant.getRow(), plant.getCol()));

                        if (plant.getAge() <= 0) {
                            // Handle plant death logic
//                            System.out.println("Handing Plant dyig logic in pest..........");
                            plant.die();
                            plantIterator.remove();
                        }
                        break; // Exit inner loop after attacking one plant
                    }
                }
            }
        }

//        for (Pest pest : selectedPests) {
//            for (Plants plant : Plants.plantsList) {
//                if (plant.getParasites().contains(pest) && plant.getAge() > 0) {
//                    int severity = randomSeverity.nextInt(pest.getSeverity() + 1);
//                    plant.setAge(plant.getAge() - severity * 7);  // Reduce health based on pest severity
//                    System.out.println(pest.getName() + " is attacking " + plant.getName() + " at (" + plant.getRow() + ", " + plant.getCol() + ")!");
//                    System.out.println("Age of " + plant.getName() + " is: " + plant.getAge());
//                    plant.addPest(pest.getName());
////                    Platform.runLater(() -> viewController.overlayPest(plant.getRow(), plant.getCol()));
//
//                    if (plant.getAge() <= 0) {
////                        plant.die();
//                    }
//                    break;
//                }
//            }
//        }
    }

    private List<Pest> selectRandomPests(String weather) {
        List<Pest> potentialPests = new ArrayList<>();
        Iterator<Pest> iterator = allPests.iterator();
        while (iterator.hasNext()) {
            Pest nextPest = iterator.next();
            if (weather.equals(nextPest.getWeather())) {
                potentialPests.add(nextPest);
            } else if (weather.equals(nextPest.getWeather())) {
                potentialPests.add(nextPest);
            } else if (weather.equalsIgnoreCase(nextPest.getWeather())) {
                potentialPests.add(nextPest);
            }
        }

        return potentialPests;
    }

//    public void run() {
//        while (isRunning) {
//            try {
//                String currentWeather = weatherController.getCurrentWeather();
//                System.out.println("Pest controller weather update: " + currentWeather);
//                //                Adjust behaviour based on weather
////                switch (currentWeather) {
////                    case "rainy":
////                        System.out.println("Pest activity reduced due to rain.");
//////                        Thread.sleep(40000); // Longer wait time (pests less active)
////                        break;
////                    case "sunny":
////                        System.out.println("Pests are highly active in sunny weather!");
////                        Thread.sleep(5000); // Shorter wait time (pests more active)
////                        attackPlants();
////                        break;
////                    case "cloudy":
////                        System.out.println("Moderate (30% chance) pest activity due to cloudy weather.");
////                        if (random.nextInt(10) < 3) {
////                            Thread.sleep(5000);// 30% chance of rain
////                            attackPlants();
////                        } else {
////                            System.out.println("No pest activity!");
////                        }
//////                        Thread.sleep(30000); // Moderate wait time
//////                        attackPlants();
////                        break;
////                }
//                Thread.sleep(1000); //run everyday
//            } catch (InterruptedException ie) {
//                Thread.currentThread().interrupt();
//                isRunning = false;
//            }
//        }
//    }
}
