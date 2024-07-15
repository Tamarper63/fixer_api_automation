package org.example.tests;

import io.restassured.response.Response;
import org.example.Utils.FixerApiUtils;
import org.example.mockResponse.MockErrorResponse;
import org.example.models.FixerErrorResponse;
import org.example.models.FixerResponseJson;
import org.example.responses.LatestRatesEndpoint;
import org.junit.Test;
import java.time.LocalDate;
import static io.restassured.RestAssured.given;
import static org.example.ApiConfig.LATEST_ENDPOINT;
import static org.example.Utils.FixerApiUtils.validateParamsType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class FixerLatestTests extends BaseApiTest {
    LatestRatesEndpoint latestRatesEndpoint = new LatestRatesEndpoint();

    @Test
    public void validateEmptyAccessKeyReturnsErrorResponse() {
        MockErrorResponse expectedErrorResponse = MockErrorResponse.createMissingAccessError();
        //expected Mock

        // Perform the actual API request without API key
        Response response = given()
                .queryParam("access_key", "") // Provide an empty API key or remove this line to test without key
                .when()
                .get(LATEST_ENDPOINT);

        // Deserialize the actual API error response
        FixerErrorResponse actualErrorResponse = response.as(FixerErrorResponse.class);

        // Validate error code, type, and description
        assertThat(actualErrorResponse.getError().getCode(), equalTo(expectedErrorResponse.getError().getCode()));
        assertThat(actualErrorResponse.getError().getInfo(), equalTo(expectedErrorResponse.getError().getInfo()));
        assertThat(actualErrorResponse.getError().getType(), equalTo(expectedErrorResponse.getError().getType()));

    }


    @Test
    public void validateDefaultBaseCurrencyIsEUR() {

        Response response = latestRatesEndpoint.getLatestRates();

        String baseCurrency = response.jsonPath().getString("base");

        assertThat(baseCurrency, equalTo("EUR"));
    }

    @Test
    public void validateSuccessResponseTypesIsValid() {

        // Perform the actual API request
        Response response = latestRatesEndpoint.getLatestRates();

        FixerResponseJson actualResponse = response.as(FixerResponseJson.class);

        // Validate types of fields against expected types
        assertThat(actualResponse.isSuccess(), instanceOf(Boolean.class));
        assertThat(actualResponse.getBase(), instanceOf(String.class));
        assertThat(actualResponse.getDate(), instanceOf(String.class));
        validateParamsType(actualResponse);
    }

    @Test
    public void validateSuccessResponseDateIsToday() {
        ///get response
        Response response = latestRatesEndpoint.getLatestRates();
        ///thorw to object response
        FixerResponseJson actualResponse = response.as(FixerResponseJson.class);
        //check
        FixerApiUtils.validateDate(actualResponse, LocalDate.now().toString());
    }

}


