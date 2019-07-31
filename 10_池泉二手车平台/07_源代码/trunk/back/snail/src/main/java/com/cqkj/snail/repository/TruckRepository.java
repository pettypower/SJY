package com.cqkj.snail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

import com.cqkj.snail.domain.TTruck;

public interface TruckRepository extends JpaRepository<TTruck, String>, JpaSpecificationExecutor<TTruck> {
    List<TTruck> findByVehicleType(String vehicleType);
}
