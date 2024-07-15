package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FixerErrorResponse {
    private boolean success;
    private Error error;

    @JsonCreator
    public FixerErrorResponse(
            @JsonProperty("success") boolean success,
            @JsonProperty("error") Error error) {
        this.success = success;
        this.error = error;
    }

    @Data
    public static class Error {
        private int code;
        private String type;
        private String info;

        @JsonCreator
        public Error(
                @JsonProperty("code") int code,
                @JsonProperty("type") String type,
                @JsonProperty("info") String info) {
            this.code = code;
            this.type = type;
            this.info = info;
        }
    }
}
