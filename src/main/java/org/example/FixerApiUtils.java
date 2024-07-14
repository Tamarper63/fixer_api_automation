package org.example;

import io.restassured.specification.RequestSpecification;
import org.example.responses.BaseExchangeResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class FixerApiUtils {

    public static RequestSpecification givenFixerApi() {
        return given().queryParam("access_key", ApiConfig.ACCESS_KEY);
    }

    static void validateRatesType(BaseExchangeResponse actualResponse) {
        assertThat(actualResponse.getRates(), instanceOf(Map.class)); // Check if rates is a Map
        actualResponse.getRates().forEach((key, value) -> {
            assertThat(key, instanceOf(String.class));   // Check if map keys are Strings
            assertThat(value, instanceOf(Double.class)); // Check if map values are Doubles
        });
    }
}
