package com.cqkj.snail.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqkj.snail.common.domain.ResponseVO;
import com.cqkj.snail.domain.TArea;
import com.cqkj.snail.service.AreaService;
import com.fasterxml.jackson.annotation.JsonCreator;

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
@RequestMapping("/AreaInfo")
public class AreaController {

    @Resource
    AreaService areaService;

    private static final String MESSAGE = "执行成功";

    /**
     * 字典查询.
     * 
     * @return 字典信息列表
     */
    @RequestMapping("/list")
    public ResponseVO listArea() {
        ResponseVO response = new ResponseVO();
        List<TArea> areaInfoList = areaService.findAll();
        response.status(true);
        response.message(MESSAGE);
        response.data(areaInfoList);
        return response;
    }

    /**
     * 字典查询.
     * 
     * @return 字典信息列表
     */
    @RequestMapping("/listByAdcode")
    public ResponseVO listByAdcode(@RequestBody TArea area) {
        ResponseVO response = new ResponseVO();
        List<TArea> areaInfoList = areaService.findAllByAdcode(area);

        JSONArray returnJay = (JSONArray) JSONArray.toJSON(areaInfoList);
        System.out.println("returnJay :" + returnJay);

        response.status(true);
        response.message(MESSAGE);
        response.data(JSONArray.parseArray(returnJay.toString()));
        // response.data(areaInfoList);
        return response;
    }

    /**
     * 字典分页查询.
     * @return 字典信息列表
     */
    @PostMapping("/page")
    public ResponseVO pageArea(@RequestBody TArea area) {
        ResponseVO response = new ResponseVO();
        int pageNo = 1;
        int pageSize = 20;
        if (area.getPageNo() != null) {
            pageNo = area.getPageNo();
        }
        if (area.getPageSize() != null) {
            pageSize = area.getPageSize();
        }
        Specification<TArea> specification = buildQueryParam(area);
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, Sort.Direction.ASC, "id");
        Page<TArea> pageArea = areaService.findAll(specification, pageable);
        response.status(true);
        response.message(MESSAGE);
        response.data(pageArea);
        return response;
    }

    /**
     * 字典查看;
     * @return
     */
    @PostMapping("/view")
    public ResponseVO viewArea(@RequestBody TArea area) {
        ResponseVO response = new ResponseVO();
        TArea areaInfo = areaService.findById(area);
        response.status(true);
        response.message(MESSAGE);
        response.data(areaInfo);
        return response;
    }

    /**
     * 字典添加;
     * @return
     */
    @PostMapping("/save")
    public ResponseVO saveArea(@RequestBody TArea area) {
        ResponseVO response = new ResponseVO();
        areaService.saveArea(area);
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
    public ResponseVO editArea(@RequestBody TArea area) {
        ResponseVO response = new ResponseVO();
        areaService.ediTArea(area);
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
    public ResponseVO deleteArea(@RequestBody TArea area) {
        ResponseVO response = new ResponseVO();
        areaService.deleteArea(area);
        response.status(true);
        response.message(MESSAGE);
        response.data("");
        return response;
    }

    /**
     * 查询条件*/
    private Specification<TArea> buildQueryParam(TArea area) {
        return new Specification<TArea>() {
            // serialVersionUID
			private static final long serialVersionUID = 1L;

            // 重写查询方法
			@Override
			public Predicate toPredicate(Root<TArea> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();
                if (StringUtils.isNotEmpty(area.getAdcode())) {
                    predicate.add(criteriaBuilder.like(root.get("adcode"), "%" + area.getAdcode() + "%"));
                }
                if (StringUtils.isNotEmpty(area.getAreaLevel())) {
                    predicate.add(criteriaBuilder.like(root.get("areaLevel"), "%" + area.getAreaLevel() + "%"));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
			}

        };
    }
}
