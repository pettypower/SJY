package com.cqkj.snail.service;

import java.util.List;

import com.cqkj.snail.domain.TDict;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface DictService {
    TDict findByCode(String dictCode);

    List<TDict> findAll();

    Page<TDict> findAll(Specification<TDict> specification, Pageable pageable);

    TDict findById(TDict dict);

    void saveDict(TDict dict);

    void editDict(TDict dict);

    void deleteDict(TDict dict);
}
