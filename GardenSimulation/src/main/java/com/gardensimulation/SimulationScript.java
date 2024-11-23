package com.gardensimulation;

public class SimulationScript {
    
    public static void main(String[] args) throws InterruptedException{
        GardenSimulatorAPI api = new GardenSimulatorAPI();
        api.initializeGarden();

        api.temperature(90);
        api.parasite("pest1");
        
        Thread.sleep(1000*GardenSimulatorAPI.DAY_SIMULATION_SECONDS);
        api.parasite("pest2");
        api.rain(5);

        Thread.sleep(1000*GardenSimulatorAPI.DAY_SIMULATION_SECONDS);
        api.parasite("pest1");
        api.parasite("pest2");
        api.temperature(120);
        api.rain(10);

        Thread.sleep(1000*GardenSimulatorAPI.DAY_SIMULATION_SECONDS*100);
    }
}
