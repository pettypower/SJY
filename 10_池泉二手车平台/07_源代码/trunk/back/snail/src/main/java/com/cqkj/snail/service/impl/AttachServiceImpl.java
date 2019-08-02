package com.cqkj.snail.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cqkj.snail.domain.TAttach;
import com.cqkj.snail.repository.AttachRepository;
import com.cqkj.snail.service.AttachService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AttachServiceImpl implements AttachService {

    @Resource
    AttachRepository attachRepository;

    @Override
    public List<TAttach> findAll() {
        return attachRepository.findAll();
    }

    @Override
    public Page<TAttach> findAll(Specification<TAttach> specification,  Pageable pageable) {
        return attachRepository.findAll(specification, pageable);
    }

    @Override
    public TAttach findById(TAttach attach) {
        return attachRepository.findById(attach.getId()).get();
    }

    @Override
    public TAttach saveAttach(TAttach attach) {
        return attachRepository.save(attach);
    }

    @Override
    public void deleteAttach(TAttach attach) {
        attachRepository.delete(attach);
    }

}
