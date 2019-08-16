package com.cqkj.snail.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

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

    // 车辆类型内容
    @Transient
    private String vehicleTypeContent;

    // 里程数
    @Length(max = 100,message = "里程数最多不能超过100个字")
    private String mileage;

    // 发动机品牌
    private String engineBrand;

    // 发动机品牌内容
    @Transient
    private String engineBrandContent;

    // 柴油类型
    private String fuelType;

    // 柴油类型内容
    @Transient
    private String fuelTypeContent;

    // 排放标准
    private String emissionStandard;

    // 排放标准内容
    @Transient
    private String emissionStandardContent;

    // 车辆品牌
    private String vehicleBrand;

    // 车辆品牌内容
    @Transient
    private String vehicleBrandContent;

    // 车系
    private String vehicleSystem;

    // 车系内容
    @Transient
    private String vehicleSystemContent;

    // 颜色
    private String colour;

    // 颜色内容
    @Transient
    private String colourContent;

    // 马力
    @Length(max = 100,message = "里程数最多不能超过100个字")
    private String horsePower;

    // 驱动方式
    private String drivingMode;

    // 驱动方式内容
    @Transient
    private String drivingModeContent;

    // 图片附件
    private String attachmentPic;

    // 车辆报价
    @Length(max = 10,message = "车辆报价最多不能超过10位")
    private Integer price;

    // 车辆报价查询条件
    @Transient
    private String priceCondition;

    // 发布状态
    private String status;

    // 发布状态内容
    @Transient
    private String statusContent;

    // 看车地点
    private String carWatchingPlace;

    // 看车地点内容
    @Transient
    private String carWatchingPlaceContent;

    // 排序规则
    @Transient
    private String sortCondition;
}