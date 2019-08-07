package com.cqkj.snail.service;

import java.util.List;

import com.cqkj.snail.domain.TAttach;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface AttachService {

    List<TAttach> findAll();

    Page<TAttach> findAll(Specification<TAttach> specification, Pageable pageable);

    TAttach findById(TAttach attach);

    TAttach findById(String attachId);

    TAttach saveAttach(TAttach attach);

    void deleteAttach(TAttach attach);
}
