package com.cqkj.snail.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cqkj.snail.common.base.BaseEntity;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = TTruck.T_TRUCK)
@Data
@EqualsAndHashCode(callSuper=true) 
public class TTruck extends BaseEntity {

    /**
     * 表名映射
     */
    static final String T_TRUCK = "T_TRUCK";
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    // 车辆类型
    private String vehicleType;

    // 里程数
    @Length(max = 100,message = "里程数最多不能超过100个字")
    private String mileage;

    // 发动机品牌
    private String engineBrand;

    // 柴油类型
    private String fuelType;

    // 排放标准
    private String emissionStandard;

    // 车辆品牌
    private String vehicleBrand;

    // 车系
    private String vehicleSystem;

    // 颜色
    private String colour;

    // 马力
    @Length(max = 100,message = "里程数最多不能超过100个字")
    private String horsePower;

    // 驱动方式
    private String drivingMode;

    // 图片附件
    private String attachmentPic;

    // 车辆报价
    @Length(max = 100,message = "车辆报价最多不能超过100个字")
    private String price;

    // 发布状态
    private String status;
}