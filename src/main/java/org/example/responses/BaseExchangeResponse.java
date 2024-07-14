package org.example.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;
@AllArgsConstructor
@Data
//@Builder
public class BaseExchangeResponse {

   boolean success;
  long timestamp;
    @JsonProperty("base") String base;
    @JsonProperty("date") String date;
    @JsonProperty("rates") Map<String, Double> rates;
    @JsonProperty("historical") boolean historical;


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
