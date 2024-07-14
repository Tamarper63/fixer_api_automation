package org.example.responses;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ExchangeRatesEndpoint {
    private final RequestSpecification requestSpec;

    private ExchangeRatesEndpoint(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public static ExchangeRatesEndpoint newInstance(RequestSpecification requestSpec) {
        return new ExchangeRatesEndpoint(requestSpec);
    }


    public Response getLatestRates() {
        return requestSpec.get("/latest")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response getHistoricalRates(String date) {
//        RequestSpecification spec = requestSpec
//                .queryParam("historical", true);
        return requestSpec.get("/" + date)
                .then()
                .extract()
                .response();
    }
}
