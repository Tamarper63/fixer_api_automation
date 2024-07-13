package org.example;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.models.ExchangeRatesHistoricalResponse;
import org.example.responses.BaseExchangeResponse;
import org.example.responses.ExchangeRatesEndpoint;
import org.example.responses.HistoricalResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.example.ApiConfig.HISTORICAL_RATES_ENDPOINT;
import static org.example.FixerApiUtils.givenFixerApi;
import static org.example.models.Consts.EXPECTED_HISTORICAL_RESPONSE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExchangeRatesHistoricalTests extends BaseApiTest {

    private static ExchangeRatesEndpoint exchangeRatesEndpoint;

    @BeforeClass
    public static void setupExchangeRatesEndpoint() {
        exchangeRatesEndpoint = ExchangeRatesEndpoint.builder(requestSpec);
    }


    @Test
    public void validateHistoricalEqualTrue() {
        HistoricalResponse exchangeRatesHistoricalResponse = EXPECTED_HISTORICAL_RESPONSE;

        // Perform the actual API request
        Response response = givenFixerApi()
                .when()
                .get(HISTORICAL_RATES_ENDPOINT)
                .then()
                .statusCode(200) // Validate the response status code is 200
                .extract()
                .response();

        ExchangeRatesHistoricalResponse actualResponse = response.as(ExchangeRatesHistoricalResponse.class);

        assertThat(actualResponse.isHistorical(), equalTo(exchangeRatesHistoricalResponse.isHistorical()));
    }

    @Test
    public void validateHistoricalDate() {
        HistoricalResponse exchangeRatesHistoricalResponse = EXPECTED_HISTORICAL_RESPONSE;

        // Perform the actual API request
        Response response = given().queryParam("access_key", ApiConfig.ACCESS_KEY)
                .when()
                .get(HISTORICAL_RATES_ENDPOINT)
                .then()
                .statusCode(200) // Validate the response status code is 200
                .extract()
                .response();

        ExchangeRatesHistoricalResponse actualResponse = response.as(ExchangeRatesHistoricalResponse.class);

        assertThat(actualResponse.getDate(), equalTo(exchangeRatesHistoricalResponse.getDate()));
    }

    //Potential Errors
    @Test
    public void inValidateHistoricalDateReturnDefault() {
        String invalidDate = "";

        HistoricalResponse expectedErrorResponse = EXPECTED_HISTORICAL_RESPONSE;

        // Perform the actual API request with invalid date
        Response response = given()
                .queryParam("access_key", ApiConfig.ACCESS_KEY)
                .queryParam("date", invalidDate)
                .when()
                .get(HISTORICAL_RATES_ENDPOINT)
                .then()
                .statusCode(200) // Validate the response status code is 200
                .extract()
                .response();


        // Deserialize the actual API error response
        ExchangeRatesHistoricalResponse actualErrorResponse = response.as(ExchangeRatesHistoricalResponse.class);

        // Validate error code and description
        assertThat(actualErrorResponse.getDate(), equalTo(expectedErrorResponse.getDate()));
    }


    /////NewLogic

    @Test
    public void testGetHistoricalRates() {
        Response response = exchangeRatesEndpoint.withHistorical(true)
                .getHistoricalRates();

        assertEquals(response.getStatusCode(), 200);

        BaseExchangeResponse ratesResponse = response.as(BaseExchangeResponse.class);
        assertTrue(ratesResponse.isSuccess());
        assertTrue(ratesResponse.isHistorical());

    }

}
