package com.gardensimulation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.gardensimulation.models.Garden;
import com.gardensimulation.models.Plant;
import com.gardensimulation.modules.PestModule;
import com.gardensimulation.modules.TemperatureModule;
import com.gardensimulation.modules.WateringModule;

public class GardenSimulatorAPI {
    Garden garden;
    WateringModule wateringModule;
    PestModule pestModule;
    TemperatureModule temperatureModule;
    boolean isInitialized;
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    // Automated modules task;
    ScheduledFuture<?> scheduledTask;
    Map<String, Plant> plantTypes = new HashMap<>();

    GardenSimulatorAPI() {
        isInitialized = false;
    }

    public Map<String, Object> getPlants() {
        Map<String,Object> plantsInfo = new HashMap<>();
        if(!isInitialized) {
            return plantsInfo;
        }
        List<String> plantNames = new ArrayList<>();
        List<Integer> requiredWaterLevel = new ArrayList<>();
        List<List<String>> pests = new ArrayList<>();

        for (Plant plant: plantTypes.values()) {
            plantNames.add(plant.getName());
            requiredWaterLevel.add(plant.getWaterRequirement());
            pests.add(new ArrayList<>(plant.getParasites()));
        }
        return plantsInfo;
    }

    void initializeGarden() {
        // Load from Config
        Properties properties = loadConfig("config.properties");
        int rows = Integer.valueOf(properties.getProperty("garden.rows"));
        int cols = Integer.valueOf(properties.getProperty("garden.columns"));
        // Load plant Types from config
        plantTypes  =  loadPlantTypes(properties);

        garden = new Garden(rows, cols);
        // Add intial plants.
        Map<Integer, Plant> initialPlants = loadInitialPlants(properties);
        for (int gridNumber: initialPlants.keySet()) {
            garden.addPlant(initialPlants.get(gridNumber), gridNumber);
        }

        temperatureModule =  new TemperatureModule(garden);
        pestModule = new PestModule(garden);
        wateringModule = new WateringModule(garden);

        startAutomatedModules();
        isInitialized = true;
    }

    void rain(int water) {
        wateringModule.addRain(water);
    }

    void temperature(int temperature) {
        temperatureModule.setCurrentTemperature(temperature);
    }

    void startAutomatedModules() {
            Runnable task = () -> {
                temperatureModule.run();
                pestModule.run();
                wateringModule.run();
            };
            scheduledTask = scheduler.scheduleAtFixedRate(task, 1, 60, TimeUnit.MINUTES);
    }

    void stopSimulation() {
        garden = null;
        temperatureModule = null;
        pestModule = null;
        wateringModule = null;
        scheduledTask.cancel(true);
        isInitialized = false;
    }

    Properties loadConfig(String path) {
        try (InputStream input = GardenSimulatorAPI.class.getClassLoader().getResourceAsStream(path)) {
            Properties props = new Properties();
            props.load(input);
            return props;
        } catch (IOException ex) {
            throw new RuntimeException("Unable to read the config file");
        }
    }

    Map<String, Plant> loadPlantTypes(Properties prop) {
          // Parse plants
          String[] plants = prop.getProperty("plants").split(",");

          // Parse water requirements
          String[] waterRequirementsStr = prop.getProperty("waterRequirement").split(",");
          int[] waterRequirements = Arrays.stream(waterRequirementsStr)
                                          .mapToInt(Integer::parseInt)
                                          .toArray();

          // Parse parasites
          String[] parasiteGroups = prop.getProperty("parasites").split(";");
          List<Set<String>> parasites = new ArrayList<>();
          for (String group : parasiteGroups) {
              parasites.add(new HashSet<>(Arrays.asList(group.split("\\|"))));
          }

          // Create Plant objects
          Map<String, Plant> plantMap = new HashMap<>();
          for (int i = 0; i < plants.length; i++) {
              Plant plant = new Plant(plants[i], waterRequirements[i], parasites.get(i));
              plantMap.put(plant.getName(), plant);
          }

          return plantMap;
    }
    
    Map<Integer, Plant> loadInitialPlants(Properties prop) {
        Map<Integer, Plant> plants = new HashMap<>();
        
        for (String key : prop.stringPropertyNames()) {
            if (key.startsWith("initialplants.")) {
                String[] values = prop.getProperty(key).split(",");

                String plantName = values[0].trim();
                int gridNumber = Integer.parseInt(values[1].trim());

                Plant plantType = plantTypes.get(plantName);
                if(plantType == null) {
                    System.out.println(plantName + " doesn't exist");
                }
                plants.put(gridNumber, new Plant(plantType.getName(), plantType.getWaterRequirement(), plantType.getParasites()));
            }
        }
        return plants;
    }

    // public static void main(String[] args) throws InterruptedException{
    //     new GardenSimulatorAPI().initializeGarden();

    //     while(true){
    //         Thread.sleep(100000);
    //     }
    // }
}
