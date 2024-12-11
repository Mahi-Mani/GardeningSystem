package com.gardensimulation.Plant;

import com.almasb.fxgl.core.View;
import com.gardensimulation.LifeController;
import com.gardensimulation.Pests.Pest;
import com.gardensimulation.SprinklerController;
import com.gardensimulation.ViewController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class Plants {
    private String name;
    private int age;
    private double current_age;
    private int MaxTemp_level;
    private int MinTemp_level;
    private int water_level;
    private int water_requirement;
    private ArrayList<Pest> parasites;
    private ArrayList<Pest> currentPests1;
    private ArrayList<String> currentPests;
    private boolean isAlive;
    private int row;
    private int col;
    public static final List<Plants> plantsList = Collections.synchronizedList(new ArrayList<>());
    private static final Logger log = Logger.getLogger(Plants.class.getName());
    private LifeController life = new LifeController();
    SprinklerController sprinklerController = new SprinklerController();

    //    Parameterized constructor
    public Plants(String name, int age, int water_requirement, int MaxTemp_level, int MinTemp_level, int water_level, ArrayList<Pest> parasites, boolean isAlive, int row, int col) {
        super();
        this.name = name;
        this.age = age;
        this.MaxTemp_level = MaxTemp_level;
        this.MinTemp_level = MinTemp_level;
        this.water_level = water_level;
        this.water_requirement = water_requirement;
        this.current_age = 0;
        this.isAlive = isAlive;
        this.row = row;
        this.col = col;
        this.parasites = parasites;
        this.currentPests = new ArrayList<>();
    }

    public Plants(ArrayList<Pest> currentPests) {
        this.currentPests1 = currentPests;
    }

    //    Getters

    public int getWater_requirement() {
        return this.water_requirement;
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

    public int getWater_level() {
        return water_level;
    }

    public void setWater_level(int water_level) {
        this.water_level = water_level;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public ArrayList<Pest> getParasites() {
        return this.parasites;
    }

    public ArrayList<String> getPestAttack() {
        return currentPests;
    }

    public void addPest(String pest) {
        currentPests.add(pest);
    }

    public void removePests() {
        currentPests.clear();
    }

    public void die() {
        this.setAlive(false);
        this.age = 0;
        log.severe(this.getName() + " at Row: " + this.getRow() + " Col: " + this.getCol() + " is Dead!");
        ViewController.appendLogToFile(this.getName() + " at Row: " + this.getRow() + " Col: " + this.getCol() + " is Dead!", "severe");
        life.removePlantFromGrid(this.getRow(), this.getCol());
//        synchronized (plantsList) {
//            plantsList.removeIf(plant -> plant.equals(this));
//        }
    }

    public void waterThePlant(int amount) {
        this.water_level = this.water_level + amount;
        if (this.water_level > this.water_requirement * 2) {
            log.severe("More water, " + this.getName() + " age is reduced by 10 units!");
            ViewController.addLogMessage("More water, " + this.getName() + " age is reduced by 10 units!", "severe");
            ViewController.appendLogToFile("More water, " + this.getName() + " age is reduced by 10 units!", "severe");
            if (this.age > 0) {
                this.age = this.age - 10;
            }
        } else if (water_level > water_requirement) {
            if (this.age > 0) {
                this.age = this.age - 3;
            }
        }
        if (this.age <= 0) {
            log.severe("Due to more water, " + this.getName() + " is dying!");
            ViewController.addLogMessage("Due to more water, " + this.getName() + " is dying!", "severe");
            ViewController.appendLogToFile("Due to more water, " + this.getName() + " is dying!", "severe");
            this.isAlive = false;
            this.setAge(0);
        }
    }

    public void dryThePlant(int amount) {
        this.water_level = this.water_level - amount;
        if (this.water_level < this.water_requirement) {
            log.warning("Today's temperature caused reduction in " + this.getName() + " water level! Age reduced by 2 units");
            ViewController.addLogMessage("Today's temperature caused reduction in " + this.getName() + " water level! Age reduced by 2 units", "warn");
            ViewController.appendLogToFile("Today's temperature caused reduction in " + this.getName() + " water level! Age reduced by 2 units", "warn");
            if (this.age > 0) {
                this.age = this.age - 5;
            }
        }
        if (this.age <= 0) {
            this.die();
        }
    }

    public void temperatureChange(int temperature) {
        dryThePlant(15);
        if ((temperature > MaxTemp_level) || (temperature < MinTemp_level)) {
            log.severe("Untolerable temperature! Affected " + this.getName() + " health by 10 units");
            ViewController.addLogMessage("Untolerable temperature! Affected " + this.getName() + " health by 10 units", "severe");
            ViewController.appendLogToFile("Untolerable temperature! Affected " + this.getName() + " health by 10 units", "severe");
            if (this.age > 0) {
                this.setAge(this.getAge() - 20);
            }

            if (this.age <= 0) {
                ViewController.addLogMessage("Plant " + this.getName() + " died due to untolerable temperature!", "severe");
                this.die();
            }
        }
    }

    public void checkDailyTemp(int temperature) {
        int diff1 = Math.abs(temperature - this.getMinTemp_level());
        int diff2 = Math.abs(temperature - this.getMaxTemp_level());
        int tempDiff = Math.min(diff1, diff2);

        if (tempDiff < 10) {
            log.warning("Today's temperature affected aging of " + this.getName() + " by 2 units!");
            ViewController.appendLogToFile("Today's temperature affected aging of " + this.getName() + " by 2 units!", "warn");
            ViewController.addLogMessage("Today's temperature affected aging of " + this.getName() + " by 2 units!", "warn");
            if (this.age > 0) {
                this.setAge(this.getAge() - 5);
            }
        }
        if (this.age < 0) {
            this.die();
        }
    }
}
