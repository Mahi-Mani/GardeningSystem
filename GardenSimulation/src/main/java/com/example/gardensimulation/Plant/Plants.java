package com.example.gardensimulation.Plant;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Plants {
    private String name;
    private int age;
    private double current_age;
    private int MaxTemp_level;
    private int MinTemp_level;
    private int water_level;
    private int water_requirement;
    private int fertilizer_level;
    private int time_for_watering;
    private int last_watering_time;
    private int days_for_fertilizer;
    private int last_fertilizer_day;
    private boolean isAlive;
    private int row;
    private int col;
    public static ArrayList<Plants> plantsList = new ArrayList<>();
    private static final Logger log = Logger.getLogger(Plants.class.getName());

    //    Parameterized constructor
    public Plants(String name, int age, int water_requirement, int MaxTemp_level, int MinTemp_level, int fertilizer_level, int water_level, int time_for_watering, int last_watering_time, int days_for_fertilizer, int last_fertilizer_day, boolean isAlive, int row, int col) {
        super();
        this.days_for_fertilizer = days_for_fertilizer;
        this.name = name;
        this.age = age;
        this.MaxTemp_level = MaxTemp_level;
        this.MinTemp_level = MinTemp_level;
        this.fertilizer_level = fertilizer_level;
        this.water_level = water_level;
        this.time_for_watering = time_for_watering;
        this.last_watering_time = last_watering_time;
        this.last_fertilizer_day = last_fertilizer_day;
        this.water_requirement = water_requirement;
        this.current_age = 0;
        this.isAlive = true;
        this.row = row;
        this.col = col;
    }

    //    Getters
    public int getLast_fertilizer_day() {
        return last_fertilizer_day;
    }

    public int getWater_requirement() {
        return this.water_requirement;
    }

    public void setLast_fertilizer_day(int last_fertilizer_day) {
        this.last_fertilizer_day = last_fertilizer_day;
    }

    public int getDays_for_fertilizer() {
        return days_for_fertilizer;
    }

    public void setDays_for_fertilizer(int days_for_fertilizer) {
        this.days_for_fertilizer = days_for_fertilizer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void setPlant_type(String plant_type) {
        this.name = plant_type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getCurrent_age() {
        return current_age;
    }

    //    Setters
    public void setCurrent_age(double current_age) {
        if (this.getCurrent_age() <= 100) {

//            myLogger.log(Level.FINE,"Age of " + this.name + " increased to " + (current_age < 100 ? (Math.round(current_age*100.0)/100.0) : 100 ));
            if (this.getCurrent_age() >= 100) {
//                myLogger.log(Level.FINE, this.name + " is fully grown");
            }
        }
        this.current_age = current_age;
    }

    public int getMaxTemp_level() {
        return MaxTemp_level;
    }

    public void setMaxTemp_level(int MaxTemp_level) {
//        myLogger.log(Level.FINE, "Max Temp level has been adjusted for " + this.name);
        this.MaxTemp_level = MaxTemp_level;
    }

    public int getMinTemp_level() {
        return MinTemp_level;
    }

    public void setMinTemp_level(int MinTemp_level) {
//        myLogger.log(Level.FINE, "Min Temp level has been adjusted for " + this.name);
        this.MinTemp_level = MinTemp_level;
    }

    public void setWater_requirement(int water_requirement) {
        this.water_requirement = water_requirement;
    }

    public int getFertilizer_level() {
        return fertilizer_level;
    }

    public void setFertilizer_level(int fertilizer_level) {
        //myLogger.log(Level.FINE, "Fertilizer given to plant " + this.name);
        this.fertilizer_level = fertilizer_level;
    }

    public int getWater_level() {
        return water_level;
    }

    public void setWater_level(int water_level) {
        System.out.println("Current Water level " + this.name + " is " + getWater_level());
        this.water_level = water_level;
    }

    public int getTime_for_watering() {
        return time_for_watering;
    }

    public void setTime_for_watering(int time_for_watering) {
        this.time_for_watering = time_for_watering;
    }

    public int getLast_watering_time() {
        return last_watering_time;
    }

    public void setLast_watering_time(int last_watering_time) {
        this.last_watering_time = last_watering_time;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public void waterThePlant(int amount) {
        this.water_level = this.water_level + amount;
        if (this.water_level > this.water_requirement * 2) {
            if (this.age > 0) {
                this.age = this.age - 20;
            }
        } else if (water_level > water_requirement) {
            if (this.age > 0) {
                this.age = this.age - 10;
            }
        }
        if(this.age <=0 ) {
            this.isAlive = false;
        }
    }

    public void temperatureChange(int temperature) {
        if((temperature > MaxTemp_level) || (temperature < MinTemp_level)) {
            log.severe("Untollerable temperature!");
            log.severe(this.name + "is dead!");
            this.age = 0;
            this.isAlive = false;
        }
    }
}
