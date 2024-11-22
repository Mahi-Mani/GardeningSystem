package com.example.gardensimulation.Plant;

import javafx.scene.image.Image;

public class Orange extends Fruits{
    public Orange(String name,int age, int maxtemp_level,int mintemp_level,int fertilizer_level, int water_level, int time_for_watering,int last_watering_time,int days_for_fertilizer, int last_fertilizer_day){
        super(name,age,maxtemp_level,mintemp_level,fertilizer_level,water_level, time_for_watering,last_watering_time,days_for_fertilizer, last_fertilizer_day);
    }

    protected Image getPlantImage() {
        // Load the tomato image
        return new Image("https://media.istockphoto.com/id/185284489/photo/orange.jpg?s=612x612&w=0&k=20&c=m4EXknC74i2aYWCbjxbzZ6EtRaJkdSJNtekh4m1PspE="); // Replace with actual path
    }
}
