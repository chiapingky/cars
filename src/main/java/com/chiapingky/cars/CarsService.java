package com.chiapingky.cars;

import com.chiapingky.cars.brand.Brand;
import com.chiapingky.cars.brand.BrandService;
import com.chiapingky.cars.car.Car;
import com.chiapingky.cars.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarsService {
    private final CarService carService;
    private final BrandService brandService;

    @Autowired
    public CarsService(CarService carService, BrandService brandService) {
        this.carService = carService;
        this.brandService = brandService;
    }

    public List<Car> getAllCars() {
        return carService.getAllCar();
    }

    public List<Car> getCarsByBrand(String brand) {
        if (brand == null) {
            return getAllCars();
        } else if (brand.isEmpty()) {
            throw new IllegalStateException("Brand must not be empty");
        }
        return carService.getCarByBrandName(brand);
    }

    public Brand insertBrand(String brand) {
        return brandService.insertBrand(new Brand(brand));
    }

    @Transactional(rollbackFor = Exception.class) //rollback when cascade delete fail
    public Brand deleteBrand(String brand) {
        return brandService.deleteBrand(new Brand(brand));
    }

    @Transactional(rollbackFor = Exception.class) //rollback when any of the insert fail
    public Car insertCar(String name, String brand) {
        Brand brandExist;
        try {
            brandExist = insertBrand(brand);
            System.out.println("Brand does not exist, new brand inserted");
        } catch (IllegalStateException e) {
            brandExist = brandService.getBrandByName(brand);
        }
        return carService.insertCar(new Car(name, brandExist));
    }

    public Car deleteCar(String name, String brand) {
        Brand brandExist = brandService.getBrandByName(brand);
        if (brandExist == null) {
            throw new IllegalStateException("Brand does not exist");
        }
        return carService.deleteCar(new Car(name, brandExist));
    }
}
