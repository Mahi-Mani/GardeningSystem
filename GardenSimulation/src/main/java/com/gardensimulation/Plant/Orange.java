package com.gardensimulation.Plant;

import com.gardensimulation.Pests.Pest;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Orange extends Fruits{
    public Orange(String name, int age, int water_requirement, int maxtemp_level, int mintemp_level, int water_level, ArrayList<Pest> parasites, boolean isAlive, int row, int col){
        super(name,age,water_requirement,maxtemp_level,mintemp_level, water_level, parasites, isAlive, row, col);
    }

    public Orange(ArrayList<Pest> currentPests) {
        super(currentPests);
    }

    protected Image getPlantImage() {
        // Load the tomato image
        return new Image("https://backyardcitrustrees.com/cdn/shop/products/Page_Orange_Tree-100_500x500.jpg?v=1578543160"); // Replace with actual path
    }

    protected Image getAttackedImage() {
        return new Image("https://naads.or.ug/wp-content/uploads/2020/11/orange-diseases.jpg"); // Pest-attacked image
    }
}
