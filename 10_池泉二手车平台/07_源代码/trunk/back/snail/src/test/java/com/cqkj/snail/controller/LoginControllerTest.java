package com.cqkj.snail.controller;

import com.cqkj.snail.Application;
import com.cqkj.snail.common.domain.ResponseVO;
import com.cqkj.snail.domain.TUser;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootConfiguration
@SpringBootTest
public class LoginControllerTest {

    @Autowired
    private MockHttpServletRequest request;
    
    @Autowired
    private static LoginController loginController;

    private static ConfigurableApplicationContext context;
    
    @BeforeClass
    public static void setUp() {
        context = SpringApplication.run(Application.class, "");
        loginController = (LoginController) context.getBean("loginController");
    }

    @Test
    public void testLogin() throws Exception {
        TUser user = new TUser();
        ResponseVO response = loginController.Login(user, request);
        Assert.assertEquals(response.get("status"), true);
    }
}