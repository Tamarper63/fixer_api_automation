package org.example.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class BaseExchangeResponse {
    private boolean success;
    private long timestamp;
    private String base;
    private String date;
    private Map<String, Double> rates;

    @Builder.Default
    private boolean historical = false;

    @JsonCreator
    public BaseExchangeResponse(
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
