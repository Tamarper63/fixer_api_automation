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
public class LatestResponse extends BaseResponse {

    public LatestResponse(
            @JsonProperty("success") boolean success,
            @JsonProperty("timestamp") long timestamp,
            @JsonProperty("base") String base,
            @JsonProperty("date") String date,
            @JsonProperty("rates") Map<String, Double> rates) {
        super(success, timestamp, base, date, rates);
    }
}
