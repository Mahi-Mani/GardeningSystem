package com.example.gardensimulation.Plant;

import com.example.gardensimulation.TemperatureRegulator;

import java.util.logging.Logger;

public class TemperatureController {
    private static final Logger log = Logger.getLogger(TemperatureController.class.getName());
    private static final int MIN_SAFE_TEMPERATURE = 30;
    private static final int MAX_SAFE_TEMPERATURE = 50;
    public static final int OPTIMUM_TEMPERATURE = 45;
    private int currentTemperature;
    private TemperatureRegulator regulator = new TemperatureRegulator();

    public TemperatureController(int currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public int getCurrentTemperature() {
        return this.currentTemperature;
    }

    public void setCurrentTemperature(int temperature) {
        this.currentTemperature = temperature;
    }

    public void adjustTemperature() {
        if(this.currentTemperature < MIN_SAFE_TEMPERATURE) {
//            Activate heating controller
            log.warning("Current Temperature is below MINIMUM safe temperature!");
            regulator.activate();
        } else if(this.currentTemperature > MAX_SAFE_TEMPERATURE) {
//            Activate cooling controller
            log.warning("Current Temperature is above MAXIMUM safe temperature!");
            regulator.activate();
        }
    }
}
