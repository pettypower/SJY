package com.cqkj.snail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.cqkj.snail.domain.TTruck;

public interface TruckRepository extends JpaRepository<TTruck, String> {
    List<TTruck> findByVehicleType(String vehicleType);
}
