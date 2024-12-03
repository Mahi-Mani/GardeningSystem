package com.gardensimulation;

import java.util.logging.Logger;

public class TemperatureRegulator {
    private static final Logger log = Logger.getLogger(TemperatureRegulator.class.getName());
    private TemperatureController temperatureController;

    public void activate() {
        temperatureController.setCurrentTemperature(TemperatureController.OPTIMUM_TEMPERATURE);
        log.info("Temperature Regulator activated! Setting current Temperature to OPTIMUM!");
    }
}
