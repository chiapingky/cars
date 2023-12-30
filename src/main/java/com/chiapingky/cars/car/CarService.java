package com.chiapingky.cars.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService (CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCar() {
        return carRepository.findAll();
    }

    public List<Car> getCarByBrandName(String brandName) {
        return carRepository.getCarByBrandName(brandName);
    }

    public Car insertCar(Car car) {
        Optional<Car> carExist = carRepository.getCarByName(car.getName());
        if (carExist.isPresent()) {
            return null;
        }
        carRepository.save(car);
        return carRepository.getCarByName(car.getName()).orElse(null);
    }

    public Car deleteCar(Car car) {
        Optional<Car> carExist = carRepository.getCarByNameAndBrandId(car.getName(), car.getBrand().getId());
        Car result = null;
        if (carExist.isPresent()) {
            result = carExist.get();
            carRepository.delete(result);
        }
        return result;
    }
}
