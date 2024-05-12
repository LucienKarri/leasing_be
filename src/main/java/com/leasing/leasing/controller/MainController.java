package com.leasing.leasing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.leasing.leasing.entity.Car;
import com.leasing.leasing.repository.CarRepository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequiredArgsConstructor
public class MainController{

    private final CarRepository carRepository;

    @PostMapping("/api/cars")
    public String postCar(@RequestBody Car car) {
        return carRepository.save(car).toString();
    }
    
    @SneakyThrows
    @GetMapping("/api/cars")
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @GetMapping("/api/carsId")
    public Car getCar(@RequestParam int id) {
        return carRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("api/cars")
    public void deleteCar(@RequestParam int id) {
        carRepository.deleteById(id);
    }

    @PutMapping("api/cars")
    public String putCar(@RequestBody Car car) {
        //TODO: process PUT request
        if (!carRepository.existsById(car.getId())) {
            return "No such row";
        }
        return carRepository.save(car).toString();
    }
    

}
