package com.leasing.leasing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leasing.leasing.entities.VehicleEntity;
import com.leasing.leasing.service.vehicle.VehicleService;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public VehicleEntity createVehicle(@RequestBody VehicleEntity vehicleEntity) {
        return vehicleService.createVehicle(vehicleEntity);
    }
    

    @GetMapping
    public List<VehicleEntity> getVehicle(@RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            return vehicleService.getVehicleById(id);
        } else {
            return vehicleService.getVehicle();
        }
    }

    @PutMapping
    public VehicleEntity updateVehicle(@RequestBody VehicleEntity vehicleEntity) {
        return vehicleService.createVehicle(vehicleEntity);
    }

    @DeleteMapping
    public void deleteVehicleById(@RequestParam Integer id) {
        vehicleService.deleteVehicleById(id);
    }

}
