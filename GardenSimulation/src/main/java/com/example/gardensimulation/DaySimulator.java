package com.example.gardensimulation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class DaySimulator {
    private int dayCounter = 1;
    private Timeline timeline;
    private Text dayDisplay;

    public StackPane getDaySimulatorUI() {
        // Display text for the current day
        dayDisplay = new Text("Day: " + dayCounter);
        dayDisplay.setFont(new Font("Arial", 40));

        // Create a timeline to update the day every 10 seconds
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(10), event -> {
                    dayCounter++; // Increment the day
                    dayDisplay.setText("Day: " + dayCounter); // Update the display
                })
        );
        timeline.setCycleCount(100); // Repeat indefinitely
        timeline.play(); // Start the timeline

        // Layout
        StackPane root = new StackPane(dayDisplay);
        root.setStyle("-fx-background-color: #eef; -fx-padding: 20;");
        return root;
    }

    // Timeline control methods
    public void stopSimulation() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    public void startSimulation() {
        if (timeline != null) {
            timeline.play();
        }
    }
}
