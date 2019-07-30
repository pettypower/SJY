package com.cqkj.snail.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cqkj.snail.domain.TUser;
import com.cqkj.snail.repository.UserRepository;
import com.cqkj.snail.service.UserService;

import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Override
    public TUser findByUserName(String loginName) {
        return userRepository.findByLoginName(loginName);
    }

    @Override
    public List<TUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public TUser findById(TUser user) {
        return userRepository.findById(user.getId()).get();
    }

    @Override
    public void saveUser(TUser user) {
        userRepository.save(user);
    }
    

    @Override
    public void editUser(TUser user) {
        TUser userInfo = userRepository.getOne(user.getId());
        userInfo.setUserName(user.getUserName());
        userInfo.setLoginName(user.getLoginName());
        userInfo.setLoginPassword(user.getLoginPassword());
        userRepository.save(userInfo);
    }

    @Override
    public void deleteUser(TUser user) {
        userRepository.delete(user);
    }

}
