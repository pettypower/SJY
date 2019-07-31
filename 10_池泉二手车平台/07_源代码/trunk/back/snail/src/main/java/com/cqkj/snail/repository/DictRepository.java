package com.cqkj.snail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cqkj.snail.domain.TDict;

public interface DictRepository extends JpaRepository<TDict, String>, JpaSpecificationExecutor<TDict> {
    TDict findByDictCode(String dictCode);
}
