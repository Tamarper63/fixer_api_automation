package org.example.utils;

import io.restassured.response.Response;
import org.example.models.FixerResponseJson;
import org.example.models.FixerErrorResponse;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;

public class FixerApiUtils {
    public static void validateStatusIsOk(Response response) {
        assertEquals(response.getStatusCode(), 200);
    }
    public static void validateParamsType(FixerResponseJson actualResponse) {
        assertThat(actualResponse.getRates(), instanceOf(Map.class)); // Check if rates is a Map
        actualResponse.getRates().forEach((key, value) -> {
            assertThat(key, instanceOf(String.class));   // Check if map keys are Strings
            assertThat(value, instanceOf(Double.class)); // Check if map values are Doubles
        });
    }

    public static void validateErrorResponseBody(FixerErrorResponse expectedErrorResponse, FixerErrorResponse actualErrorResponse) {
        assertEquals(expectedErrorResponse.isSuccess(), actualErrorResponse.isSuccess());
        assertEquals(expectedErrorResponse.getError().getCode(), actualErrorResponse.getError().getCode());
        assertEquals(expectedErrorResponse.getError().getType(), actualErrorResponse.getError().getType());
        assertEquals(expectedErrorResponse.getError().getInfo(), actualErrorResponse.getError().getInfo());
    }

    public static void validateDate(FixerResponseJson actualResponse, String date) {
        assertThat(actualResponse.getDate(), equalTo(date));
    }
}
