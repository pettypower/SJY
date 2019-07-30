package com.cqkj.snail.service;

import java.util.List;

import com.cqkj.snail.domain.TTruck;

public interface TruckService {
    List<TTruck> findByVehicleType(String vehicleType);

    List<TTruck> findAll();

    TTruck findById(TTruck truck);

    void saveTruck(TTruck truck);

    void editTruck(TTruck truck);

    void deleteTruck(TTruck truck);
}
