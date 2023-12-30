package com.chiapingky.cars;

import com.chiapingky.cars.brand.Brand;
import com.chiapingky.cars.brand.BrandService;
import com.chiapingky.cars.car.Car;
import com.chiapingky.cars.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CarsService {
    private final CarService carService;
    private final BrandService brandService;

    @Autowired
    public CarsService(CarService carService, BrandService brandService) {
        this.carService = carService;
        this.brandService = brandService;
    }

    public List<CarResponse> getAllCars() {
        List<CarResponse> result = new ArrayList<>();
        List<Car> cars = carService.getAllCar();
        Map<Integer, Brand> brands = brandService.getAllBrand().stream().collect(Collectors.toMap(Brand::getId, Function.identity()));
        cars.forEach(c ->{
                result.add(new CarResponse(c.getId(), c.getName(), brands.get(c.getBrandId()).getName()));
        });
        return result;
    }

    public List<CarResponse> getCarsByBrand(String brand) {
        if (brand == null) {
            return getAllCars();
        } else if (brand.isEmpty()) {
            throw new IllegalStateException("Brand must not be empty");
        }
        List<Car> cars = carService.getCarByBrandName(brand);
        List<CarResponse> result = new ArrayList<>();
        cars.forEach( c -> {
            result.add(new CarResponse(c.getId(), c.getName(), brand));
        });
        return result;
    }

    public Brand insertBrand(String brand) {
        Brand newBrand = brandService.insertBrand(new Brand(brand));
        if (newBrand == null) {
            throw new IllegalStateException("Brand already exist");
        }
        return newBrand;
    }

    public Brand deleteBrand(String brand) {
        Brand deleted = brandService.deleteBrand(new Brand(brand));
        if (deleted == null) {
            throw new IllegalStateException("Brand does not exist");
        }
        return deleted;
    }

    public Car insertCar(String name, String brand) {
        Brand brandExist = brandService.getBrandByName(brand);
        if (brandExist == null) {
            brandExist = brandService.insertBrand(new Brand(brand));
            if (brandExist == null) {
                throw new IllegalStateException("Trouble when inserting car. Check your brand name");
            }
            System.out.println("Brand does not exist, new brand inserted");
        }
        Car newCar = carService.insertCar(new Car(name, brandExist.getId()));
        if (newCar == null) {
            throw new IllegalStateException("Car already exist");
        }
        return newCar;
    }

    public Car deleteCar(String name, String brand) {
        Brand brandExist = brandService.getBrandByName(brand);
        if (brandExist == null) {
            throw new IllegalStateException("Brand does not exist");
        }
        Car deleted = carService.deleteCar(new Car(name, brandExist.getId()));
        if (deleted == null) {
            throw new IllegalStateException("Car does not exist");
        }
        return deleted;
    }
}
