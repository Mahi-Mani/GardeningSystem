package com.gardensimulation.Pests;

public class Pest {
    private String name;
    private int row;
    private int col;
    private int severity;
    private String weather;

    public Pest(String name, int severity, String weather) {
        this.name = name;
        this.severity = severity;
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getSeverity() {
        return this.severity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Same reference
        if (obj == null || getClass() != obj.getClass()) return false; // Null or different class
        Pest pest = (Pest) obj;
        return name.equals(pest.name); // Compare pests based on their name
    }

    @Override
    public int hashCode() {
        return name.hashCode(); // Use name's hash code
    }
}
