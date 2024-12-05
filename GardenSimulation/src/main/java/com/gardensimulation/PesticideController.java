package com.gardensimulation;

import com.almasb.fxgl.core.View;
import com.gardensimulation.Plant.*;
import javafx.application.Platform;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

public class PesticideController implements Runnable {
    private boolean isRunning = true;
    private WeatherController weatherController;
    private PestController pestController;
    public static boolean isPesticideApplied = false;
    private static final Logger log = Logger.getLogger(PesticideController.class.getName());

    public PesticideController(WeatherController weatherController) {
//        this.weatherController = weatherController;
        pestController = new PestController();
    }

    public static void treatPlant(Plants plant) {
        // Clear pests from the plant
        plant.removePests();
        plant.setAge(plant.getAge() + 5);
        if (plant.getAge() > 0) {
            if (plant instanceof Apple) {
                Apple apple = (Apple) plant;
                apple.setNormalImage(); // Change image to normal apple
            } else if (plant instanceof Orange) {
                Orange orange = (Orange) plant;
                orange.setNormalImage();
            } else if (plant instanceof Lemon) {
                Lemon lemon = (Lemon) plant;
                lemon.setNormalImage();
            } else if (plant instanceof Lily) {
                Lily lily = (Lily) plant;
                lily.setNormalImage();
            } else if (plant instanceof Tulip) {
                Tulip tulip = (Tulip) plant;
                tulip.setNormalImage();
            } else if (plant instanceof Tomato) {
                Tomato tomato = (Tomato) plant;
                tomato.setNormalImage();
            } else if (plant instanceof Rose) {
                Rose rose = (Rose) plant;
                rose.setNormalImage();
            } else if (plant instanceof Sunflower) {
                Sunflower sunflower = (Sunflower) plant;
                sunflower.setNormalImage();
            }
        } else {
            plant.die();
        }
//        Notify pest controller
//        pestController.setPesticideApplied(true);
        isPesticideApplied = true;

//        System.out.println(pestController.getPesticideApplied());

        // Update the UI (remove pest overlays)
//        viewController.updatePlantPestOverlay(plant);
    }

    public void run() {
//        isRunning = true;
        while (isRunning) {
            try {
                log.info("PESTICIDE thread is running!");
                ViewController.appendLogToFile("Pesticide thread is running", "info");
//                ViewController.appendLogToFile("Pesticide thread is running", "info");
//                System.out.println(this.weatherController.getCurrentWeather());
                // Scan plants for pests
                for (Plants plant : Plants.plantsList) {
                    System.out.println(plant.getPestAttack());
                    if (!plant.getPestAttack().isEmpty()) {
                        // Treat the plant and remove pests
                        this.treatPlant(plant);
                    }
                }
                if (isPesticideApplied) {
                    Platform.runLater(() -> {
                        ViewController.addLogMessage("Spraying pesticide to garden!", "info");
                        ViewController.appendLogToFile("Spraying pesticide to garden!", "info");
                    });
                    // Log the treatment action
                    log.info("Spraying pesticide to garden!");
                }
//                isRunning = false;
                // Wait for cooldown time before the next pesticide application
                Thread.sleep(40000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                isRunning = false;
            }
        }
    }
}
