package com.cqkj.snail.controller;

import java.util.List;

import javax.annotation.Resource;

import com.cqkj.snail.common.domain.ResponseVO;
import com.cqkj.snail.domain.TUser;
import com.cqkj.snail.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/userInfo")
public class UserController {

    @Resource
    UserService userService;

    private static final String MESSAGE = "执行成功";

    /**
     * 用户查询.
     * @return 用户信息列表
     */
    @RequestMapping("/list")
    public ResponseVO listUser() {
        ResponseVO response = new ResponseVO();
        List<TUser> userInfoList = userService.findAll();
        response.status(true);
        response.message(MESSAGE);
        response.data(userInfoList);
        return response;
    }

    /**
     * 用户添加;
     * @return
     */
    @PostMapping("/save")
    public ResponseVO saveUser(@RequestBody TUser user) {
        ResponseVO response = new ResponseVO();
        userService.saveUser(user);
        response.status(true);
        response.message(MESSAGE);
        response.data("");
        return response;
    }

    /**
     * 用户编辑;
     * @return
     */
    @PostMapping("/edit")
    public ResponseVO editUser(@RequestBody TUser user) {
        ResponseVO response = new ResponseVO();
        userService.editUser(user);
        response.status(true);
        response.message(MESSAGE);
        response.data("");
        return response;
    }

    /**
     * 用户删除;
     * @return
     */
    @PostMapping("/delete")
    public ResponseVO deleteUser(@RequestBody TUser user) {
        ResponseVO response = new ResponseVO();
        userService.deleteUser(user);
        response.status(true);
        response.message(MESSAGE);
        response.data("");
        return response;
    }
}
