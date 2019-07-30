package com.cqkj.snail.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cqkj.snail.common.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = TDict.T_DICT)
@Data
@EqualsAndHashCode(callSuper=true) 
public class TDict extends BaseEntity {

    /**
     * 表名映射
     */
    
    static final String T_DICT = "T_DICT";

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    // 父类型Id
    private String parentId;

    // 字典名称
    private String dictName;

    // 字典编码
    private String dictCode;

    // 排序号
    private Integer orderNumber;

}