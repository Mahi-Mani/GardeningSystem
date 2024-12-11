package com.gardensimulation;

import com.gardensimulation.Plant.Plants;

import java.util.logging.Logger;

public class TemperatureRegulator {
    private static final Logger log = Logger.getLogger(TemperatureRegulator.class.getName());
    private TemperatureController temperatureController;

    public void activate() {
        temperatureController.setCurrentTemperature(TemperatureController.OPTIMUM_TEMPERATURE);
        log.info("Temperature Regulator activated! Setting current Temperature to OPTIMUM!");
        ViewController.addLogMessage("Temperature Regulator activated! Setting current Temperature to OPTIMUM!", "info");
        ViewController.appendLogToFile("Temperature Regulator activated! Setting current Temperature to OPTIMUM! (70)", "info");
        for (Plants plant : Plants.plantsList) {
            plant.setAge(plant.getAge() + 4);
        }
    }
}
