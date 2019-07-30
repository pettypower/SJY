package com.cqkj.snail.service;

import java.util.List;

import com.cqkj.snail.domain.TUser;

public interface UserService {
    TUser findByUserName(String loginName);

    List<TUser> findAll();

	TUser findById(TUser user);

    void saveUser(TUser user);

    void editUser(TUser user);

    void deleteUser(TUser user);
}
