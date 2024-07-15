package org.example.tests;

import io.restassured.RestAssured;
import org.example.ApiConfig;
import org.junit.BeforeClass;

public abstract class BaseApiTest {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = ApiConfig.BASE_URL;
    }
}

