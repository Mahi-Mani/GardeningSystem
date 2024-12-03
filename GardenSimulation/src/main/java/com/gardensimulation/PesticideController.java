package com.gardensimulation;

import com.gardensimulation.Plant.*;

public class PesticideController implements Runnable {
    private boolean isRunning = true;
    private WeatherController weatherController;
    private PestController pestController;
    public static boolean isPesticideApplied = false;

    public PesticideController(WeatherController weatherController) {
//        this.weatherController = weatherController;
        pestController = new PestController();
    }

    public static void treatPlant(Plants plant) {
        // Log the treatment action
        System.out.println("Spraying pesticide to garden!");

        // Clear pests from the plant
        plant.removePests();
        plant.setAge(plant.getAge() + 5);
        if (plant.getAge() > 0) {
            if (plant instanceof Apple) {
                Apple apple = (Apple) plant;
                apple.setNormalImage(); // Change image to normal apple
            } else if (plant instanceof Orange) {
                System.out.println("inside instance f");
                Orange orange = (Orange) plant;
                orange.setNormalImage();
            } else if (plant instanceof Lemon) {
                System.out.println("inside instance f");
                Lemon lemon = (Lemon) plant;
                lemon.setNormalImage();
            } else if (plant instanceof Lily) {
                System.out.println("inside instance f");
                Lily lily = (Lily) plant;
                lily.setNormalImage();
            } else if (plant instanceof Tulip) {
                System.out.println("inside instance f");
                Tulip tulip = (Tulip) plant;
                tulip.setNormalImage();
            } else if (plant instanceof Tomato) {
                System.out.println("inside instance f");
                Tomato tomato = (Tomato) plant;
                tomato.setNormalImage();
            } else if (plant instanceof Rose) {
                System.out.println("inside instance f");
                Rose rose = (Rose) plant;
                rose.setNormalImage();
            } else if (plant instanceof Sunflower) {
                System.out.println("inside instance f");
                Sunflower sunflower = (Sunflower) plant;
                sunflower.setNormalImage();
            }
        } else {
            System.out.println("PLANT could not be SAVED even with pesticide!");
            plant.die();
        }
//        Notify pest controller
//        pestController.setPesticideApplied(true);
        isPesticideApplied = true;
        System.out.println("<<<<<<<<<< Inside Pesticide >>>>>");
        System.out.println(isPesticideApplied);

//        System.out.println(pestController.getPesticideApplied());

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
                        this.treatPlant(plant);
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
