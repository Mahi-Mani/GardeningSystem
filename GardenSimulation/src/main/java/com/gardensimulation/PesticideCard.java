package com.gardensimulation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PesticideCard extends VBox {
    private ImageView pesticideIcon;  // Image for the weather icon
    private Label pesticideTextLabel; // Text label for weather condition
//    private WeatherController weatherController = new WeatherController();

    // Constructor for the WeatherCard
    public PesticideCard() {
        // Create the weather icon ImageView
        pesticideIcon = new ImageView();
        pesticideIcon.setFitWidth(150);
        pesticideIcon.setFitHeight(150);

        // Create the weather text Label
        pesticideTextLabel = new Label("");
        pesticideTextLabel.setFont(new Font("Arial", 12));
        pesticideTextLabel.setTextFill(Color.BLACK);

        // Add the icon and text to the VBox (card style)
        this.getChildren().addAll(pesticideIcon, pesticideTextLabel);

        // Set alignment and spacing within the card
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
//        this.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
        setStyle("-fx-background-color: #d1fb96; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 2;");
    }

    // Method to update the weather card
    public void updatePesticideCard(Boolean status, String imageUrl) {
        // Update the weather icon
        pesticideIcon.setImage(new Image(imageUrl));

        // Update the pesticide text
        if (status) {
            pesticideTextLabel.setText("Pesticide Effective!");
        } else {
            pesticideTextLabel.setText("No Pesticide!");
        }
    }
}
