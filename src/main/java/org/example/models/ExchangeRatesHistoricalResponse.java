package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder  // Generates a builder pattern for the class
@Data
public class ExchangeRatesHistoricalResponse {
    boolean success;
    long timestamp;
    boolean historical;
    String base;
    String date;
    Map<String, Double> rates;

    @JsonCreator
    public ExchangeRatesHistoricalResponse(
            @JsonProperty("success") boolean success,
            @JsonProperty("timestamp") long timestamp,
            @JsonProperty("historical") boolean historical,
            @JsonProperty("base") String base,
            @JsonProperty("date") String date,
            @JsonProperty("rates") Map<String, Double> rates) {
        // Method to override the base currency
        this.success = success;
        this.timestamp = timestamp;
        this.base = base;
        this.date = date;
        this.rates = rates;
        this.historical = historical;
    }

}
