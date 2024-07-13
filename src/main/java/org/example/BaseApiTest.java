package org.example;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.example.responses.ExchangeRatesEndpoint;
import org.junit.BeforeClass;

public abstract class BaseApiTest {
    protected static RequestSpecification requestSpec;
    protected static ExchangeRatesEndpoint exchangeRatesEndpoint;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = ApiConfig.BASE_URL;
        requestSpec = RestAssured.given().log().all();
        exchangeRatesEndpoint = ExchangeRatesEndpoint.builder(requestSpec);
    }
}