package com.cqkj.snail.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cqkj.snail.domain.TTruck;
import com.cqkj.snail.repository.TruckRepository;
import com.cqkj.snail.service.TruckService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class TruckServiceImpl implements TruckService {

    @Resource
    TruckRepository truckRepository;

    @Override
    public List<TTruck> findByVehicleType(String vehicleType) {
        return truckRepository.findByVehicleType(vehicleType);
    }

    @Override
    public List<TTruck> findAll() {
        return truckRepository.findAll();
    }

    @Override
    public Page<TTruck> findAll(Specification<TTruck> specification,  Pageable pageable) {
        return truckRepository.findAll(specification, pageable);
    }

    @Override
    public TTruck findById(TTruck truck) {
        return truckRepository.findById(truck.getId()).get();
    }

    @Override
    public void saveTruck(TTruck truck) {
        truckRepository.save(truck);
    }
    
    @Override
    public void editTruck(TTruck truck) {
        TTruck truckInfo = truckRepository.getOne(truck.getId());
        truckInfo.setVehicleType(truck.getVehicleType());
        truckRepository.save(truckInfo);
    }

    @Override
    public void deleteTruck(TTruck truck) {
        truckRepository.delete(truck);
    }

    @Override
    public int addTwoNumber(int frist, int second){
        return frist + second;
    }

}
