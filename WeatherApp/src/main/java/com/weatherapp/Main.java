package com.weatherapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage){
        //Create UI components 
        Label label = new Label("Enter City: ");
        TextField cityInput = new TextField();
        Button getWeatherButton = new Button("Get Weather");
        Label weatherResult = new Label();

         // Set button action
         getWeatherButton.setOnAction(e -> {
        String city = cityInput.getText();
        WeatherService weatherService = new WeatherService();
        String weatherInfo = weatherService.getWeather(city);
        weatherResult.setText(weatherInfo);
        });

        // Layout
        VBox layout = new VBox(10); // 10 is the spacing between elements
        layout.getChildren().addAll(label, cityInput, getWeatherButton, weatherResult);

        // Scene
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Weather App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
    
    

