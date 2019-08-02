package com.cqkj.snail.service;

import java.util.List;

import com.cqkj.snail.domain.TArea;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface AreaService {
    List<TArea> findAllByAdcode(TArea area);

    List<TArea> findAll();
        
    Page<TArea> findAll(Specification<TArea> specification, Pageable pageable);

    TArea findById(TArea area);

    void saveArea(TArea area);

    void ediTArea(TArea area);

    void deleteArea(TArea area);
}
