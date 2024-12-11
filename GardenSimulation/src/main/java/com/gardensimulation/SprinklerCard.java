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

    // Constructor for the SprinklerCard
    public SprinklerCard() {
        // Create the sprinkler icon ImageView
        sprinklerIcon = new ImageView();
        sprinklerIcon.setFitWidth(150);
        sprinklerIcon.setFitHeight(150);

        // Create the sprinkler text Label
        sprinklerTextLabel = new Label("OFF");
        sprinklerTextLabel.setFont(new Font("Arial", 20));
        sprinklerTextLabel.setTextFill(Color.BLACK);

        // Add the icon and text to the VBox (card style)
        this.getChildren().addAll(sprinklerIcon, sprinklerTextLabel);

        // Set alignment and spacing within the card
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
//        this.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
        setStyle("-fx-background-color: #d1fb96; -fx-padding: 5; -fx-border-color: black; -fx-border-width: 3;");
    }

    // Method to update the sprinkler card
    public void updateSprinkler(Boolean status) {
        // Update the sprinkler icon
        if (status) {
            sprinklerIcon.setImage(new Image("https://media1.giphy.com/media/7Xp6WZXFADXkkP7z9X/giphy.gif?cid=6c09b9529330fin1czs6t2w4xu0tkphyk6eibycol4nbqegs&ep=v1_internal_gif_by_id&rid=giphy.gif&ct=g"));
            sprinklerTextLabel.setText("ON");
        } else {

            sprinklerIcon.setImage(new Image("https://www.groundsguys.com/us/en-us/grounds-guys/_assets/expert-tips/sprinkler-system.webp"));
            // Update the sprinkler text
            sprinklerTextLabel.setText("OFF");
        }
    }
}