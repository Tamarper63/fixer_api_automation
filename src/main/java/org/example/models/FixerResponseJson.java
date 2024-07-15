package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class FixerResponseJson {

    boolean success;
    long timestamp;
    String base;
    String date;
    Map<String, Double> rates;
    boolean historical;

    @JsonCreator
    public FixerResponseJson(
            @JsonProperty("success") boolean success,
            @JsonProperty("timestamp") long timestamp,
            @JsonProperty("base") String base,
            @JsonProperty("date") String date,
            @JsonProperty("rates") Map<String, Double> rates,
            @JsonProperty("historical") boolean historical) {
        this.success = success;
        this.timestamp = timestamp;
        this.base = base;
        this.date = date;
        this.rates = rates;
        this.historical = historical;
    }
}
