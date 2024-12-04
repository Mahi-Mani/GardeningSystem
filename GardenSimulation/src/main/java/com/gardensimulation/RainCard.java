package com.gardensimulation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class RainCard extends VBox {
    private ImageView rainIcon;  // Image for the weather icon
    private Label rainTextLabel; // Text label for weather condition
//    private WeatherController weatherController = new WeatherController();

    // Constructor for the WeatherCard
    public RainCard() {
        // Create the weather icon ImageView
        rainIcon = new ImageView();
        rainIcon.setFitWidth(100);
        rainIcon.setFitHeight(100);

        // Create the weather text Label
        rainTextLabel = new Label("");
        rainTextLabel.setFont(new Font("Arial", 20));
        rainTextLabel.setTextFill(Color.BLACK);

        // Add the icon and text to the VBox (card style)
        this.getChildren().addAll(rainIcon, rainTextLabel);

        // Set alignment and spacing within the card
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
//        this.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
        setStyle("-fx-background-color: lightblue; -fx-padding: 10; -fx-border-color: black; -fx-border-width: 2;");
    }

    // Method to update the weather card
    public void updateRain() {
        // Update the weather icon
        rainIcon.setImage(new Image("https://i.pinimg.com/originals/b1/f3/f1/b1f3f164e96c1b8b203fd7d34640eb57.gif"));

        // Update the weather text
        rainTextLabel.setText("Raining!");
        System.out.println("Inisde updaterainUI update rain");
    }
}