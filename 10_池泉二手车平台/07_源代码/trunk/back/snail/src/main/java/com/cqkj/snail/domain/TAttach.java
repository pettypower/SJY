package com.cqkj.snail.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cqkj.snail.common.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = TAttach.T_ATTACH)
@Data
@EqualsAndHashCode(callSuper=true) 
public class TAttach extends BaseEntity {

    /**
     * 表名映射
     */
    
    static final String T_ATTACH = "T_ATTACH";

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    // 文件真名
    private String realName;

    // 文件保存名
    private String saveName;

    // 文件保存地址
    private String savePath;

    // 附件大小
    private String attachSize;

}