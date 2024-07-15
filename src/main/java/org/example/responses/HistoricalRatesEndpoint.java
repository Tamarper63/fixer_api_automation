package org.example.responses;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.example.ApiConfig.ACCESS_KEY;

public class HistoricalRatesEndpoint {
    public Response getHistoricalRates(String date) {
        try {
            return RestAssured.given().queryParam("access_key", ACCESS_KEY).get("/" + date)
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            System.err.println("Exception occurred while fetching latest rates: " + e.getMessage());
            throw e;
        }
    }
}
