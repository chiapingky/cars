package com.chiapingky.cars.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query(
            nativeQuery = true,
            value = "SELECT id, name, brand_id FROM (" +
                    "SELECT car.id, car.name, car.brand_id, brand.name AS brand_name FROM car " +
                    "INNER JOIN brand " +
                    "on car.brand_id = brand.id WHERE brand.name = :brandName)"
    )
    List<Car> getCarByBrandName(@Param("brandName") String brand);
    Optional<Car> getCarByName(String name);
    Optional<Car> getCarByNameAndBrandId(String name, int brandId);
}
