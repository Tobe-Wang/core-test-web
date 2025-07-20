/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.demo.web;

import cn.zhaofd.core.base.StringUtil;
import cn.zhaofd.core.net.exception.HttpException;
import cn.zhaofd.core.spring.web.RestFileUtil;
import cn.zhaofd.coretestweb.config.PropertyConfig;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传下载Controller
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private final PropertyConfig propertyConfig;

    @Autowired
    public FileController(PropertyConfig propertyConfig) {
        this.propertyConfig = propertyConfig;
    }

    /**
     * 上传文件
     *
     * @param file    文件
     * @param request HttpServletRequest对象
     * @return 状态码
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public String upload(@RequestParam MultipartFile file, HttpServletRequest request) {
        // 输入参数验证
        if (file == null || file.isEmpty()) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "文件为空");
        }

        /*
        // 上传文件(使用应用相对目录保存)
        String dirPath = RestFileUtil.getRealPath(request, propertyConfig.getValue("server.upload.relativeDir"));
        return RestFileUtil.upload(file, dirPath);
        */

        return RestFileUtil.upload(file, propertyConfig.getValue("server.upload.dirPath"));
    }

    /**
     * 上传多个文件
     *
     * @param files   文件数组
     * @param request HttpServletRequest对象
     * @return 状态码
     */
    @RequestMapping(value = "/upload/multiple", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public List<String> uploadMultiple(@RequestParam MultipartFile[] files, HttpServletRequest request) {
        // 输入参数验证
        if (files == null || files.length == 0) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "文件为空");
        }

        List<String> listResult = new ArrayList<>();
        String uploadDirPath = propertyConfig.getValue("server.upload.dirPath");
        for (MultipartFile file : files) {
            listResult.add(RestFileUtil.upload(file, uploadDirPath));
        }
        return listResult;
    }

    /**
     * 下载文件
     *
     * @param serverFileName   服务端文件名
     * @param downloadFileName 下载文件名
     * @return 文件流
     */
    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> download(@RequestParam String serverFileName, @RequestParam String downloadFileName) {
        // 输入参数验证
        if (StringUtil.isNullOrTrimEmpty(serverFileName)) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "参数serverFileName不能为空");
        }
        if (StringUtil.isNullOrTrimEmpty(downloadFileName)) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "参数downloadFileName不能为空");
        }

        Path filePath = Paths.get(propertyConfig.getValue("server.upload.dirPath"), serverFileName);
        return RestFileUtil.download(filePath.toAbsolutePath().toString(), downloadFileName);
    }
}
