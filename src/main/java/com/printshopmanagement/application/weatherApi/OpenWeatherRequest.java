package com.printshopmanagement.application.weatherApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.printshopmanagement.application.config.ConfigData;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OpenWeatherRequest {
    private final ConfigData configData;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public OpenWeatherRequest(ConfigData configData) {
        this.configData = configData;
    }

    public OpenWeatherPOJO OpenWeatherConnnect() throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()){
            HttpGet request = new HttpGet("https://api.openweathermap.org/data/2.5/weather?id=" + configData.getCityIdKrakow() + "&appid=" + configData.getWeatherApiKey() + "&units=metric");

            return client.execute(request, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), OpenWeatherPOJO.class));
        }
    }

}
