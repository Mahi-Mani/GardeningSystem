package com.gardensimulation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SprinklerCard extends VBox {
    private ImageView sprinklerIcon;  // Image for the weather icon
    private Label sprinklerTextLabel; // Text label for weather condition
//    private WeatherController weatherController = new WeatherController();

    // Constructor for the WeatherCard
    public SprinklerCard() {
        // Create the weather icon ImageView
        sprinklerIcon = new ImageView("https://www.groundsguys.com/us/en-us/grounds-guys/_assets/expert-tips/sprinkler-system.webp");
        sprinklerIcon.setFitWidth(100);
        sprinklerIcon.setFitHeight(100);

        // Create the weather text Label
        sprinklerTextLabel = new Label("OFF");
        sprinklerTextLabel.setFont(new Font("Arial", 20));
        sprinklerTextLabel.setTextFill(Color.BLACK);

        // Add the icon and text to the VBox (card style)
        this.getChildren().addAll(sprinklerIcon, sprinklerTextLabel);

        // Set alignment and spacing within the card
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
//        this.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
        setStyle("-fx-background-color: lightblue; -fx-padding: 10; -fx-border-color: black; -fx-border-width: 3;");
    }

    // Method to update the weather card
    public void updateSprinkler(String weatherCondition, String imageUrl) {
        // Update the weather icon
        sprinklerIcon.setImage(new Image(imageUrl));

        // Update the weather text
        sprinklerTextLabel.setText(weatherCondition);
    }
}