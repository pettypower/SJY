package com.cqkj.snail.service.impl;


import com.cqkj.snail.domain.LoginResult;
import com.cqkj.snail.service.LoginService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public LoginResult login(String loginName, String loginPassword) {
        LoginResult loginResult = new LoginResult();
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(loginPassword)) {
            loginResult.setLogin(false);
            loginResult.setResult("用户名或者密码为空");
            return loginResult;
        }
        return loginResult;
    }

    @Override
    public void logout() {

	}
}
