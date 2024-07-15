package org.example.mockResponse;

import org.example.models.FixerErrorResponse;

public class MockErrorResponse extends FixerErrorResponse {
    public MockErrorResponse(boolean success, Error error) {
        super(success, error);
    }

    public static MockErrorResponse createInvalidEmptyDateError() {
        return new MockErrorResponse(
                false,
                new Error(103, "invalid_api_function", "")
        );
    }

    public static MockErrorResponse createInvalidFutureDateError() {
        return new MockErrorResponse(
                false,
                new Error(302, "invalid_date", "You have entered an invalid date. [Required format: date=YYYY-MM-DD]")
        );
    }

    public static MockErrorResponse createMissingAccessError() {
        return new MockErrorResponse(
                false,
                new Error(101, "missing_access_key", "You have not supplied an API Access Key. [Required format: access_key=YOUR_ACCESS_KEY]")
        );
    }

}
