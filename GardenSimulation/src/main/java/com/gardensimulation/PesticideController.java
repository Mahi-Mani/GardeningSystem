package com.gardensimulation;

import com.gardensimulation.Plant.Plants;

public class PesticideController implements Runnable {
    private boolean isRunning = true;
    private WeatherController weatherController;
    private PestController pestController;

    public PesticideController(WeatherController weatherController) {
        this.weatherController = weatherController;
        pestController = new PestController();
    }

    public void treatPlant(Plants plant) {
        // Log the treatment action
        System.out.println("Spraying pesticide to garden!");

        // Clear pests from the plant
        plant.removePests();
        plant.setAge(plant.getAge() + 5);
//        Notify pest controller
        pestController.setPesticideApplied(true);

        // Update the UI (remove pest overlays)
//        viewController.updatePlantPestOverlay(plant);
    }

    public void run() {
        while (isRunning) {
            try {
                System.out.println("PESTICIDE thread is running!");
//                System.out.println(this.weatherController.getCurrentWeather());
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
