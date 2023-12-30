package com.chiapingky.cars.car;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int brandId;

    public Car() {
    }

    public Car(int id, String name, int brandId) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
    }

    public Car(String name, int brandId) {
        this.name = name;
        this.brandId = brandId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBrandId() {
        return brandId;
    }
}
