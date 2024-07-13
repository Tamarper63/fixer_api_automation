package org.example.responses;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ExchangeRatesEndpoint {
    private final RequestSpecification requestSpec;
    private boolean historical;

    private ExchangeRatesEndpoint(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public static ExchangeRatesEndpoint builder(RequestSpecification requestSpec) {
        return new ExchangeRatesEndpoint(requestSpec);
    }

    public ExchangeRatesEndpoint withHistorical(boolean historical) {
        this.historical = historical;
        return this;
    }

    public Response getLatestRates() {
        return requestSpec.get("/latest")
                .then()
                .extract()
                .response();
    }

    public Response getHistoricalRates() {
        return requestSpec.get("/historical")
                .then()
                .extract()
                .response();
    }
}
