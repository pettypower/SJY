package com.cqkj.snail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cqkj.snail.domain.TUser;

public interface UserRepository extends JpaRepository<TUser, String>, JpaSpecificationExecutor<TUser> {
    TUser findByLoginName(String loginName);
}
