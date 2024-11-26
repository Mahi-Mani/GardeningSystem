package com.example.gardensimulation.Pests;

public class Pest {
    private String name;
    private int row;
    private int col;
    private String severity;

    public Pest(String name, String severity, int row, int col) {
        this.name = name;
        this.severity = severity;
        this.row = row;
        this.col = col;
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
}
