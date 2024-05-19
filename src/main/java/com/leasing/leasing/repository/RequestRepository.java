package com.leasing.leasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leasing.leasing.entities.RequestEntity;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Integer> {

}
