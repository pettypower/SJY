package com.cqkj.snail.controller;

import java.util.List;

import javax.annotation.Resource;

import com.cqkj.snail.common.domain.ResponseVO;
import com.cqkj.snail.domain.TDict;
import com.cqkj.snail.service.DictService;

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
    @RequestMapping("/list")
    public ResponseVO listDict() {
        ResponseVO response = new ResponseVO();
        List<TDict> dictInfoList = dictService.findAll();
        response.status(true);
        response.message(MESSAGE);
        response.data(dictInfoList);
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
}
