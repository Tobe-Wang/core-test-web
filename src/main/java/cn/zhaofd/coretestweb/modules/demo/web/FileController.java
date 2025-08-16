/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.modules.demo.web;

import cn.zhaofd.core.spring.web.RestFileUtil;
import cn.zhaofd.coretestweb.config.PropertyConfig;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

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

    public FileController(@Autowired PropertyConfig propertyConfig) {
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
     * @param files 文件数组
     * @return 状态码
     */
    @RequestMapping(value = "/upload/multiple", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public List<String> uploadMultiple(@RequestParam MultipartFile[] files) {
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
     * @param serverFileName   服务端存储的文件名
     * @param downloadFileName 下载显示的文件名
     * @return 响应实体
     */
    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> download(@RequestParam String serverFileName, @RequestParam(required = false) String downloadFileName) {
        Path filePath = Paths.get(propertyConfig.getValue("server.upload.dirPath"), serverFileName);
        return RestFileUtil.download(filePath.toAbsolutePath().toString(), downloadFileName);
    }

    /**
     * 流式下载文件
     *
     * @param serverFileName   服务端存储的文件名
     * @param downloadFileName 下载显示的文件名
     * @return 流式响应实体
     */
    @GetMapping(value = "/streamDownload", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<StreamingResponseBody> streamDownload(@RequestParam String serverFileName, @RequestParam(required = false) String downloadFileName) {
        Path filePath = Paths.get(propertyConfig.getValue("server.upload.dirPath"), serverFileName);
        return RestFileUtil.streamDownload(filePath.toAbsolutePath().toString(), downloadFileName);
    }
}
