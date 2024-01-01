package com.chiapingky.cars;

import com.chiapingky.brand.Brand;
import com.chiapingky.brand.BrandService;
import com.chiapingky.car.Car;
import com.chiapingky.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarsService {
    private final CarService carServiceImpl;
    private final BrandService brandService;

    @Autowired
    public CarsService(CarService carServiceImpl, BrandService brandService) {
        this.carServiceImpl = carServiceImpl;
        this.brandService = brandService;
    }

    public List<Car> getAllCars() {
        return carServiceImpl.getAllCar();
    }

    public List<Car> getCarsByBrand(String brand) {
        if (brand == null) {
            return getAllCars();
        } else if (brand.isEmpty()) {
            throw new IllegalStateException("Brand must not be empty");
        }
        return carServiceImpl.getCarByBrandName(brand);
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
        return carServiceImpl.insertCar(new Car(name, brandExist));
    }

    public Car deleteCar(String name, String brand) {
        Brand brandExist = brandService.getBrandByName(brand);
        if (brandExist == null) {
            throw new IllegalStateException("Brand does not exist");
        }
        return carServiceImpl.deleteCar(new Car(name, brandExist));
    }
}
