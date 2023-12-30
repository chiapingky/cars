Feature: test success and fail scenario

    Background:
        * url localhost:8080

    Scenario:deleteBrand
        # insert data first
        * def reqInsertSuccess = read('insertBrand.json')
        * def resInsertSuccess = call read('../insertBrand/reusable.feature@scenarioName:insertSuccess') {request_json: '#(reqInsertSuccess)'}

        # delete data for the first time
        * def reqDeleteSuccess = read('deleteBrand.json')
        * def resDeleteSuccess = call read('reusable.feature@scenarioName:deleteSuccess') {request_json: '#(reqDeleteSuccess)'}

        # delete same data again and fail
        * def resDeleteFailed = call read('reusable.feature@scenarioName:deleteFail') {request_json: '#(reqDeleteSuccess)'}