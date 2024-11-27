package com.gardensimulation;


import javafx.application.Application;
import javafx.scene.Scene;

import com.almasb.fxgl.core.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        ViewController controller = new ViewController();
        // Set up the scene and show the stage
        Scene scene = new Scene(controller.createContent(), 800, 700);
        primaryStage.setTitle("Automated Gardening System");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}