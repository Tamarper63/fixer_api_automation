package org.example;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class FixerApiUtils {

    public static RequestSpecification givenFixerApi() {
        return given().queryParam("access_key", ApiConfig.ACCESS_KEY);
    }
}
