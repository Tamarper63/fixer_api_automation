package org.example.mockResponse;

import org.example.models.FixerResponseJson;
import org.example.utils.DateUtils;

import java.util.Map;

public class MockSuccessResponse {
    public static FixerResponseJson createTomorrowMockResponse() {
        return new FixerResponseJson(
                true,
                System.currentTimeMillis(),
                "EUR",
                DateUtils.getTomorrowsDate(),
                Map.of(
                        "USD", 1.636492,
                        "EUR", 1.196476,
                        "CAD", 1.739516
                ),
                false
        );
    }
}
