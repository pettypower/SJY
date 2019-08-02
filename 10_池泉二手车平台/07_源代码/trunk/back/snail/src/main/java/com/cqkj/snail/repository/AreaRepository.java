package com.cqkj.snail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

import com.cqkj.snail.domain.TArea;

public interface AreaRepository extends JpaRepository<TArea, String>, JpaSpecificationExecutor<TArea> {
    List<TArea> findByAdcode(String findAllByAdcode);
}
