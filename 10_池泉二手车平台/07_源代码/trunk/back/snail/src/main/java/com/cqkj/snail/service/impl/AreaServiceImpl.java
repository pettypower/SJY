package com.cqkj.snail.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cqkj.snail.domain.TArea;
import com.cqkj.snail.repository.AreaRepository;
import com.cqkj.snail.service.AreaService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl implements AreaService {

    @Resource
    AreaRepository areaRepository;

    @Override
    public List<TArea> findAll() {
        return areaRepository.findAll();
    }

    @Override
    public Page<TArea> findAll(Specification<TArea> specification, Pageable pageable) {
        return areaRepository.findAll(specification, pageable);
    }

    @Override
    public TArea findById(TArea area) {
        return areaRepository.findById(area.getId()).get();
    }

    @Override
    public void saveArea(TArea area) {
        areaRepository.save(area);

    }

    @Override
    public void ediTArea(TArea area) {
        TArea areaInfo = areaRepository.getOne(area.getId());
        // 行政区代码
        areaInfo.setAdcode(area.getAdcode());
        // 地域等级
        areaInfo.setAreaLevel(area.getAreaLevel());
        // 名称
        areaInfo.setName(area.getName());
        // 上级
        areaInfo.setParentId(area.getParentId());
        //保存对象
        areaRepository.save(areaInfo);
    }

    @Override
    public void deleteArea(TArea area) {
        areaRepository.delete(area);
    }

    @Override
    public List<TArea> findAllByAdcode(TArea area) {

        return areaRepository.findByAdcode(area.getAdcode());
    }

}
