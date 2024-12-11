package com.gardensimulation.Plant;

import com.gardensimulation.Pests.Pest;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Lemon extends Vegetables{
    public Lemon(String name, int age, int water_requirement, int maxtemp_level, int mintemp_level,int water_level,ArrayList<Pest> parasites, boolean isAlive, int row, int col){
        super(name,age,water_requirement,maxtemp_level,mintemp_level,water_level, parasites, isAlive, row, col);
    }

    public Lemon(ArrayList<Pest> currentPests) {
        super(currentPests);
    }

    protected Image getPlantImage() {
        // Load the tomato image
        return new Image("https://www.bhg.com/thmb/sMcFJE8aJdtudLVwlVduvvr64f0=/1244x0/filters:no_upscale():strip_icc()/lemon-tree-pot-5de2d1aa-145020d2490c464396d8b982219dce95.jpg"); // Replace with actual path
    }

    protected Image getAttackedImage() {
        return new Image("https://www.shutterstock.com/image-photo/lemon-fruits-affected-by-pest-600nw-2229584175.jpg"); // Pest-attacked image
    }
}
