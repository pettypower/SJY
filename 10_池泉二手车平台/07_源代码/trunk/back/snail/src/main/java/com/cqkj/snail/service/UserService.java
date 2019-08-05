package com.cqkj.snail.service;

import java.util.List;

import com.cqkj.snail.domain.TUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface UserService {
    TUser findByLoginName(String loginName);

    List<TUser> findAll();
    
    Page<TUser> findAll(Specification<TUser> specification, Pageable pageable);

	TUser findById(TUser user);

    void saveUser(TUser user);

    void editUser(TUser user);

    void deleteUser(TUser user);
}
