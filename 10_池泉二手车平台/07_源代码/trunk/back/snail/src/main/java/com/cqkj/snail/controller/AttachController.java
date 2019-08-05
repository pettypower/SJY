package com.cqkj.snail.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.cqkj.snail.common.domain.ResponseVO;
import com.cqkj.snail.domain.AttachProperty;
import com.cqkj.snail.domain.TAttach;
import com.cqkj.snail.service.AttachService;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/attach")
@CrossOrigin
public class AttachController {

    @Resource
    AttachService attachService;

    @Autowired
    AttachProperty attachProperty;

    private static final String MESSAGE = "执行成功";
    private static final String ERROR_MESSAGE = "上传失败，上传文件为空或者文件不存在。";

    /**
     * 附件查询.
     * @return 附件信息列表
     */
    @GetMapping("/list")
    public ResponseVO listAttach() {
        ResponseVO response = new ResponseVO();
        List<TAttach> attachInfoList = attachService.findAll();
        response.status(true);
        response.message(MESSAGE);
        response.data(attachInfoList);
        return response;
    }

    /**
     * 单个附件上传
     * 
     * @param <Path>
     * @param attach 附件
     * @return
     */
    @PostMapping("/singleUpload")
    public ResponseVO fileUpload(MultipartFile file) {
        ResponseVO response = new ResponseVO();
        // 上传文件不存在或者上传文件为空
        if (Objects.isNull(file) || file.isEmpty()) {
            response.status(false);
            response.message(ERROR_MESSAGE);
            return response;
        }
        try {
            String filePath = null;
            // 根据文件类型选择不同文件存在路径（图片，语音，视频）
            if (file.getContentType().startsWith("image")) {
                filePath = attachProperty.getImagePath();
            } else if (file.getContentType().startsWith("audio")) {
                filePath = attachProperty.getVoicePath();
            } else if (file.getContentType().startsWith("video")) {
                filePath = attachProperty.getVideoPath();
            } else {
                filePath = attachProperty.getOtherFilePath();
            }
            // 分离文件后缀名
            String realName = file.getOriginalFilename();
            String suffix = realName.substring(realName.lastIndexOf("."));
            // 生成UUID文件名
            String saveName = UUID.randomUUID().toString().replace("-", "") + suffix;
            Path path = Paths.get(filePath + saveName);
            // 如果文件路径不存在，则创建该文件路径
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(filePath));
            }
            File saveFile = new File(filePath + saveName);
            // 上传文件写入指定文路径
            file.transferTo(saveFile);
            // 附件信息存入数据库
            TAttach attach = new TAttach();
            attach.setRealName(realName);
            attach.setSaveName(saveName);
            attach.setSavePath(attachProperty.getAttachUrl() + saveName);
            TAttach attachResult = attachService.saveAttach(attach);
            Map<String, String> map = new HashMap<String, String>();
            map.put("attachId", attachResult.getId());
            response.status(true);
            response.message(MESSAGE);
            response.data(map);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            response.status(false);
            response.message(ERROR_MESSAGE);
            response.data("");
            return response;
		}
    }

    /**
     * 多个附件上传
     * 
     * @param <Path>
     * @param attach 附件
     * @return
     */
    @PostMapping("/multiUpload")
    public ResponseVO fileUpload(MultipartFile[] files) {
        ResponseVO response = new ResponseVO();
        // 上传文件不存在或者上传文件为空
        if (Objects.isNull(files) || files.length == 0) {
            response.status(false);
            response.message(ERROR_MESSAGE);
            return response;
        }
        try {
            StringBuilder attachId = new StringBuilder();
            for (MultipartFile file : files) {
                String filePath = null;
                // 根据文件类型选择不同文件存在路径（图片，语音，视频）
                if (file.getContentType().startsWith("image")) {
                    filePath = attachProperty.getImagePath();
                } else if (file.getContentType().startsWith("audio")) {
                    filePath = attachProperty.getVoicePath();
                } else if (file.getContentType().startsWith("video")) {
                    filePath = attachProperty.getVideoPath();
                } else {
                    filePath = attachProperty.getOtherFilePath();
                }
                // 分离文件后缀名
                String realName = file.getOriginalFilename();
                String suffix = realName.substring(realName.lastIndexOf("."));
                // 生成UUID文件名
                String saveName = UUID.randomUUID().toString().replace("-", "") + suffix;
                Path path = Paths.get(filePath + saveName);
                // 如果文件路径不存在，则创建该文件路径
                if (!Files.isWritable(path)) {
                    Files.createDirectories(Paths.get(filePath));
                }
                File saveFile = new File(filePath + saveName);
                // 上传文件写入指定文路径
                file.transferTo(saveFile);
                // 附件信息存入数据库
                TAttach attach = new TAttach();
                attach.setRealName(realName);
                attach.setSaveName(saveName);
                attach.setSavePath(attachProperty.getAttachUrl() + saveName);
                TAttach attachResult = attachService.saveAttach(attach);
                attachId.append(attachResult.getId()).append(",");
            }
            Map<String, String> map = new HashMap<String, String>();
            map.put("attachId", attachId.substring(0, attachId.lastIndexOf(",")));
            response.status(true);
            response.message(MESSAGE);
            response.data(map);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            response.status(false);
            response.message(ERROR_MESSAGE);
            response.data("");
            return response;
		}
    }
    
    /**
     * 附件分页查询.
     * @return 附件信息列表
     */
    @PostMapping("/page")
    public ResponseVO pageAttach(@RequestBody TAttach attach) {
        ResponseVO response = new ResponseVO();
        int pageNo = 1;
        int pageSize = 20;
        if (attach.getPageNo() != null) {
            pageNo = attach.getPageNo();
        }
        if (attach.getPageSize() != null) {
            pageSize = attach.getPageSize();
        }
        Specification<TAttach> specification = buildQueryParam(attach);
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, Sort.Direction.ASC, "id");
        Page<TAttach> pageattach = attachService.findAll(specification, pageable);
        response.status(true);
        response.message(MESSAGE);
        response.data(pageattach);
        return response;
    }

    /**
     * 附件查看;
     * @return
     */
    @PostMapping("/view")
    public ResponseVO viewAttach(@RequestBody TAttach attach) {
        ResponseVO response = new ResponseVO();
        TAttach attachInfo = attachService.findById(attach);
        response.status(true);
        response.message(MESSAGE);
        response.data(attachInfo);
        return response;
    }

    /**
     * 附件添加;
     * @return
     */
    @PostMapping("/save")
    public ResponseVO saveAttach(@RequestBody TAttach attach) {
        ResponseVO response = new ResponseVO();
        attachService.saveAttach(attach);
        response.status(true);
        response.message(MESSAGE);
        response.data("");
        return response;
    }

    /**
     * 附件删除;
     * @return
     */
    @PostMapping("/delete")
    public ResponseVO deleteAttach(@RequestBody TAttach attach) {
        ResponseVO response = new ResponseVO();
        attachService.deleteAttach(attach);
        response.status(true);
        response.message(MESSAGE);
        response.data("");
        return response;
    }

    /**
     * 查询条件
     */
    private Specification<TAttach> buildQueryParam(TAttach attach) {
        return new Specification<TAttach>() {
            // serialVersionUID
            private static final long serialVersionUID = 1L;

            // 重写查询方法
            @Override
            public Predicate toPredicate(Root<TAttach> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();
                if (StringUtils.isNotEmpty(attach.getRealName())) {
                    predicate.add(criteriaBuilder.like(root.get("realName"), "%" + attach.getRealName() + "%"));
                }
                if (StringUtils.isNotEmpty(attach.getSaveName())) {
                    predicate.add(criteriaBuilder.like(root.get("saveName"), "%" + attach.getSaveName() + "%"));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }

        };
    }
}
