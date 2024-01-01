package com.chiapingky.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> getCarByBrandName(String brand);

    Optional<Car> getCarByNameAndBrandId(String name, int brandId);
}
