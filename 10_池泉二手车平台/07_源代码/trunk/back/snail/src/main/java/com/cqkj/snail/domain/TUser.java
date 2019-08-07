package com.cqkj.snail.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.cqkj.snail.common.base.BaseEntity;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = TUser.T_USER)
public class TUser extends BaseEntity {

    /**
     * 表名映射
     */
    static final String T_USER = "T_USER";

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    // 用户姓名
    private String userName;
    // 登陆名
    @NotBlank(message="用户名不能为空。")
    @Length(max = 100,message = "用户名最多不能超过100个字")
    private String loginName;
    // 登陆密码
    @NotBlank(message="密码不能为空。")
    @Length(max = 100,message = "密码最多不能超过100个字")
    private String loginPassword;
    // 联系电话
    @Length(max = 20,message = "联系电话长度不可超过20")
    private String phone;

    /**
     * 获取用户姓名
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户登陆名
     * @return
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置用户登陆名
     * @param loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取用户登陆密码
     * @return
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * 设置用户登陆密码
     * @param loginPassword
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    /**
     * 获取电话号码
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话号码
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}