package com.gardensimulation;

import com.gardensimulation.Pests.*;
import com.gardensimulation.Plant.Plants;
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
    private boolean isPesticideApplied = false;

    public PestController(ViewController viewController) {
//        this.plants = plants;
        this.allPests = Arrays.asList(new Aphids(), new Beetles(), new BulbFly(), new CodlingMoth(),
                new Cutworms(), new HornWorms(), new SpiderMites(),
                new Whiteflies(), new LeafMiner(), new Caterpillars());
        this.viewController = viewController;

    }

    public PestController() {
        this.allPests = Arrays.asList(new Aphids(), new Beetles(), new BulbFly(), new CodlingMoth(),
                new Cutworms(), new HornWorms(), new SpiderMites(),
                new Whiteflies(), new LeafMiner(), new Caterpillars());
//        weatherController = new WeatherController();
    }

    public void setPesticideApplied(boolean value) {
        this.isPesticideApplied = value;
    }

    public boolean getPesticideApplied() {
        return this.isPesticideApplied;
    }

    //    Method to attack plants
    public void attackPlan(String weather) {
        System.out.println("PRINTING WEATHER FROM PEST: " + weather);
        List<Pest> selectedPests = new ArrayList<>();

        if (this.getPesticideApplied()) {
            System.out.println("Pesticide in effect, no pest activity!");
        } else {
            if (weather == "sunny") {
                System.out.println("Expect more pest activity today due to sunny weather!"); // Select 1 to all pests
//            Select one or more pests
                selectedPests = selectRandomPests(weather);
                attackPlants(selectedPests);
            } else if (weather == "cloudy") {
                System.out.println("(30% chance) pest activity due to cloudy weather.");
                if (random.nextInt(10) < 3) {
                    System.out.println("Pest: Moderate");
                    selectedPests = selectRandomPests(weather);
                    attackPlants(selectedPests);
                } else {
                    System.out.println("No pest activity!");
                }
            } else if (weather == "rainy") {
                System.out.println("Pest activity reduced due to rain.");
                this.setPesticideApplied(false);
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

                        // If using UI updates
                        // Platform.runLater(() -> viewController.overlayPest(plant.getRow(), plant.getCol()));

                        if (plant.getAge() <= 0) {
                            // Handle plant death logic
                            System.out.println("Handing Plant dyig logic in pest..........");
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
        int numPests = 0;
        if (weather == "sunny") {
            numPests = random.nextInt(allPests.size()) + 1; // Select 1 to all pests
        } else if (weather == "cloudy") {
            numPests = 2;
        }

        Collections.shuffle(allPests); // Randomize pest order
        return allPests.subList(0, numPests); // Select random pests
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
