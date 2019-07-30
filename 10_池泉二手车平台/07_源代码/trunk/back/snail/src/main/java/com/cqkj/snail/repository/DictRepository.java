package com.cqkj.snail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqkj.snail.domain.TDict;

public interface DictRepository extends JpaRepository<TDict, String> {
    TDict findByDictCode(String dictCode);
}
