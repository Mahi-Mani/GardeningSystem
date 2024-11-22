package com.example.gardensimulation.Plant;

import javafx.scene.image.Image;

public class Tomato extends Vegetables{
    public Tomato(String name,int age, int water_requirement, int maxtemp_level,int mintemp_level,int fertilizer_level, int water_level, int time_for_watering,int last_watering_time,int days_for_fertilizer, int last_fertilizer_day){
        super(name,age,water_requirement,maxtemp_level,mintemp_level,fertilizer_level,water_level, time_for_watering,last_watering_time,days_for_fertilizer, last_fertilizer_day);
    }

    protected Image getPlantImage() {
        // Load the tomato image
        return new Image("https://media.istockphoto.com/id/466175630/photo/tomato-isolated-on-white-background.jpg?s=612x612&w=0&k=20&c=ELzCVzaiRMgiO7A5zQLkuws0N_lvPxrgJWPn7C7BXz0="); // Replace with actual path
    }
}
