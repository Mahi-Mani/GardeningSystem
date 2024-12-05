package com.gardensimulation;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.effect.ColorAdjust;
import javafx.util.Duration;

public class SprinklerCard extends VBox {
    private ImageView sprinkerIcon;  // Image for the weather icon
    private Label waterTextLabel; // Text label for weather condition

    private String sprinklingImage = "https://img.freepik.com/free-vector/realistic-sprinkler-illustration_23-2150317300.jpg";
    private ColorAdjust colorAdjust; // To adjust brightness

    private PauseTransition pause; // PauseTransition for handling sprinkler auto-off

    // Constructor for the WeatherCard
    public SprinklerCard() {
        // Create the weather icon ImageView
        sprinkerIcon = new ImageView();
        sprinkerIcon.setFitWidth(100);
        sprinkerIcon.setFitHeight(100);
        sprinkerIcon.setImage(new Image(sprinklingImage));
        // Create ColorAdjust for dimming/brightening
        colorAdjust = new ColorAdjust();
        sprinkerIcon.setEffect(colorAdjust);
        setSprinklerState(false);

        waterTextLabel = new Label("");
        // Create the weather text Label
        waterTextLabel.setText("Sprinkler OFF");
        waterTextLabel.setFont(new Font("Arial", 20));
        waterTextLabel.setTextFill(Color.BLACK);

        // Add the icon and text to the VBox (card style)
        this.getChildren().addAll(sprinkerIcon, waterTextLabel);

        // Set alignment and spacing within the card
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        setStyle("-fx-background-color: lightblue; -fx-padding: 10; -fx-border-color: black; -fx-border-width: 2;");
    }

    // Method to update the weather card and turn on the sprinkler
    public void waterSprinkled(int waterLevel) {
        // Update the weather text
        waterTextLabel.setText("Sprinkler ON \nWater Levels: " + waterLevel);
        // Turn on the sprinkler
        setSprinklerState(true);

        // Cancel any existing pause transition (if it was already active)
        if (pause != null && pause.getStatus() == javafx.animation.Animation.Status.RUNNING) {
            pause.stop(); // Stop the previous pause if it's still running
        }

        // Automatically turn off the sprinkler after 10 seconds
        pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> {
            setSprinklerState(false);
            waterTextLabel.setText("Sprinkler OFF");
        });  // Turn off the sprinkler
        pause.play(); // Start the new pause
    }

    // Method to turn the sprinkler on or off (dim or brighten)
    public void setSprinklerState(boolean isOn) {
        if (isOn) {
            // Brighten the image when the sprinkler is "On"
            colorAdjust.setBrightness(0); // Normal brightness
        } else {
            // Dim the image when the sprinkler is "Off"
            colorAdjust.setBrightness(-0.7); // Dim the image (adjust value as needed)
        }
    }
}
