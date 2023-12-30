Feature: test success and fail scenario

    Background:
        * url localhost:8080

    Scenario:getAllCars
        # read empty data
        * def resReadEmpty = call read('reusable.feature@scenarioName:readEmpty')

        # insert for first time, brand and car both new and will be inserted
        * def reqInsertSuccess1 = read('insertCar1.json')
        * def resInsertSuccess1 = call read('../insertCar/reusable.feature@scenarioName:insertSuccess') {request_json: '#(reqInsertSuccess1)'}
        * def reqInsertSuccess2 = read('insertCar2.json')
        * def resInsertSuccess2 = call read('../insertCar/reusable.feature@scenarioName:insertSuccess') {request_json: '#(reqInsertSuccess2)'}

        # read inserted data
        * def resReadSuccess = call read('reusable.feature@scenarioName:readSuccess')

        # delete data
        * def reqDeleteSuccess1 = read('insertCar1.json')
        * def resDeleteSuccess1 = call read('../deleteCar/reusable.feature@scenarioName:deleteSuccess') {request_json: '#(reqDeleteSuccess1)'}
        * def reqDeleteSuccess2 = read('insertCar2.json')
        * def resDeleteSuccess2 = call read('../deleteCar/reusable.feature@scenarioName:deleteSuccess') {request_json: '#(reqDeleteSuccess2)'}