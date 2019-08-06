package com.cqkj.snail.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
    @NotBlank(message="字典名称不能为空。")
    private String dictName;

    // 字典编码
    @NotBlank(message="字典编码不能为空。")
    private String dictCode;

    // 排序号
    private Integer orderNumber;

    /**
     * 子类
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId", referencedColumnName = "dictCode")
    private List<TDict> children = new ArrayList<>();

}