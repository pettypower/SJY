package com.cqkj.snail.service;

import java.util.List;

import com.cqkj.snail.domain.TTruck;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface TruckService {
    List<TTruck> findByVehicleType(String vehicleType);

    List<TTruck> findAll();
        
    Page<TTruck> findAll(Specification<TTruck> specification, Pageable pageable);

    TTruck findById(TTruck truck);

    void saveTruck(TTruck truck);

    void editTruck(TTruck truck);

    void deleteTruck(TTruck truck);

    int addTwoNumber(int frist, int second);
}
