package com.cqkj.snail.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.cqkj.snail.common.domain.ResponseVO;
import com.cqkj.snail.domain.TDict;
import com.cqkj.snail.service.DictService;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dictInfo")
public class DictController {

    @Resource
    DictService dictService;

    private static final String MESSAGE = "执行成功";

    /**
     * 字典查询.
     * @return 字典信息列表
     */
    @GetMapping("/list")
    public ResponseVO listDict() {
        ResponseVO response = new ResponseVO();
        List<TDict> dictInfoList = dictService.findAll();
        response.status(true);
        response.message(MESSAGE);
        response.data(dictInfoList);
        return response;
    }
    
    /**
     * 字典分页查询.
     * @return 字典信息列表
     */
    @PostMapping("/page")
    public ResponseVO pageDict(@RequestBody TDict dict) {
        ResponseVO response = new ResponseVO();
        int pageNo = 1;
        int pageSize = 20;
        if (dict.getPageNo() != null) {
            pageNo = dict.getPageNo();
        }
        if (dict.getPageSize() != null) {
            pageSize = dict.getPageSize();
        }
        Specification<TDict> specification = buildQueryParam(dict);
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, Sort.Direction.ASC, "id");
        Page<TDict> pageDict = dictService.findAll(specification, pageable);
        response.status(true);
        response.message(MESSAGE);
        response.data(pageDict);
        return response;
    }

    /**
     * 字典查看;
     * @return
     */
    @PostMapping("/view")
    public ResponseVO viewDict(@RequestBody TDict dict) {
        ResponseVO response = new ResponseVO();
        TDict dictInfo = dictService.findById(dict);
        response.status(true);
        response.message(MESSAGE);
        response.data(dictInfo);
        return response;
    }

    /**
     * 字典添加;
     * @return
     */
    @PostMapping("/save")
    public ResponseVO saveDict(@RequestBody TDict dict) {
        ResponseVO response = new ResponseVO();
        dictService.saveDict(dict);
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
    public ResponseVO editDict(@RequestBody TDict dict) {
        ResponseVO response = new ResponseVO();
        dictService.editDict(dict);
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
    public ResponseVO deleteDict(@RequestBody TDict dict) {
        ResponseVO response = new ResponseVO();
        dictService.deleteDict(dict);
        response.status(true);
        response.message(MESSAGE);
        response.data("");
        return response;
    }

    /**
     * 查询条件
     */
    private Specification<TDict> buildQueryParam(TDict dict) {
        return new Specification<TDict>() {
            // serialVersionUID
			private static final long serialVersionUID = 1L;

            // 重写查询方法
			@Override
			public Predicate toPredicate(Root<TDict> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();
                if (StringUtils.isNotEmpty(dict.getDictCode())) {
                    predicate.add(criteriaBuilder.like(root.get("dictCode"), "%" + dict.getDictCode() + "%"));
                }
                if (StringUtils.isNotEmpty(dict.getDictName())) {
                    predicate.add(criteriaBuilder.like(root.get("dictName"), "%" + dict.getDictName() + "%"));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
			}

        };
    }
}
