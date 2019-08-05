package com.cqkj.snail.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import com.cqkj.snail.common.domain.ResponseVO;
import com.cqkj.snail.domain.TUser;
import com.cqkj.snail.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
     * 用户列表查询.
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
     * 字典分页查询.
     * @return 字典信息列表
     */
    @PostMapping("/page")
    public ResponseVO pageUser(@RequestBody TUser user) {
        ResponseVO response = new ResponseVO();
        int pageNo = 1;
        int pageSize = 20;
        if (user.getPageNo() != null) {
            pageNo = user.getPageNo();
        }
        if (user.getPageSize() != null) {
            pageSize = user.getPageSize();
        }
        Specification<TUser> specification = buildQueryParam(user);
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, Sort.Direction.ASC, "id");
        Page<TUser> pageUser = userService.findAll(specification, pageable);
        response.status(true);
        response.message(MESSAGE);
        response.data(pageUser);
        return response;
    }

    /**
     * 用户查询;
     * @return 用户信息
     */
    @PostMapping("/view")
    public ResponseVO viewUser(@RequestBody TUser user) {
        ResponseVO response = new ResponseVO();
        TUser duserInfo = userService.findById(user);
        response.status(true);
        response.message(MESSAGE);
        response.data(duserInfo);
        return response;
    }

    /**
     * 用户添加;
     * @return
     */
    @PostMapping("/save")
    public ResponseVO saveUser(@Valid@RequestBody TUser user) {
        ResponseVO response = new ResponseVO();
        // 判断用户是否已存在，已存在则返回错误信息
        TUser userInfo = userService.findByLoginName(user.getLoginName());
        if (userInfo != null && StringUtils.isNotEmpty(userInfo.getLoginName())) {
            response.status(false);
            response.message("用户名已存在");
            response.data("");
            return response;
        }
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);
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
        LocalDateTime now = LocalDateTime.now();
        user.setUpdateTime(now);
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

    /**
     * 查询条件
     */
    private Specification<TUser> buildQueryParam(TUser user) {
        return new Specification<TUser>() {
            // serialVersionUID
			private static final long serialVersionUID = 1L;

            // 重写查询方法
			@Override
			public Predicate toPredicate(Root<TUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();
                if (StringUtils.isNotEmpty(user.getLoginName())) {
                    predicate.add(criteriaBuilder.like(root.get("loginName"), "%" + user.getLoginName() + "%"));
                }
                if (StringUtils.isNotEmpty(user.getUserName())) {
                    predicate.add(criteriaBuilder.like(root.get("userName"), "%" + user.getUserName() + "%"));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
			}

        };
    }
}
