package com.leasing.leasing.service.vehicle;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leasing.leasing.entities.VehicleEntity;
import com.leasing.leasing.repository.VehicleRepository;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public VehicleEntity createVehicle(VehicleEntity vehicle){
        return vehicleRepository.save(vehicle);
    }

    public List<VehicleEntity> getVehicleById(Integer id){
        return vehicleRepository.findById(id).stream().toList();
    }

    public List<VehicleEntity> getVehicle(){
        return vehicleRepository.findAll();
    }

    public void deleteVehicleById(Integer id) {
        vehicleRepository.deleteById(id);
    }
}
