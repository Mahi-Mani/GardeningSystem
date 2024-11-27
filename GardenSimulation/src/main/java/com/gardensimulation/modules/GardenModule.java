package com.gardensimulation.modules;

import com.gardensimulation.models.Garden;

public abstract class GardenModule {
     final Garden garden;

     GardenModule(Garden garden){
        this.garden = garden;
     }

     abstract public void run();
}
