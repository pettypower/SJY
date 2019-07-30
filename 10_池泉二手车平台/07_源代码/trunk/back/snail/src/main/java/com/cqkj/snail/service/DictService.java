package com.cqkj.snail.service;

import java.util.List;

import com.cqkj.snail.domain.TDict;

public interface DictService {
    TDict findByCode(String dictCode);

    List<TDict> findAll();

    TDict findById(TDict dict);

    void saveDict(TDict dict);

    void editDict(TDict dict);

    void deleteDict(TDict dict);
}
