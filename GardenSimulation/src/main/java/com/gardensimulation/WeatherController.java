package com.gardensimulation;

import com.gardensimulation.Plant.Plants;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Random;

public class WeatherController {
    //    private WeatherType currentWeather;
    private String currentWeather = "";
    private int temperature;
    private int humidity;
    private Random random = new Random();
    private RainController rainController = new RainController();
    private TemperatureController temperatureController;
    private SprinklerController sprinklerController;
    private WeatherWidget weatherWidget;
    private PestController pestController;
    private String sunnyIcon = "https://www.shutterstock.com/image-photo/orange-sky-sun-clouds-during-260nw-2475701091.jpg";
    private String rainyIcon = "https://centralca.cdn-anvilcms.net/media/images/2019/01/02/images/Rainy_Weather_pix.max-752x423.jpg";
    private String cloudyIcon = "https://t4.ftcdn.net/jpg/05/13/26/73/360_F_513267391_QEmNGeOFLLqrILTnoq21dReUPp5UsoNr.jpg";
    private String icon = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLDFES-GXOy3SjZJptoHS-dx8YWG9Psv8EZg&s";

    public WeatherController() {
        currentWeather = generateRandomWeather();
        weatherWidget = new WeatherWidget(currentWeather);
//        this.setCurrentWeather(currentWeather);
//        this.setWeatherWidget(currentWeather);
        updateTemperatureAndHumidity();
        temperatureController = new TemperatureController(temperature);
        sprinklerController = new SprinklerController();
        pestController = new PestController();
    }

    public String generateRandomWeather() {
        String[] conditions = {"sunny", "cloudy"};
        return conditions[random.nextInt(conditions.length)];
    }

    public void setCurrentWeather(String weather) {
        this.currentWeather = weather;
    }

    public void setWeatherWidget(String weather) {
        if (weather.equalsIgnoreCase("rainy")) {
            this.icon = rainyIcon;
        } else if (weather.equalsIgnoreCase("sunny")) {
            this.icon = sunnyIcon;
        } else if (weather.equalsIgnoreCase("cloudy")) {
            this.icon = cloudyIcon;
        }
    }

    public String getWeatherWidget() {
        return this.icon;
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

    public static String getCapitalized(String str) {
        if (str == null || str.isEmpty()) {
            return str; // Return the original string if it's null or empty
        }
        // Create a new string with the first letter capitalized
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // Simulate weather for the day
    public void simulateDailyWeather() {
        System.out.println("Today's weather: " + currentWeather);
        System.out.println("Temperature: " + temperature + "°F");
        System.out.println("Humidity: " + humidity + "%");
        setCurrentWeather(currentWeather);
        setWeatherWidget(currentWeather);
//        weatherWidget.updateWeather(currentWeather);
//        weatherWidget.requestLayout();

        if ("rainy".equals(currentWeather)) {
            System.out.println("!!!!!!!!!!!!!!RAINY WEATHER SIMULATION");
            simulateRainyDay();
            Platform.runLater(() -> {
                pestController.attackPlan("rainy");
            });
        } else if ("sunny".equals(currentWeather)) {
            System.out.println("!!!!!!!!!!!!!!SUNNY WEATHER SIMULATION");
            simulateSunnyDay();
            Platform.runLater(() -> {
                pestController.attackPlan("sunny");
            });
        } else if ("cloudy".equals(currentWeather)) {
            System.out.println("!!!!!!!!!!!!!!CLOUDY WEATHER SIMULATION");
            simulateCloudyDay();
            Platform.runLater(() -> {
                pestController.attackPlan("cloudy");
            });
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
        sprinklerController.activateSprinklers(Plants.plantsList);
    }

    // Simulate rain happening 4 times randomly during a rainy day
    private void simulateRainyDay() {
        System.out.println("Rain is expected throughout the day.");
        TemperatureController.setCurrentTemperature(temperature);
        System.out.println("Rainy day temp: " + TemperatureController.getCurrentTemperature());
        temperatureController.checkPlantTempStatus();
        temperatureController.adjustTemperature();
        rainController.generateRainfall(Plants.plantsList); // Notify RainController
    }

    // Simulate cloudy day with reduced rain probability
    private void simulateCloudyDay() {
        System.out.println("It's cloudy today. Rain is less likely.");
        if (random.nextInt(10) < 3) { // 30% chance of rain
            System.out.println("Rain started briefly! 5 units of rain recorded");
            PesticideController.isPesticideApplied = false;
            System.out.println("Rain washed the pesticide away!");
            for (Plants plant : Plants.plantsList) {
                plant.waterThePlant(5);// Notify RainController
            }
        } else {
            System.out.println("No rain today despite cloudy weather.");
        }
        sprinklerController.activateSprinklers(Plants.plantsList);
    }

    // Randomize weather condition for the next day
    public void updateWeatherForNextDay() {
        currentWeather = generateRandomWeather();
        updateTemperatureAndHumidity();
    }

    public String getCurrentWeather() {
        return this.currentWeather;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

}
