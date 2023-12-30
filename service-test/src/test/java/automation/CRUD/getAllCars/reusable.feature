Feature: insert data scenario success and fail

    Background:
        * url 'http://localhost:8080'

    @ignore @scenarioName:readSuccess
    Scenario: insert new data successfully
        Given path '/car'
        And method GET
        * print response
        Then match response != null

    @ignore @ScenarioName:readEmpty
    Scenario: insert existing data and failed
        Given path '/car'
        And method GET
        * print response
        Then match response.status != "OK"
        Then match response != null
        And match response.status == "OK"
        * def resultSchema =
            """
            []
            """
        And match response contains resultSchema