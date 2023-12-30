Feature: test success and fail scenario

    Background:
        * url localhost:8080

    Scenario:deleteCar
        # insert data first
        * def reqInsertSuccess = read('insertCar.json')
        * def resInsertSuccess = call read('../insertCar/reusable.feature@scenarioName:insertSuccess') {request_json: '#(reqInsertSuccess)'}

        # delete data for the first time
        * def reqDeleteSuccess = read('deleteCar.json')
        * def resDeleteSuccess = call read('reusable.feature@scenarioName:deleteSuccess') {request_json: '#(reqDeleteSuccess)'}

        # delete same data again and fail
        * def resDeleteFailed = call read('reusable.feature@scenarioName:deleteFail') {request_json: '#(reqDeleteSuccess)'}