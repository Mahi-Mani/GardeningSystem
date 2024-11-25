package com.example.gardensimulation;

import com.example.gardensimulation.Plant.Plants;
import com.example.gardensimulation.Plant.TemperatureController;

import java.util.logging.Logger;

public class LifeController implements Runnable {
    private static final Logger log = Logger.getLogger(TemperatureController.class.getName());
    private DaySimulator currentDay = new DaySimulator();
    private boolean isRunning = true;
    private int tempDay = 1;

    public LifeController(DaySimulator daySimulator) {
        daySimulator.setDayChangeListener(day ->
        {
            System.out.println("Event listener");
            for (Plants plant : Plants.plantsList) {
                if (plant.isAlive()) {
                    plant.setAge(plant.getAge() - 1);
                }
                if (plant.getAge() <= 0) {
                    plant.setAlive(false);
                }
            }
        });
    }

    public void adjustPlantHealth() {
        System.out.println("Inside adjust plant health");


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
        while (isRunning) {
            this.adjustPlantHealth();
            try {
//                Periodically check every 10 seconds
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                isRunning = false;
            }
        }
    }
}
