package com.leasing.leasing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String brand;

    private String model;

    private int year;

    private int liftingCapacity;

    public Car(String brand, String model, int year, int liftingCapacity) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.liftingCapacity = liftingCapacity;
    }

    public Car() {}

    @Override
    public String toString() {
        return "Car{" +
            "brand='" + brand + '\'' +
            ", model='" + model + '\'' +
            ", year=" + year +
            ", lifting=" + liftingCapacity +
            '}';
    }
}
