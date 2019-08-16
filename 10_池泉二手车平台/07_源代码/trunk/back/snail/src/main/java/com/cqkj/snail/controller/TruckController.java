package com.cqkj.snail.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import com.alibaba.fastjson.JSONObject;
import com.cqkj.snail.common.constant.TruckConstants;
import com.cqkj.snail.common.domain.ResponseVO;
import com.cqkj.snail.domain.TArea;
import com.cqkj.snail.domain.TDict;
import com.cqkj.snail.domain.TTruck;
import com.cqkj.snail.repository.AreaRepository;
import com.cqkj.snail.service.AreaService;
import com.cqkj.snail.service.DictService;
import com.cqkj.snail.service.TruckService;
import com.cqkj.snail.utils.TruckUtils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.DateUtils;

@RestController
@RequestMapping("/truckInfo")
public class TruckController {

    @Resource
    TruckService truckService;
    @Resource
    DictService dictService;
    @Resource
    AreaRepository areaRepository;
    

    private static final String MESSAGE = "执行成功";

    /**
     * 车辆查询.
     * 
     * @return 车辆信息列表
     */
    @RequestMapping("/list")
    public ResponseVO listTruck() {
        ResponseVO response = new ResponseVO();
        List<TTruck> truckInfoList = truckService.findAll();
        response.status(true);
        response.message(MESSAGE);
        response.data(truckInfoList);
        return response;
    }

    /**
     * 车辆分页查询.
     * 
     * @return 车辆信息列表
     */
    @PostMapping("/page")
    public ResponseVO pageTruck(@RequestBody TTruck truck) {
        ResponseVO response = new ResponseVO();

        // 验证价格区间数据格式
        if (!TruckUtils.checkPriceFormat(truck.getPriceCondition())) {
            response.status(false);
            response.message("价格参数异常。");
            return response;
        }

        int pageNo = 1;
        int pageSize = 20;
        if (truck.getPageNo() != null) {
            pageNo = truck.getPageNo() > 0 ? truck.getPageNo(): 1;
        }
        if (truck.getPageSize() != null) {
            pageSize = truck.getPageSize();
        }

        Specification<TTruck> specification = buildQueryParam(truck);
        // 排序条件(默认价格低优先)
        Order order = Sort.Order.asc(TruckConstants.PRICE);
        if (!StringUtils.isEmpty(truck.getSortCondition())) {
            switch (truck.getSortCondition()) {
            // 价格高优先排序
            case TruckConstants.HIGHT_PRICE_FIRST:
                order = Sort.Order.desc(TruckConstants.PRICE);
                break;
            // 价格低优先排序
            case TruckConstants.LOW_PRICE_FIRST:
                order = Sort.Order.asc(TruckConstants.PRICE);
                break;
            // 最新上架排序
            case TruckConstants.SHELF_TIME:
                order = Sort.Order.asc(TruckConstants.CREATE_TIME);
                break;
            // 里程低优先排序
            case TruckConstants.LOW_MILEAGE_FIRST:
                order = Sort.Order.asc(TruckConstants.MILEAGE);
                break;

            default:
                break;
            }
        }
        Sort sort = new Sort(order);

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<TTruck> pageTruck = truckService.findAll(specification, pageable);

        Page<JSONObject> pageHandle = new PageImpl<>(jsonHandle(pageTruck.getContent()), pageable,
                pageTruck.getTotalElements());
        response.status(true);
        response.message(MESSAGE);
        response.data(pageHandle);
        return response;
    }

