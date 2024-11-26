package com.example.gardensimulation;

import com.example.gardensimulation.Pests.*;
import com.example.gardensimulation.Plant.Plants;

import java.util.*;

public class PestController implements Runnable {
    private boolean isRunning = true;
    private final Random random = new Random();
    //    private final List<Plants> plants;
    private final List<Pest> allPests;
    //    ViewController viewController = new ViewController();
    private ViewController viewController;

    public PestController(ViewController viewController) {
//        this.plants = plants;
        this.allPests = Arrays.asList(new Aphids(), new Beetles(), new BulbFly(), new CodlingMoth(),
                new Cutworms(), new HornWorms(), new SpiderMites(),
                new Whiteflies(), new LeafMiner(), new Caterpillars());
        this.viewController = viewController;
    }

    //    Method to attack plants
    public void attackPlants() {
        System.out.println("Inside attack plants method!");
        // Select one or more pests randomly
        List<Pest> selectedPests = selectRandomPests();

        // Attack plants vulnerable to the selected pests
        for (Pest pest : selectedPests) {
            for (Plants plant : Plants.plantsList) {
                if (plant.getParasites().contains(pest)) {
                    plant.setAge(plant.getAge() - pest.getSeverity() * 7);  // Reduce health based on pest severity
                    System.out.println(pest.getName() + " are attacking " + plant.getName() + " at (" + plant.getRow() + ", " + plant.getCol() + ")!");
                    viewController.overlayPest(plant.getRow(), plant.getCol());
                    if (plant.getAge() <= 0) {
//                        plant.die();
                    }
                    break;
                }
            }
        }
    }

    private List<Pest> selectRandomPests() {
        int numPests = random.nextInt(allPests.size()) + 1; // Select 1 to all pests
        Collections.shuffle(allPests); // Randomize pest order
        return allPests.subList(0, numPests); // Select random pests
    }

    public void run() {
        while (isRunning) {
            try {
//                Simulate every 2 days
                Thread.sleep(20000);
                attackPlants();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                isRunning = false;
            }
        }
    }
}
