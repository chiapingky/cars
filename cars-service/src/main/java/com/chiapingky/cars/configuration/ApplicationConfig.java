package com.chiapingky.cars.configuration;

import com.chiapingky.brand.BrandRepository;
import com.chiapingky.brand.BrandService;
import com.chiapingky.brand.BrandServiceImpl;
import com.chiapingky.car.CarRepository;
import com.chiapingky.car.CarService;
import com.chiapingky.car.CarServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CarService carService(CarRepository carRepository) {
        return new CarServiceImpl(carRepository);
    }

    @Bean
    public BrandService brandService(BrandRepository brandRepository) {
        return new BrandServiceImpl(brandRepository);
    }
}
