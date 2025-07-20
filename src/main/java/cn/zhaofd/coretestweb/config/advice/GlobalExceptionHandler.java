/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.config.advice;

import cn.zhaofd.core.net.exception.HttpException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局Controller异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 统一处理HttpException
     *
     * @param e HttpException
     * @return ResponseEntity 响应
     */
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<String> handleHttpException(HttpException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(e.getStatusCode()));
    }
}
