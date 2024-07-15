package org.example.responses;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.example.ApiConfig.ACCESS_KEY;

public class TomorrowPredictionEndpoint {

    public Response getTomorrowRates() {
        try {
            return RestAssured.given().queryParam("access_key", ACCESS_KEY).get("/tomorrow")
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            System.err.println("Exception occurred while fetching tommorow rates: " + e.getMessage());
            throw e;
        }
    }
}

