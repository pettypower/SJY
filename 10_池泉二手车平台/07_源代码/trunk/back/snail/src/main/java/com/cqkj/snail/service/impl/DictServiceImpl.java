package com.cqkj.snail.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cqkj.snail.domain.TDict;
import com.cqkj.snail.repository.DictRepository;
import com.cqkj.snail.service.DictService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class DictServiceImpl implements DictService {

    @Resource
    DictRepository dictRepository;

    @Override
    public TDict findByCode(String dictCode) {
        return dictRepository.findByDictCode(dictCode);
    }

    @Override
    public List<TDict> findAll() {
        return dictRepository.findAll();
    }

    @Override
    public Page<TDict> findAll(Specification<TDict> specification,  Pageable pageable) {
        return dictRepository.findAll(specification, pageable);
    }

    @Override
    public TDict findById(TDict dict) {
        return dictRepository.findById(dict.getId()).get();
    }

    @Override
    public void saveDict(TDict dict) {
        dictRepository.save(dict);
    }
    

    @Override
    public void editDict(TDict dict) {
        TDict dictInfo = dictRepository.getOne(dict.getId());
        dictInfo.setDictName(dict.getDictName());
        dictRepository.save(dictInfo);
    }

    @Override
    public void deleteDict(TDict dict) {
        dictRepository.delete(dict);
    }

}
