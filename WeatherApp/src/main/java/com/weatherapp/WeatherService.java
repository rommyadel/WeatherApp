package com.weatherapp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherService {
    private static final String API_KEY = "1232b62ed5c7a71a68383100eca93c6a";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    public String getWeather(String city) {
        
        String url = BASE_URL + city + "&appid=" + API_KEY + "&units=imperial";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonData = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonData);

                String weatherDescription = jsonNode.get("weather").get(0).get("description").asText();
                double temperature = jsonNode.get("main").get("temp").asDouble();

                return "Weather: " + weatherDescription + "Temperature: " + temperature + "°F";
            } else {
                return "Error: " + response.message();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Could not retrieve weather data";
        }
    }
}
