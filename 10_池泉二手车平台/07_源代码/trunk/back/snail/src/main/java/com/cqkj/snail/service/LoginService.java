package com.cqkj.snail.service;

import com.cqkj.snail.domain.LoginResult;

public interface LoginService {
    LoginResult login(String userName, String password);

    void logout();
}
