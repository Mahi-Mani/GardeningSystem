package com.gardensimulation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WeatherCard extends VBox {
    private ImageView weatherIcon;  // Image for the weather icon
    private Label weatherTextLabel; // Text label for weather condition
//    private WeatherController weatherController = new WeatherController();

    // Constructor for the WeatherCard
    public WeatherCard() {
        // Create the weather icon ImageView
        weatherIcon = new ImageView();
        weatherIcon.setFitWidth(150);
        weatherIcon.setFitHeight(150);

        // Create the weather text Label
        weatherTextLabel = new Label("");
        weatherTextLabel.setFont(new Font("Arial", 20));
        weatherTextLabel.setTextFill(Color.BLACK);

        // Add the icon and text to the VBox (card style)
        this.getChildren().addAll(weatherIcon, weatherTextLabel);

        // Set alignment and spacing within the card
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
//        this.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
        setStyle("-fx-background-color: #d1fb96; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 2;");
    }

    // Method to update the weather card
    public void updateWeather(String weatherCondition, String imageUrl) {
        // Update the weather icon
        weatherIcon.setImage(new Image(imageUrl));

        // Update the weather text
        weatherTextLabel.setText(weatherCondition);
    }
}
