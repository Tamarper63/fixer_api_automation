package org.example.tests;

import io.restassured.response.Response;
import org.example.mockResponse.MockSuccessResponse;
import org.example.utils.FixerApiUtils;
import org.example.models.FixerResponseJson;
import org.example.responses.TomorrowPredictionEndpoint;
import org.junit.Test;

import static org.example.utils.DateUtils.getTomorrowsDate;

public class FixerTomorrowTests extends BaseApiTest {
    TomorrowPredictionEndpoint tomorrowPredictionEndpoint = new TomorrowPredictionEndpoint();
    String TOMORROWS_DATE = getTomorrowsDate();

    @Test
    public void validateDateEqualsTomorrow() {
        //mock will replace this req until release
//        FixerResponseJson tomorrowMockResponse = MockSuccessResponse.createTomorrowMockResponse();

        Response response = tomorrowPredictionEndpoint.getTomorrowRates();

        FixerApiUtils.validateStatusIsOk(response);

        FixerResponseJson actualResponse = response.as(FixerResponseJson.class);

        FixerApiUtils.validateDate(actualResponse, TOMORROWS_DATE);
    }
}
