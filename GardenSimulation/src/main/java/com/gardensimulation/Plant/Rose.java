package com.gardensimulation.Plant;

import com.gardensimulation.Pests.Pest;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Rose extends Flowers {
    public Rose(String name, int age, int water_requirement, int maxtemp_level, int mintemp_level, int water_level, ArrayList<Pest> parasites, boolean isAlive, int row, int col) {
        super(name, age, water_requirement, maxtemp_level, mintemp_level, water_level, parasites, isAlive, row, col);
    }

    public Rose(ArrayList<Pest> currentPests) {
        super(currentPests);
    }

    protected Image getPlantImage() {
        // Load the Rose image
        return new Image("https://www.shutterstock.com/shutterstock/videos/9492146/thumb/1.jpg?ip=x480"); // Replace with actual path
    }

    protected Image getAttackedImage() {
        return new Image("https://www.gardentech.com/-/media/project/oneweb/gardentech/images/blog/protecting-roses-from-aphids-and-other-common-pests/protect-roses-japanese.jpg"); // Pest-attacked image
    }
}
