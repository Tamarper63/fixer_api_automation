package org.example.models;

import org.example.responses.HistoricalResponse;
import org.example.responses.LatestResponse;

import java.time.LocalDate;
import java.util.Map;

public class Consts {
    public static final LatestResponse EXPECTED_LATEST_RESPONSE = LatestResponse.builder()
            .success(true)
            .timestamp(1626078723L)
            .base("EUR")
            .date(LocalDate.now().toString())
            .rates(Map.of("USD", 1.23, "GBP", 0.89, "JPY", 130.5))
            .build();

    public static final HistoricalResponse EXPECTED_HISTORICAL_RESPONSE = HistoricalResponse.builder()
            .success(true)
            .timestamp(1626078723L)
            .historical(true)
            .base("EUR")
            .date("2013-12-24")
            .rates(Map.of("USD", 1.23, "GBP", 0.89, "JPY", 130.5))
            .build();

}
