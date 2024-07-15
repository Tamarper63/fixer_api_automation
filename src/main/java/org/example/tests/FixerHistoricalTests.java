package org.example.tests;

import io.restassured.response.Response;
import org.example.Utils.FixerApiUtils;
import org.example.models.FixerErrorResponse;
import org.example.models.FixerResponseJson;
import org.example.responses.HistoricalRatesEndpoint;
import org.example.mockResponse.MockErrorResponse;
import org.junit.Test;

import static org.example.Utils.FixerApiUtils.validateErrorResponseBody;
import static org.example.Utils.FixerApiUtils.validateParamsType;
import static org.example.Utils.DateUtils.getTomorrowsDate;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FixerHistoricalTests extends BaseApiTest {
    public static final String DATE = "2020-12-24";
    HistoricalRatesEndpoint historicalRatesEndpoint = new HistoricalRatesEndpoint();

    @Test
    public void validateHistoricalEqualTrue() {
        Response response = historicalRatesEndpoint.getHistoricalRates(DATE);

        FixerApiUtils.validateStatusIsOk(response);

        FixerResponseJson actualResponse = response.as(FixerResponseJson.class);

        assertThat(actualResponse.isHistorical(), equalTo(true));
    }

    @Test
    public void validateHistoricalDate() {
        Response response = historicalRatesEndpoint.getHistoricalRates(DATE);

        FixerApiUtils.validateStatusIsOk(response);

        FixerResponseJson actualResponse = response.as(FixerResponseJson.class);
        assertThat(actualResponse.getDate(), equalTo(DATE));
    }

    //Potential Errors
    @Test
    public void inValidateHistoricalDateReturnError() {
        String invalidDate = "";
        //expected error
        MockErrorResponse expectedErrorResponse = MockErrorResponse.createInvalidEmptyDateError();

        // Send request with invalid date
        Response response = historicalRatesEndpoint.getHistoricalRates(invalidDate);

        // covert to object
        FixerErrorResponse actualErrorResponse = response.as(FixerErrorResponse.class);

        // Validate the actual error response against the expected error response
        assertEquals(expectedErrorResponse.isSuccess(), actualErrorResponse.isSuccess());
        assertEquals(expectedErrorResponse.getError().getCode(), actualErrorResponse.getError().getCode());
        assertEquals(expectedErrorResponse.getError().getType(), actualErrorResponse.getError().getType());
    }

    @Test
    public void testGetHistoricalRatesParamsType() {
        Response response = historicalRatesEndpoint
                .getHistoricalRates(DATE);

        FixerApiUtils.validateStatusIsOk(response);

        FixerResponseJson ratesResponse = response.as(FixerResponseJson.class);

        assertTrue(ratesResponse.isSuccess());
        assertTrue(ratesResponse.isHistorical());
        assertEquals(ratesResponse.getDate(), DATE);

        validateParamsType(ratesResponse);
    }
    @Test
    public void invalidFutureHistoricalDateReturnError() {
        String FUTURE_DATE = getTomorrowsDate();
        // Expected error response
        FixerErrorResponse expectedErrorResponse = MockErrorResponse.createInvalidFutureDateError();

        // Send request with a future date
        Response response = historicalRatesEndpoint.getHistoricalRates(FUTURE_DATE);

        // Get the actual error response
        FixerErrorResponse actualErrorResponse = response.as(FixerErrorResponse.class);

        validateErrorResponseBody(expectedErrorResponse, actualErrorResponse);
    }

    @Test
    public void invalidDateFormatReturnError() {
        String DIFFERENT_DATE_FORMAT = "22-12-2024";
        // Expected error response
        FixerErrorResponse expectedErrorResponse = MockErrorResponse.createInvalidFutureDateError();

        // Send request with a future date
        Response response = historicalRatesEndpoint.getHistoricalRates(DIFFERENT_DATE_FORMAT);

        // Get the actual error response
        FixerErrorResponse actualErrorResponse = response.as(FixerErrorResponse.class);

        validateErrorResponseBody(expectedErrorResponse, actualErrorResponse);
    }

}
