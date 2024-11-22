package com.example.gardensimulation.Plant;

import javafx.scene.image.Image;

public class Lemon extends Vegetables{
    public Lemon(String name,int age, int maxtemp_level,int mintemp_level,int fertilizer_level, int water_level, int time_for_watering,int last_watering_time,int days_for_fertilizer, int last_fertilizer_day){
        super(name,age,maxtemp_level,mintemp_level,fertilizer_level,water_level, time_for_watering,last_watering_time,days_for_fertilizer, last_fertilizer_day);
    }

    protected Image getPlantImage() {
        // Load the tomato image
        return new Image("https://t4.ftcdn.net/jpg/02/85/95/37/360_F_285953794_Aufu90PPcaOk59DQLSbSMRkLQumniBXi.jpg"); // Replace with actual path
    }
}
