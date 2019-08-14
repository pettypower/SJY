package com.cqkj.snail.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import com.cqkj.snail.common.constant.TruckConstants;
import com.cqkj.snail.common.domain.ResponseVO;
import com.cqkj.snail.domain.TTruck;
import com.cqkj.snail.service.TruckService;
import com.cqkj.snail.utils.TruckUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/truckInfo")
public class TruckController {

    @Resource
    TruckService truckService;

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
        if (!TruckUtils.checkPriceFormat(truck.getPrice())) {
            response.status(false);
            response.message("价格参数异常。");
            return response;
        }

        int pageNo = 1;
        int pageSize = 20;
        if (truck.getPageNo() != null) {
            pageNo = truck.getPageNo();
        }
        if (truck.getPageSize() != null) {
            pageSize = truck.getPageSize();
        }

        Specification<TTruck> specification = buildQueryParam(truck);
        // 排序条件(默认价格低优先)
        Order order = Sort.Order.asc("price");
        if (StringUtils.isNotEmpty(truck.getSortCondition())) {
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

        Pageable pageable2 = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<TTruck> pageTruck = truckService.findAll(specification, pageable2);
        response.status(true);
        response.message(MESSAGE);
        response.data(pageTruck);
        return response;
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
                if (StringUtils.isNotEmpty(truck.getPrice())) {
                    String[] priceLst = truck.getPrice().split("-");
                    // 第一个变量
                    String priceFristStr = priceLst[0].trim();
                    // 第二换个变量
                    String priceSecondStr = priceLst[1].trim();
                    // 第一个变量查询条件1
                    Predicate p1 = null;
                    // 第二个变量查询条件2
                    Predicate p2 = null;
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
                if (StringUtils.isNotEmpty(truck.getVehicleType())) {
                    predicate.add(criteriaBuilder.like(root.get("vehicleType"), "%" + truck.getVehicleType() + "%"));
                }

                // 车源品牌查询
                if (StringUtils.isNotEmpty(truck.getVehicleBrand())) {
                    predicate.add(criteriaBuilder.like(root.get("vehicleBrand"), "%" + truck.getVehicleBrand() + "%"));
                }

                // 车系查询
                if (StringUtils.isNotEmpty(truck.getVehicleSystem())) {
                    predicate
                            .add(criteriaBuilder.like(root.get("vehicleSystem"), "%" + truck.getVehicleSystem() + "%"));
                }

                // 看车地点查询
                if (StringUtils.isNotEmpty(truck.getCarWatchingPlace())) {
                    predicate.add(criteriaBuilder.like(root.get("carWatchingPlace"),
                            "%" + truck.getCarWatchingPlace() + "%"));
                }

                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }

        };
    }
}
