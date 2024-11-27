package com.gardensimulation;

import com.gardensimulation.Plant.Plants;

public class PesticideController implements Runnable {
    private boolean isRunning = true;

    public void treatPlant(Plants plant) {
        // Log the treatment action
        System.out.println("Applying pesticide to " + plant.getName() + " at Row: " + plant.getRow() + " at Col: " + plant.getCol());

        // Clear pests from the plant
        plant.removePests();
        plant.setAge(plant.getAge() + 5);
        System.out.println("Pesticide application increased the age of plant: " + plant.getAge());

        // Update the UI (remove pest overlays)
//        viewController.updatePlantPestOverlay(plant);
    }

    public void run() {
        while (isRunning) {
            try {
                System.out.println("Pesticide thread is running!");
                // Scan plants for pests
                for (Plants plant : Plants.plantsList) {
                    System.out.println(plant.getPestAttack());
                    if (!plant.getPestAttack().isEmpty()) {
                        // Treat the plant and remove pests
                        treatPlant(plant);
                    }
                }

                // Wait for cooldown time before the next pesticide application
                Thread.sleep(40000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                isRunning = false;
            }
        }
    }
}
