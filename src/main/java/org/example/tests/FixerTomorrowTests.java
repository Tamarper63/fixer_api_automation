package org.example.tests;

import io.restassured.response.Response;
import org.example.Utils.FixerApiUtils;
import org.example.models.FixerResponseJson;
import org.example.responses.TomorrowPredictionEndpoint;
import org.junit.Test;

import static org.example.Utils.DateUtils.getTomorrowsDate;

public class FixerTomorrowTests extends BaseApiTest {
    TomorrowPredictionEndpoint tomorrowPredictionEndpoint = new TomorrowPredictionEndpoint();
    String TOMORROWS_DATE = getTomorrowsDate();

    @Test
    public void validateDateEqualsTomorrow() {
        //Using Mock
        Response response = tomorrowPredictionEndpoint.getTomorrowRates();

        FixerApiUtils.validateStatusIsOk(response);

        FixerResponseJson actualResponse = response.as(FixerResponseJson.class);

        FixerApiUtils.validateDate(actualResponse, TOMORROWS_DATE);
    }
}
