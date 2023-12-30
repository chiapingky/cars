Feature: test success and fail scenario

    Background:
        * url localhost:8080

    Scenario:insertBrand
        # insert for first time
        * def reqInsertSuccess = read('insertBrand.json')
        * def resInsertSuccess = call read('reusable.feature@scenarioName:insertSuccess') {request_json: '#(reqInsertSuccess)'}

        # insert same data again and fail
        * def resInsertFailed = call read('reusable.feature@scenarioName:insertFail') {request_json: '#(reqInsertSuccess)'}

        # delete data
        * def reqDeleteSuccess = read('insertBrand.json')
        * def resDeleteSuccess = call read('../deleteBrand/reusable.feature@scenarioName:deleteSuccess') {request_json: '#(reqDeleteSuccess)'}