package com.chiapingky.cars.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (carRepository.getCarByNameAndBrandId(car.getName(), car.getBrand().getId()).isPresent()) {
            throw new IllegalStateException("Car already exist");
        }
        carRepository.save(car);
        return carRepository.getCarByNameAndBrandId(car.getName(), car.getBrand().getId()).orElse(null);
    }

    public Car deleteCar(Car car) {
        Car carExist = carRepository.getCarByNameAndBrandId(car.getName(), car.getBrand().getId()).orElse(null);
        if (carExist != null) {
            carRepository.delete(carExist);
            return carExist;
        } else {
            throw new IllegalStateException("Car does not exist");
        }
    }
}
