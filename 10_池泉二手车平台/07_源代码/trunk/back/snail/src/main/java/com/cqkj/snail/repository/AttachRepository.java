package com.cqkj.snail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cqkj.snail.domain.TAttach;

public interface AttachRepository extends JpaRepository<TAttach, String>, JpaSpecificationExecutor<TAttach> {}
