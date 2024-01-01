package com.chiapingky.cars;

import com.chiapingky.brand.Brand;
import com.chiapingky.brand.BrandRepository;
import com.chiapingky.car.Car;
import com.chiapingky.car.CarRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories( basePackageClasses = {
		CarRepository.class, BrandRepository.class
})
@EntityScan(basePackageClasses = {
		Car.class, Brand.class
})
public class CarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsApplication.class, args);
	}

}
