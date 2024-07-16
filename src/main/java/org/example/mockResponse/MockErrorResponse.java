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

    public static MockErrorResponse createInvalidDateError() {
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

    public static MockErrorResponse creatInvalidAccessKeyError() {
        return new MockErrorResponse(
                false,
                new Error(101, "invalid_access_key", "You have not supplied a valid API Access Key. [Technical Support: support@apilayer.com]")
        );
    }
}
