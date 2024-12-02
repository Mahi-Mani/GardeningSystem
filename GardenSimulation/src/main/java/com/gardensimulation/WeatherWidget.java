package com.gardensimulation;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

public class WeatherWidget extends VBox {
    private Text weatherText;
    private ImageView weatherIcon;
    Label weatherLabel;

    public WeatherWidget(String weather) {
        // Initialize components
        weatherIcon = new ImageView();
        weatherText = new Text(weather);
//        weatherText = new Text("Weather: ");
        weatherText.setFont(new Font("Arial", 20));
        weatherLabel = new Label();
//        weatherIcon = new ImageView(new Image("https://www.theschoolrun.com/sites/theschoolrun.com/files/weather_.jpg")); // Default icon

        // Set icon size
        weatherIcon.setFitWidth(100);
        weatherIcon.setFitHeight(100);

        // Align and style
        setAlignment(Pos.CENTER);
        setSpacing(10);
        getChildren().addAll(weatherIcon, weatherText, weatherLabel);
        setStyle("-fx-background-color: lightblue; -fx-padding: 10; -fx-border-color: black; -fx-border-width: 2;");
    }


    public void updateWeather(String condition) {
        System.out.println("Inside update UI weather!");

//        Platform.runLater(() -> {
            System.out.println("Inside platform run later!");
            weatherIcon.setImage(new Image("https://www.theschoolrun.com/sites/theschoolrun.com/files/weather_.jpg"));
            weatherText.setText("Sunny");
            weatherLabel.setText("Heelloooo");
//        });
    }
//        Platform.runLater(() -> {
//            weatherLabel.setText("Suny");
//            System.out.println("Inside update UI weather!");
//            System.out.println(condition);
//            weatherText.setText("Weather: " + condition);
//            switch (condition.toLowerCase()) {
//                case "rainy":
//                    System.out.println("Inside this rainy case!");
//                    weatherIcon = new ImageView(new Image("https://www.creativefabrica.com/wp-content/uploads/2023/11/17/Rainy-clipart-Rain-cloud-weather-icon-Graphics-84326141-1.jpg"));
////                    weatherIcon.setImage(new Image("https://www.creativefabrica.com/wp-content/uploads/2023/11/17/Rainy-clipart-Rain-cloud-weather-icon-Graphics-84326141-1.jpg"));
//                    setStyle("-fx-background-color: lightgray;");
//                    getChildren().addAll(weatherIcon, weatherText);
//                    break;
//                case "cloudy":
//                    System.out.println("Inside this cloudy case!");
//                    weatherIcon.setImage(new Image(""));
//                    setStyle("-fx-background-color: lightblue;");
//                    getChildren().addAll(weatherIcon, weatherText);
//                    break;
//                case "sunny":
//                    System.out.println("Inside this sunny case!");
//                    weatherIcon.setImage(new Image("https://static.vecteezy.com/system/resources/previews/000/538/537/non_2x/sunny-and-cloudy-with-blue-sky-background-vector-illustration.jpg"));
//                    setStyle("-fx-background-color: yellow;");
//                    getChildren().addAll(weatherIcon, weatherText);
//                    break;
//                default:
//                    weatherIcon.setImage(new Image(""));
//                    setStyle("-fx-background-color: white;");
//                    break;
//            }
//        });
//    }

}
