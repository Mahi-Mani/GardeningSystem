package com.gardensimulation;

import com.gardensimulation.Plant.Plants;
import com.gardensimulation.models.Plant;
import javafx.application.Platform;

import java.util.Random;

public class WeatherController {
    //    private WeatherType currentWeather;
    private String currentWeather;
    private int temperature;
    private int humidity;
    private Random random = new Random();
    private RainController rainController = new RainController();
    private TemperatureController temperatureController;
    private SprinklerController sprinklerController;
    private WeatherWidget weatherWidget;

    public WeatherController() {
        currentWeather = generateRandomWeather();
        weatherWidget = new WeatherWidget(currentWeather);
        updateTemperatureAndHumidity();
        temperatureController = new TemperatureController(temperature);
        sprinklerController = new SprinklerController();
    }

    public String generateRandomWeather() {
        String[] conditions = {"sunny", "rainy", "cloudy"};
        return conditions[random.nextInt(conditions.length)];
    }

    private void updateTemperatureAndHumidity() {
        switch (currentWeather) {
            case "sunny":
                temperature = 80 + random.nextInt(25);
                humidity = 30 + random.nextInt(30);
                break;
            case "rainy":
                temperature = 45 + random.nextInt(10);
                humidity = 70 + random.nextInt(20);
                break;
            case "cloudy":
                temperature = 60 + random.nextInt(15);
                humidity = 50 + random.nextInt(20);
                break;
        }
    }

    // Simulate weather for the day
    public void simulateDailyWeather() {
        System.out.println("Today's weather: " + currentWeather);
        System.out.println("Temperature: " + temperature + "°F");
        System.out.println("Humidity: " + humidity + "%");
//        weatherWidget.updateWeather(currentWeather);
        weatherWidget.requestLayout();

        if ("rainy".equals(currentWeather)) {
            simulateRainyDay();
        } else if ("sunny".equals(currentWeather)) {
            simulateSunnyDay();
        } else if ("cloudy".equals(currentWeather)) {
            simulateCloudyDay();
        }
    }

    //    Simulate sunny day and notify temperature controller
    private void simulateSunnyDay() {
        System.out.println("Sunny day");
        TemperatureController.setCurrentTemperature(temperature);
        System.out.println(TemperatureController.getCurrentTemperature());
        temperatureController.checkPlantTempStatus();
        temperatureController.adjustTemperature();
        sprinklerController.reduceWaterLevel();
    }

    // Simulate rain happening 4 times randomly during a rainy day
    private void simulateRainyDay() {
        System.out.println("Rain is expected throughout the day.");
        rainController.generateRainfall(Plants.plantsList); // Notify RainController
    }

    // Simulate cloudy day with reduced rain probability
    private void simulateCloudyDay() {
        System.out.println("It's cloudy today. Rain is less likely.");
        if (random.nextInt(10) < 3) { // 30% chance of rain
            System.out.println("Rain started briefly! 5 units of rain recorded");
            for (Plants plant : Plants.plantsList) {
                plant.waterThePlant(5);// Notify RainController
            }
        } else {
            System.out.println("No rain today despite cloudy weather.");
        }
    }

    // Randomize weather condition for the next day
    public void updateWeatherForNextDay() {
        currentWeather = generateRandomWeather();
        updateTemperatureAndHumidity();
    }

    public String getCurrentWeather() {
        return currentWeather;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

}