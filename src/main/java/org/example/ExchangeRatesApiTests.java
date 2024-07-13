package org.example;

import io.restassured.response.Response;
import org.example.models.ExchangeRatesErrorResponse;
import org.example.models.ExchangeRatesLatestResponse;
import org.example.responses.ExchangeRatesEndpoint;
import org.example.responses.HistoricalResponse;
import org.example.responses.LatestResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.example.ApiConfig.HISTORICAL_RATES_ENDPOINT;
import static org.example.ApiConfig.LATEST_ENDPOINT;
import static org.example.models.Consts.EXPECTED_LATEST_RESPONSE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ExchangeRatesApiTests extends BaseApiTest {

    private static ExchangeRatesEndpoint exchangeRatesEndpoint;

    @BeforeClass
    public static void setupExchangeRatesEndpoint() {
        exchangeRatesEndpoint = ExchangeRatesEndpoint.builder(requestSpec);
    }


    @Test
    public void validateEmptyApiKeyResponse() {
        ExchangeRatesErrorResponse.Error error = new ExchangeRatesErrorResponse.Error(101, "missing_access_key", "You have not supplied an API Access Key. [Required format: access_key=YOUR_ACCESS_KEY]");
        ExchangeRatesErrorResponse expectedErrorResponse = ExchangeRatesErrorResponse.builder()
                .success(false)
                .error(error)
                .build();

        // Perform the actual API request without API key
        Response response = given()
                .queryParam("access_key", "") // Provide an empty API key or remove this line to test without key
                .when()
                .get(HISTORICAL_RATES_ENDPOINT);

        // Deserialize the actual API error response
        ExchangeRatesErrorResponse actualErrorResponse = response.as(ExchangeRatesErrorResponse.class);

        // Validate error code, type, and description
        assertThat(actualErrorResponse.getError().getCode(), equalTo(expectedErrorResponse.getError().getCode()));
        assertThat(actualErrorResponse.getError().getInfo(), equalTo(expectedErrorResponse.getError().getInfo()));
        assertThat(actualErrorResponse.getError().getType(), equalTo(expectedErrorResponse.getError().getType()));

    }

    @Test
    public void exchangeRatesByDefaultRelativeEUR() {
        Response response = given()
                .queryParam("access_key", ApiConfig.ACCESS_KEY)
                .when()
                .get(LATEST_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Assert "base" currency is EUR
        String baseCurrency = response.jsonPath().getString("base");
        assertThat(baseCurrency, equalTo("EUR"));
    }

    @Test
    public void validateAllParamsTypeResponse() {
        // Perform the actual API request
        Response response = given().queryParam("access_key", ApiConfig.ACCESS_KEY)
                .when()
                .get(LATEST_ENDPOINT)
                .then()
                .statusCode(200) // Validate the response status code is 200
                .extract()
                .response();

        ExchangeRatesLatestResponse actualResponse = response.as(ExchangeRatesLatestResponse.class);

        // Validate types of fields against expected types
        assertThat(actualResponse.isSuccess(), instanceOf(Boolean.class)); // Check if isSuccess() returns Boolean
        assertThat(actualResponse.getBase(), instanceOf(String.class));    // Check if getBase() returns String
        assertThat(actualResponse.getDate(), instanceOf(String.class));    // Check if getDate() returns String
        assertThat(actualResponse.getRates(), instanceOf(java.util.HashMap.class)); // Check if getRates() returns a HashMap

    }

    @Test
    public void validateCurrentDateParams() {

        Response response = given().queryParam("access_key", ApiConfig.ACCESS_KEY)
                .when()
                .get(LATEST_ENDPOINT)
                .then()
                .statusCode(200) // Validate the response status code is 200
                .extract()
                .response();

        ExchangeRatesLatestResponse actualResponse = response.as(ExchangeRatesLatestResponse.class);
        assertThat(actualResponse.getDate(), equalTo(EXPECTED_LATEST_RESPONSE.getDate()));
    }
}


