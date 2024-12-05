package com.gardensimulation;

import com.gardensimulation.Plant.Plants;

import java.util.Iterator;
import java.util.logging.Logger;

public class TemperatureController implements Runnable {
    private static final Logger log = Logger.getLogger(TemperatureController.class.getName());
    private static final int MIN_SAFE_TEMPERATURE = 50;
    private static final int MAX_SAFE_TEMPERATURE = 100;
    public static final int OPTIMUM_TEMPERATURE = 70;
    private static int currentTemperature;
    private TemperatureRegulator regulator = new TemperatureRegulator();
    private boolean isRunning = true;

    public TemperatureController(int currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public static int getCurrentTemperature() {
        return currentTemperature;
    }

    public static void setCurrentTemperature(int temperature) {
        currentTemperature = temperature;
    }

    public void checkPlantTempStatus() {
        for (Plants plant : Plants.plantsList) {
            plant.checkDailyTemp(getCurrentTemperature());
        }
    }

    public void adjustTemperature() {

        if (currentTemperature < MIN_SAFE_TEMPERATURE) {
            for (Plants plant : Plants.plantsList) {
                plant.temperatureChange(currentTemperature);
            }
//            Activate heating controller
            log.warning("Current Temperature is below MINIMUM safe temperature!");
            regulator.activate();
        } else if (currentTemperature > MAX_SAFE_TEMPERATURE) {
            for (Plants plant : Plants.plantsList) {
                plant.temperatureChange(currentTemperature);
            }
//            Activate cooling controller
            log.warning("Current Temperature is above MAXIMUM safe temperature!");
            regulator.activate();
        } else {
            log.info("Current Temperature is OPTIMUM! No need to turn on the regulator!");
        }
    }

    @Override
    public void run() {
        log.info("Temperature Controller thread is running!");
        while (isRunning) {
            this.adjustTemperature();
            try {
//                Periodically check every 10 seconds
                Thread.sleep(10000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                isRunning = false;
            }
        }
    }
}
