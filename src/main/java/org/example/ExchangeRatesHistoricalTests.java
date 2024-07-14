package org.example;

import io.restassured.response.Response;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.example.models.ExchangeRatesErrorResponse;
import org.example.responses.BaseExchangeResponse;
import org.junit.Test;

import java.time.LocalDate;

import static org.example.FixerApiUtils.validateRatesType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
public class ExchangeRatesHistoricalTests extends BaseApiTest {
    public static final String DATE = "2013-12-24";

    @Test
    public void validateHistoricalEqualTrue() {
        Response response = exchangeRatesEndpoint
                .getHistoricalRates(DATE);

        BaseExchangeResponse actualResponse = response.as(BaseExchangeResponse.class);

        assertThat(actualResponse.isHistorical(), equalTo(true));
    }

    @Test
    public void validateHistoricalDate() {
        Response response = exchangeRatesEndpoint
                .getHistoricalRates(DATE);

        BaseExchangeResponse actualResponse = response.as(BaseExchangeResponse.class);
        assertThat(actualResponse.getDate(), equalTo(DATE));
    }

    //Potential Errors
    @Test
    public void inValidateHistoricalDateReturnError() {
        String invalidDate = "";

        //expected error
        ExchangeRatesErrorResponse.Error error = new ExchangeRatesErrorResponse.Error(103, "missing_access_key", "You have not supplied an API Access Key. [Required format: access_key=YOUR_ACCESS_KEY]");

        ExchangeRatesErrorResponse expectedErrorResponse = ExchangeRatesErrorResponse.builder()
                .success(false)
                .error(error)
                .build();

        Response response = exchangeRatesEndpoint.getHistoricalRates(invalidDate);

        ExchangeRatesErrorResponse actualErrorResponse = response.as(ExchangeRatesErrorResponse.class);

        // Validate error code and description
        assertThat(actualErrorResponse.getError().getCode(), equalTo(expectedErrorResponse.getError().getCode()));

    }

    /////NewLogic
    @Test
    public void testGetHistoricalRates() {
        log.info("getHistorical {}", LocalDate.now().toString());
        Response response = exchangeRatesEndpoint
                .getHistoricalRates(DATE);


        assertEquals(response.getStatusCode(), 200);

        BaseExchangeResponse ratesResponse = response.as(BaseExchangeResponse.class);

        assertTrue(ratesResponse.isSuccess());
        assertTrue(ratesResponse.isHistorical());
        assertEquals(ratesResponse.getDate(), DATE);
        validateRatesType(ratesResponse);

    }

}
