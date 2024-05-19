package com.leasing.leasing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String brand;
    private String model; 
    private int year;
    private int liftingCapacity;
    private int length;
    private int height;
    private int width; 
    private int capacity;
    private int enginePower;
    private int torque;
    private String fuelType;
    private String transmission;
    private String description;

}
