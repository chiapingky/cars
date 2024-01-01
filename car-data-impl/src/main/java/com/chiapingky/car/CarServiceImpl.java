package com.chiapingky.car;

import java.util.List;

public class CarServiceImpl implements CarService{
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
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
