package com.leasing.leasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leasing.leasing.entities.VehicleEntity;


@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Integer>{

}
