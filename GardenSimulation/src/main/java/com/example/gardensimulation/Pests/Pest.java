package com.example.gardensimulation.Pests;

public class Pest {
    private String name;
    private int row;
    private int col;
    private String severity;

    public Pest(String name, String severity) {
        this.name = name;
        this.severity = severity;
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
}
