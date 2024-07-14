package org.example;

import io.restassured.response.Response;
import org.example.models.ExchangeRatesErrorResponse;
import org.example.responses.BaseExchangeResponse;
import org.junit.Test;
import java.time.LocalDate;
import static io.restassured.RestAssured.given;
import static org.example.ApiConfig.LATEST_ENDPOINT;
import static org.example.FixerApiUtils.validateRatesType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ExchangeRatesLatestTests extends BaseApiTest {

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
                .get(LATEST_ENDPOINT);

        // Deserialize the actual API error response
        ExchangeRatesErrorResponse actualErrorResponse = response.as(ExchangeRatesErrorResponse.class);

        // Validate error code, type, and description
        assertThat(actualErrorResponse.getError().getCode(), equalTo(expectedErrorResponse.getError().getCode()));
        assertThat(actualErrorResponse.getError().getInfo(), equalTo(expectedErrorResponse.getError().getInfo()));
        assertThat(actualErrorResponse.getError().getType(), equalTo(expectedErrorResponse.getError().getType()));

    }

    @Test
    public void exchangeRatesByDefaultRelativeEUR() {

        Response response = exchangeRatesEndpoint
                .getLatestRates();

        String baseCurrency = response.jsonPath().getString("base");
        assertThat(baseCurrency, equalTo("EUR"));
    }

    @Test
    public void validateAllParamsTypeResponse() {
        // Perform the actual API request
        Response response = exchangeRatesEndpoint
                .getLatestRates();

        BaseExchangeResponse actualResponse = response.as(BaseExchangeResponse.class);

        // Validate types of fields against expected types
        assertThat(actualResponse.isSuccess(), instanceOf(Boolean.class)); // Check if isSuccess() returns Boolean
        assertThat(actualResponse.getBase(), instanceOf(String.class));    // Check if getBase() returns String
        assertThat(actualResponse.getDate(), instanceOf(String.class));    // Check if getDate() returns String
        validateRatesType(actualResponse);

    }
    @Test
    public void validateCurrentDateParams() {
        Response response = exchangeRatesEndpoint
                .getLatestRates();

        BaseExchangeResponse actualResponse = response.as(BaseExchangeResponse.class);
        assertThat(actualResponse.getDate(), equalTo(LocalDate.now().toString()));
    }

}


