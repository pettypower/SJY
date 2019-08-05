package com.cqkj.snail.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.alibaba.fastjson.JSONObject;
import com.cqkj.snail.common.domain.ResponseVO;
import com.cqkj.snail.domain.TTruck;
import com.cqkj.snail.service.TruckService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/truckInfo")
public class TruckController {

    @Resource
    TruckService truckService;

    private static final String MESSAGE = "执行成功";

    /**
     * 车辆查询.
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
     * @return 车辆信息列表
     */
    @PostMapping("/page")
    public ResponseVO pageTruck(@RequestBody TTruck truck) {
        ResponseVO response = new ResponseVO();
        int pageNo = 1;
        int pageSize = 20;
        if (truck.getPageNo() != null) {
            pageNo = truck.getPageNo();
        }
        if (truck.getPageSize() != null) {
            pageSize = truck.getPageSize();
        }
        Specification<TTruck> specification = buildQueryParam(truck);
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, Sort.Direction.ASC, "id");
        Page<TTruck> pageTruck = truckService.findAll(specification, pageable);


        JSONObject returnObj = new JSONObject();
        returnObj.put("total", pageTruck.getTotalElements());
        returnObj.put("content", pageTruck.getContent());


        response.status(true);
        response.message(MESSAGE);
        response.data(returnObj);
        return response;
    }

    /**
     * 车辆查看;
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
     * @return
     */
    @PostMapping("/save")
    public ResponseVO saveTruck(@RequestBody TTruck truck) {
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
     * @return
     */
    @PostMapping("/edit")
    public ResponseVO editTruck(@RequestBody TTruck truck) {
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
     * 查询条件*/
    private Specification<TTruck> buildQueryParam(TTruck truck) {
        return new Specification<TTruck>() {
            // serialVersionUID
			private static final long serialVersionUID = 1L;

            // 重写查询方法
			@Override
			public Predicate toPredicate(Root<TTruck> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();
                if (StringUtils.isNotEmpty(truck.getVehicleBrand())) {
                    predicate.add(criteriaBuilder.like(root.get("vehicleBrand"), "%" + truck.getVehicleBrand() + "%"));
                }
                if (StringUtils.isNotEmpty(truck.getVehicleSystem())) {
                    predicate.add(criteriaBuilder.like(root.get("vehicleSystem"), "%" + truck.getVehicleSystem() + "%"));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
			}

        };
    }
}
