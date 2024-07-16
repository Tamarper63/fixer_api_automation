package org.example.tests;

import groovyjarjarantlr4.v4.runtime.RuleVersion;
import io.restassured.response.Response;
import org.example.mockResponse.MockSuccessResponse;
import org.example.utils.FixerApiUtils;
import org.example.models.FixerResponseJson;
import org.example.responses.TomorrowPredictionEndpoint;
import org.junit.Test;

import javax.annotation.processing.SupportedSourceVersion;

import static org.example.utils.DateUtils.getTomorrowsDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//Replace with real response from version number 1.1
public class FixerTomorrowTests extends BaseApiTest {
    TomorrowPredictionEndpoint tomorrowPredictionEndpoint = new TomorrowPredictionEndpoint();
    String TOMORROWS_DATE = getTomorrowsDate();
    @Test
    public void validateDateEqualsTomorrow() {
        //Actual Response
//     Response response = tomorrowPredictionEndpoint.getTomorrowRates();
//     FixerApiUtils.validateStatusIsOk(response);
//     FixerResponseJson actualResponse = response.as(FixerResponseJson.class);
        //Mock
        FixerResponseJson mockResponse = MockSuccessResponse.createTomorrowMockResponse();

        // Validate the mock response
        assertTrue(mockResponse.isSuccess());
        assertEquals(TOMORROWS_DATE, mockResponse.getDate());
    }
}
