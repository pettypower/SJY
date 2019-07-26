package com.cqkj.snail.controller;

import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.cqkj.snail.common.domain.ResponseVO;
import com.cqkj.snail.domain.TUser;
import com.cqkj.snail.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api")
public class LoginController {

    @Resource
    private UserService userService;

    /**
     * 登陆认证方法
     * @return 登陆认证结果
     * @throws Exception 
     */
    @PostMapping("/login")
    public ResponseVO Login(@RequestBody TUser user, HttpServletRequest request) throws Exception {
        ResponseVO response = new ResponseVO();
        response.status(true);
        final String errorMessage = "用户名或密码错误";
        String loginName = user.getLoginName();
        String loginPassword = user.getLoginPassword();
        TUser userInfo = userService.findByUserName(loginName);
        if (userInfo == null) {
            response.status(false);
            response.message(errorMessage);
            response.data(userInfo);
            return response;
        }
        // 判断密码是否相等
        if (!Objects.equals(loginPassword, userInfo.getLoginPassword()) ) {
            
            response.status(false);
            response.message(errorMessage);
            response.data(userInfo);
            return response;
        }

        response.message("登陆成功");
        response.data(userInfo);
        return response;
    }
}