    /**
     * 返回数据二次处理
     * 
     * @return 车辆信息列表
     */
    private List<JSONObject> jsonHandle(List<TTruck> truckLst) {

        List<JSONObject> returnLst = new ArrayList<>();

        for (TTruck truck : truckLst) {
            // 把车源对象转换成jsonObject对象
            JSONObject truckJson = JSONObject.parseObject(JSONObject.toJSONString(truck));
            // 车型
            if (!StringUtils.isEmpty(truckJson.get("vehicleType"))) {
                // 查询车型对应的字典数据
                TDict dict = dictService.findByCode(truckJson.get("vehicleType").toString());
                if (dict != null) {
                    // 设置车型内容
                    truck.setVehicleTypeContent(dict.getDictName());
                    truckJson.put("vehicleTypeContent", dict.getDictName());
                } else {
                    truckJson.put("vehicleTypeContent", "");
                }
            } else {
                truckJson.put("vehicleType", "");
                truckJson.put("vehicleTypeContent", "");
            }
            // 发动机品牌
            if (!StringUtils.isEmpty(truckJson.get("engineBrand"))) {
                // 查询对应字典数据
                TDict dict = dictService.findByCode(truckJson.get("engineBrand").toString());
                if (dict != null) {
                    // 设置发动机品牌内容
                    truck.setEngineBrandContent(dict.getDictName());
                    truckJson.put("engineBrandContent", dict.getDictName());
                } else {
                    truckJson.put("engineBrandContent", "");
                }
            } else {
                truckJson.put("engineBrand", "");
                truckJson.put("engineBrandContent", "");
            }

            // 燃油类型
            if (!StringUtils.isEmpty(truckJson.get("fuelType"))) {
                // 查询对应字典数据
                TDict dict = dictService.findByCode(truckJson.get("fuelType").toString());
                if (dict != null) {
                    // 设置燃油类型内容
                    truck.setFuelTypeContent(dict.getDictName());
                    truckJson.put("fuelTypeContent", dict.getDictName());
                } else {
                    truckJson.put("fuelTypeContent", "");
                }
            } else {
                truckJson.put("fuelType", "");
                truckJson.put("fuelTypeContent", "");
            }
            // 排放标准
            if (!StringUtils.isEmpty(truckJson.get("emissionStandard"))) {
                // 查询对应字典数据
                TDict dict = dictService.findByCode(truckJson.get("emissionStandard").toString());
                if (dict != null) {
                    // 设置排放标准内容
                    truck.setEmissionStandardContent(dict.getDictName());
                    truckJson.put("emissionStandardContent", dict.getDictName());
                } else {
                    truckJson.put("emissionStandardContent", "");
                }
            } else {
                truckJson.put("emissionStandard", "");
                truckJson.put("emissionStandardContent", "");
            }
            // 车辆品牌
            if (!StringUtils.isEmpty(truckJson.get("vehicleBrand"))) {
                // 查询对应字典数据
                TDict dict = dictService.findByCode(truckJson.get("vehicleBrand").toString());
                if (dict != null) {
                    // 设置车辆品牌内容
                    truck.setVehicleBrandContent(dict.getDictName());
                    truckJson.put("vehicleBrandContent", dict.getDictName());
                } else {
                    truckJson.put("vehicleBrandContent", "");
                }
            } else {
                truckJson.put("vehicleBrand", "");
                truckJson.put("vehicleBrandContent", "");
            }
            // 车系
            if (!StringUtils.isEmpty(truckJson.get("vehicleSystem"))) {
                // 查询对应字典数据
                TDict dict = dictService.findByCode(truckJson.get("vehicleSystem").toString());
                if (dict != null) {
                    // 设置车系内容
                    truck.setVehicleSystemContent(dict.getDictName());
                    truckJson.put("vehicleSystemContent", dict.getDictName());
                } else {
                    truckJson.put("vehicleSystemContent", "");
                }
            } else {
                truckJson.put("vehicleSystem", "");
                truckJson.put("vehicleSystemContent", "");
            }
            // 颜色
            if (!StringUtils.isEmpty(truckJson.get("colour"))) {
                // 查询对应字典数据
                TDict dict = dictService.findByCode(truckJson.get("colour").toString());
                if (dict != null) {
                    // 设置颜色内容
                    truck.setColourContent(dict.getDictName());
                    truckJson.put("colourContent", dict.getDictName());
                } else {
                    truckJson.put("colourContent", "");
                }
            } else {
                truckJson.put("colour", "");
                truckJson.put("colourContent", "");
            }
            // 驱动方式
            if (!StringUtils.isEmpty(truckJson.get("drivingMode"))) {
                // 查询对应字典数据
                TDict dict = dictService.findByCode(truckJson.get("drivingMode").toString());
                if (dict != null) {
                    // 设置驱动方式内容
                    truck.setDrivingModeContent(dict.getDictName());
                    truckJson.put("drivingModeContent", dict.getDictName());
                } else {
                    truckJson.put("drivingModeContent", "");
                }
            } else {
                truckJson.put("drivingMode", "");
                truckJson.put("drivingModeContent", "");
            }
            // 发布状态
            if (!StringUtils.isEmpty(truckJson.get("status"))) {
                // 查询对应字典数据
                TDict dict = dictService.findByCode(truckJson.get("status").toString());
                if (dict != null) {
                    // 设置发布状态内容
                    truck.setStatusContent(dict.getDictName());
                    truckJson.put("statusContent", dict.getDictName());
                } else {
                    truckJson.put("statusContent", "");
                }
            } else {
                truckJson.put("status", "");
                truckJson.put("statusContent", "");
            }
            // 看车地点
            if (!StringUtils.isEmpty(truckJson.get("carWatchingPlace"))) {
                // 查询对应字典数据
                List<TArea> areaLst = areaRepository.findByAdcode(truckJson.get("carWatchingPlace").toString());
                if (areaLst != null) {
                    // 设置看车地点内容
                    truck.setCarWatchingPlaceContent(areaLst.get(0).getName());
                    truckJson.put("carWatchingPlaceContent", areaLst.get(0).getName());
                } else {
                    truckJson.put("carWatchingPlaceContent", "");
                }
            } else {
                truckJson.put("carWatchingPlace", "");
                truckJson.put("carWatchingPlaceContent", "");
            }

            // 时间类型格式调整
            truckJson.put("createTime", truck.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            truckJson.put("updateTime", truck.getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            // 马力
            if (StringUtils.isEmpty(truckJson.get("horsePower"))) {
                truckJson.put("horsePower", "");
            }

            // 图片附件
            if (StringUtils.isEmpty(truckJson.get("attachmentPic"))) {
                truckJson.put("attachmentPic", "");
            }

            returnLst.add(truckJson);
        }
        return returnLst;
    }

    /**
     * 车辆查看;
     * 
     * @return
     */
    @PostMapping("/view")
    public ResponseVO viewTruck(@RequestBody TTruck truck) {
        ResponseVO response = new ResponseVO();
        TTruck truckInfo = truckService.findById(truck);
        response.status(true);
        response.message(MESSAGE);
        response.data(truckInfo);
        return response;
    }

    /**
     * 车辆添加;
     * 
     * @return
     */
    @PostMapping("/save")
    public ResponseVO saveTruck(@Valid @RequestBody TTruck truck) {
        ResponseVO response = new ResponseVO();
        LocalDateTime now = LocalDateTime.now();
        truck.setCreateTime(now);
        truck.setUpdateTime(now);
        truckService.saveTruck(truck);
        response.status(true);
        response.message(MESSAGE);
        response.data("");
        return response;
    }

    /**
     * 车辆编辑;
     * 
     * @return
     */
    @PostMapping("/edit")
    public ResponseVO editTruck(@Valid @RequestBody TTruck truck) {
        ResponseVO response = new ResponseVO();
        LocalDateTime now = LocalDateTime.now();
        truck.setUpdateTime(now);
        truckService.editTruck(truck);
        response.status(true);
        response.message(MESSAGE);
        response.data("");
        return response;
    }

    /**
     * 车辆删除;
     * 
     * @return
     */
    @PostMapping("/delete")
    public ResponseVO deleteTruck(@RequestBody TTruck truck) {
        ResponseVO response = new ResponseVO();
        truckService.deleteTruck(truck);
        response.status(true);
        response.message(MESSAGE);
        response.data("");
        return response;
    }

    /**
     * 查询条件;
     * 
     * @return
     */
    private Specification<TTruck> buildQueryParam(TTruck truck) {
        return new Specification<TTruck>() {
            // serialVersionUID
            private static final long serialVersionUID = 1L;

            // 重写查询方法
            @Override
            public Predicate toPredicate(Root<TTruck> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();
                // 价格属性不为空的场合
                if (!StringUtils.isEmpty(truck.getPriceCondition())) {
                    String[] priceLst = truck.getPriceCondition().split("-");
                    // 第一个变量
                    String priceFristStr = priceLst[0].trim();
                    // 第二换个变量
                    String priceSecondStr = priceLst[1].trim();
                    // 第一个变量不是0的场合
                    if (!TruckConstants.ZERO.equals(priceFristStr)) {
                        predicate.add(criteriaBuilder.gt(root.get("price").as(Integer.class),
                                Integer.parseInt(priceFristStr)));
                    }
                    // 第二个变量不是无限制的场合(NO_LIMIT)
                    if (!TruckConstants.NO_LIMIT.equals(priceSecondStr)) {
                        predicate.add(criteriaBuilder.le(root.get("price").as(Integer.class),
                                Integer.parseInt(priceSecondStr)));
                    }
                }

                // 车型查询
                if (!StringUtils.isEmpty(truck.getVehicleType())) {
                    predicate.add(criteriaBuilder.like(root.get("vehicleType"), "%" + truck.getVehicleType() + "%"));
                }

                // 车源品牌查询
                if (!StringUtils.isEmpty(truck.getVehicleBrand())) {
                    predicate.add(criteriaBuilder.like(root.get("vehicleBrand"), "%" + truck.getVehicleBrand() + "%"));
                }

                // 车系查询
                if (!StringUtils.isEmpty(truck.getVehicleSystem())) {
                    predicate
                            .add(criteriaBuilder.like(root.get("vehicleSystem"), "%" + truck.getVehicleSystem() + "%"));
                }

                // 看车地点查询
                if (!StringUtils.isEmpty(truck.getCarWatchingPlace())) {
                    predicate.add(criteriaBuilder.like(root.get("carWatchingPlace"),
                            "%" + truck.getCarWatchingPlace() + "%"));
                }

                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
