# Fixer Api Automation

## Tests Overview

### Technologies Used

- Java
- Junit
- Rest-Assured
- Lombok

### Test Classes

1. **[FixerHistoricalTests.java](tests%2FFixerHistoricalTests.java)**
   - **Description**: Tests related to historical exchange rates API endpoints.
   - **Features Tested**:
      - Validation of historical data correctness.
      - Handling invalid historical dates.
      - Error responses for future dates.
      - Verification of response types.
      - Ensuring correct status codes.

2. **[FixerLatestTests.java](tests%2FFixerLatestTests.java)**
   - **Description**: Tests related to the latest exchange rates API endpoint.
   - **Features Tested**:
      - Handling of empty or missing access key.
      - Validation of the default base currency.
      - Verification of response data types.
      - Ensuring the response date matches today's date.

3. **[FixerTomorrowTests.java](tests%2FFixerTomorrowTests.java)**
    - **Description**: Tests related to the prediction of tomorrow's exchange rates API endpoint.
    - **Features Tested**:
        - Validation that the response date equals tomorrow's date.