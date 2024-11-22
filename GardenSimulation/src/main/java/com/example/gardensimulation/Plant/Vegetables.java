package com.example.gardensimulation.Plant;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Vegetables extends Plants{
    ImageView plantView;

    public Vegetables(String name,int age, int maxtemp_level,int mintemp_level,int fertilizer_level, int water_level, int time_for_watering,int last_watering_time,int days_for_fertilizer, int last_fertilizer_day){
        super(name,age,maxtemp_level,mintemp_level,fertilizer_level,water_level, time_for_watering,last_watering_time,days_for_fertilizer, last_fertilizer_day);
        plantView = new ImageView();
        plantView.setFitWidth(80);
        plantView.setFitHeight(80);
        plantView.setImage(getPlantImage());}

    // Abstract method to get the specific plant image
    protected abstract Image getPlantImage();

    //    Getter for imageView
    public ImageView getPlantView() {
        return plantView;
    }
}
