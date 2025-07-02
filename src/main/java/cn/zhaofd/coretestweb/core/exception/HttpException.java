/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.core.exception;

/**
 * http自定义异常类
 */
public class HttpException extends RuntimeException {
    /**
     * http状态码(默认值0)
     */
    private final int statusCode;
    /**
     * 异常信息
     */
    private final String message;

    /**
     * 构造方法
     *
     * @param message 错误信息
     */
    public HttpException(String message) {
        super(message);
        this.statusCode = 0;
        this.message = message;
    }

    /**
     * 构造函数
     *
     * @param statusCode http状态码({@code org.springframework.http.HttpStatus})
     * @param message    错误信息
     */
    public HttpException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * 构造方法（支持异常链）
     *
     * @param statusCode http状态码({@code org.springframework.http.HttpStatus})
     * @param message    错误信息
     * @param cause      异常原因
     */
    public HttpException(int statusCode, String message, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * 获取http状态码
     *
     * @return int http状态码({@code org.springframework.http.HttpStatus})
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * 获取异常信息
     *
     * @return String 异常信息
     */
    public String getMessage() {
        return message;
    }
}
