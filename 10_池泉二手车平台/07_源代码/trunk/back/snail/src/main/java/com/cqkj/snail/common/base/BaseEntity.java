package com.cqkj.snail.common.base;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 实体基类
 * @author yusg
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 64)
    private String id;

    /**
     * 创建人
     */
    @Column(length = 64)
    private String createUser;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @Column(length = 64)
    private String updateUser;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateTime;

    /**
     * 页码
     * @return
     */
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer pageNo;

    /**
     * 数据条数
     * @return
     */
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer pageSize;

    /**
     * 获取主键（id）
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键（id）
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取创建人
     * @return
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     * @param createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取创建时间
     * @return
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     * @param createTime
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新人
     * @return
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置更新人
     * @param updateUser
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取更新时间
     * @return
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     * @param updateTime
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取页码
     * 
     * @return
     */
    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * 设置页码
     * @param pageNo
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取页面数据条数
     * @return
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置页面数据条数
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
