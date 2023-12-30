package com.chiapingky.cars;

import com.chiapingky.cars.brand.Brand;
import com.chiapingky.cars.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/car")
public class CarsServiceController {
    private static final String CAR_NAME = "carName";
    private static final String CAR_BRAND = "carBrand";
    private final CarsService carsService;

    @Autowired
    public CarsServiceController(CarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping
    public List<CarResponse> getCarsByBrand(@RequestParam(name = "brand", required = false) String brand) {
        return carsService.getCarsByBrand(brand);
    }

    @PostMapping(path = "/brand")
    public Brand insertBrand(@RequestBody Brand brand) {
        return carsService.insertBrand(brand.getName());
    }

    @PostMapping
    public Car insertCar(@RequestBody Map<String, String> car) {
        return carsService.insertCar(car.get(CAR_NAME), car.get(CAR_BRAND));
    }

    @DeleteMapping(path = "/brand")
    public Brand deleteBrand(@RequestBody Brand brand) {
        return carsService.deleteBrand(brand.getName());
    }

    @DeleteMapping
    public Car deleteCar(@RequestBody Map<String, String> car) {
        return carsService.deleteCar(car.get(CAR_NAME), car.get(CAR_BRAND));
    }
}
