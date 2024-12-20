package com.gardensimulation.Plant;

import com.gardensimulation.Pests.Pest;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Vegetables extends Plants{
    ImageView plantView;

    public Vegetables(String name, int age, int water_requirement, int maxtemp_level, int mintemp_level, int fertilizer_level, int water_level, int time_for_watering, int last_watering_time, int days_for_fertilizer, int last_fertilizer_day, ArrayList<Pest> parasites, boolean isAlive, int row, int col){
        super(name,age,water_requirement,maxtemp_level,mintemp_level,fertilizer_level,water_level, time_for_watering,last_watering_time,days_for_fertilizer, last_fertilizer_day, parasites,true, row, col);
        plantView = new ImageView();
        plantView.setFitWidth(80);
        plantView.setFitHeight(80);
        plantView.setImage(getPlantImage());
    }

    public Vegetables(ArrayList<Pest> currentPests) {
        super(currentPests);
    }

    // Abstract method to get the specific plant image
    protected abstract Image getPlantImage();

    //    Getter for imageView
    public ImageView getPlantView() {
        return plantView;
    }

    // Method to set the image when the plant is attacked
    public void setAttackedImage() {
        this.plantView.setImage(getAttackedImage());
    }

    protected abstract Image getAttackedImage();

    // Method to reset to normal image (after pesticide use or pest removal)
    public void setNormalImage() {
        this.plantView.setImage(getPlantImage());
    }
}
