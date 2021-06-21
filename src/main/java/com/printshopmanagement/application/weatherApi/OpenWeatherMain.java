package com.printshopmanagement.application.weatherApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherMain {
    @JsonProperty("temp")
    private Double temp;

    @JsonProperty("pressure")
    private Double pressure;

    @JsonProperty("humidity")
    private Double humidity;
}
