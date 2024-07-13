package org.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class HistoricalResponse extends BaseResponse {
    boolean historical;

    public HistoricalResponse(@JsonProperty("success") boolean success,
                              @JsonProperty("timestamp") long timestamp,
                              @JsonProperty("base") String base,
                              @JsonProperty("date") String date,
                              @JsonProperty("rates") Map<String, Double> rates,
                              @JsonProperty("historical") boolean historical
                              ) {
        super(success, timestamp, base, date, rates);
        this.historical = historical;
    }


}
