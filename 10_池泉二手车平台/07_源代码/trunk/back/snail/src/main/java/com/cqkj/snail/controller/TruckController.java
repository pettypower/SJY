package com.cqkj.snail.controller;

import java.util.List;

import javax.annotation.Resource;

import com.cqkj.snail.common.domain.ResponseVO;
import com.cqkj.snail.domain.TTruck;
import com.cqkj.snail.service.TruckService;

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
     * 字典查询.
     * @return 字典信息列表
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
     * 字典查看;
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
     * 字典添加;
     * @return
     */
    @PostMapping("/save")
    public ResponseVO saveTruck(@RequestBody TTruck truck) {
        ResponseVO response = new ResponseVO();
        truckService.saveTruck(truck);
        response.status(true);
        response.message(MESSAGE);
        response.data("");
        return response;
    }

    /**
     * 字典编辑;
     * @return
     */
    @PostMapping("/edit")
    public ResponseVO editTruck(@RequestBody TTruck truck) {
        ResponseVO response = new ResponseVO();
        truckService.editTruck(truck);
        response.status(true);
        response.message(MESSAGE);
        response.data("");
        return response;
    }

    /**
     * 字典删除;
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
}
