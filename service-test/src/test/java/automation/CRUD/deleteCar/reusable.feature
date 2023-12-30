Feature: delete data scenario success and fail

    Background:
        * url 'http://localhost:8080'

    @ignore @scenarioName:deleteSuccess
    Scenario: insert new data successfully
        Given path '/car'
        * def request_json = __arg.request_json
        * print request_json
        And request request_json
        And method DELETE
        * print response
        Then match response != null
        * def resultSchema =
            """
            {
              id: '#number',
              name: '#string',
              brand: '#object'
            }
            """
        And match response contains resultSchema

    @ignore @ScenarioName:deleteFail
    Scenario: insert existing data and failed
        Given path '/car'
        * def request_json = __arg.request_json
        * print request_json
        And request request_json
        And method DELETE
        * print response
        Then match response.status != "OK"
        And match response.message == "Car does not exist"