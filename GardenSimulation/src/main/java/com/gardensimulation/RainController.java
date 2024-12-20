package com.gardensimulation;

import com.gardensimulation.Plant.Plants;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class RainController {
    private static final Logger log = Logger.getLogger(RainController.class.getName());
    private final SprinklerController sprinkler = new SprinklerController();
    private TemperatureController temperatureController;
    private int rainfallUnits;
    private final Random random;

    public RainController() {
        random = new Random();
        rainfallUnits = 0;
    }

    public void generateRainfall(List<Plants> plants) {
        rainfallUnits = random.nextInt(20);
        log.info("It's raining!" + rainfallUnits + " units of rain recorded!");
        for(Plants plant: plants) {
            plant.waterThePlant(rainfallUnits);
        }
//        TemperatureController.setCurrentTemperature(TemperatureController.getCurrentTemperature() - 15);
//        log.info("Current Temperature is: " + temperatureController.getCurrentTemperature());
        sprinkler.activateSprinklers(plants);
    }

    public int getRainfallUnits() {
        return rainfallUnits;
    }
}
