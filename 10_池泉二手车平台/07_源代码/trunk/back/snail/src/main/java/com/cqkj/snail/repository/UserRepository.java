package com.cqkj.snail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqkj.snail.domain.TUser;

public interface UserRepository extends JpaRepository<TUser, Long> {
    TUser findByLoginName(String loginName);
}
