package com.example.gardensimulation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.function.Consumer;

public class DaySimulator {
    private int dayCounter = 1;
    private Timeline timeline;
    private Text dayDisplay;

    // Listener to notify external classes about day changes
    private Consumer<Integer> dayChangeListener;

    public StackPane getDaySimulatorUI() {
        // Display text for the current day
        dayDisplay = new Text("Day: " + dayCounter);
        dayDisplay.setFont(new Font("Arial", 40));

        // Create a timeline to update the day every 20 seconds
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(10), event -> {
                    dayCounter++; // Increment the day
                    this.setDayCounter(dayCounter);
                    dayDisplay.setText("Day: " + dayCounter); // Update the display
                    if (dayChangeListener != null) {
                        dayChangeListener.accept(dayCounter); // Notify listener
                    }
                })
        );
        timeline.setCycleCount(100); // Repeat 100 times
        timeline.play(); // Start the timeline

        // Layout
        StackPane root = new StackPane(dayDisplay);
        root.setStyle("-fx-background-color: #eef; -fx-padding: 20;");
        return root;
    }

    public int getDayCounter() {
        return this.dayCounter;
    }

    public void setDayCounter(int dayCounter) {
        this.dayCounter = dayCounter;
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

    // Setter for listener
    public void setDayChangeListener(Consumer<Integer> listener) {
        this.dayChangeListener = listener;
    }
}
