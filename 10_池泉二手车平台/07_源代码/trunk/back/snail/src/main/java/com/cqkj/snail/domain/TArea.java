package com.cqkj.snail.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.cqkj.snail.common.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = TArea.T_AREA)
@Data
@EqualsAndHashCode(callSuper = true)
public class TArea extends BaseEntity {

    /**
     * 表名映射
     */
    static final String T_AREA = "T_AREA";

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    // 上级ID
    private String parentId;
    // 行政区划分代码
    private String adcode;
    // 行政区名称
    private String name;
    // 经度
    private String longitude;
    // 维度
    private String latitude;
    // 地域等级
    private String areaLevel;

    /**
     * 子类
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId", referencedColumnName = "adcode")
    private List<TArea> children = new ArrayList<>();

}