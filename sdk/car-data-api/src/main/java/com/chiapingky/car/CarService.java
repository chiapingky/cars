package com.chiapingky.car;

import java.util.List;

public interface CarService {
    List<Car> getAllCar();
    List<Car> getCarByBrandName(String brandName);
    Car insertCar(Car car);
    Car deleteCar(Car car);
}
