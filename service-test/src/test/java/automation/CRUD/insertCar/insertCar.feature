Feature: test success and fail scenario

    Background:
        * url localhost:8080

    Scenario:insertCar
        # insert for first time, brand and car both new and will be inserted
        * def reqInsertSuccess = read('insertCar.json')
        * def resInsertSuccess = call read('reusable.feature@scenarioName:insertSuccess') {request_json: '#(reqInsertSuccess)'}

        # insert same data again and fail
        * def resInsertFailed = call read('reusable.feature@scenarioName:insertFail') {request_json: '#(reqInsertSuccess)'}

        # delete data
        * def reqDeleteSuccess = read('insertCar.json')
        * def resDeleteSuccess = call read('../deleteCar/reusable.feature@scenarioName:deleteSuccess') {request_json: '#(reqDeleteSuccess)'}