package org.example.responses;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.example.ApiConfig.ACCESS_KEY;
import static org.example.ApiConfig.LATEST_ENDPOINT;

public class LatestRatesEndpoint {

    public Response getLatestRates() {
        try {
            return RestAssured.given().queryParam("access_key", ACCESS_KEY).get(LATEST_ENDPOINT)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();
        } catch (Exception e) {
            System.err.println("Exception occurred while fetching latest rates: " + e.getMessage());
            throw e;
        }
    }
}