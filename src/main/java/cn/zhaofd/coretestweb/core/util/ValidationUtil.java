/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.core.util;

import org.springframework.validation.Errors;

import java.util.stream.Collectors;

/**
 * 对象属性验证工具类
 *
 * @apiNote 依赖spring-boot-starter-validation包
 */
public final class ValidationUtil {
    private ValidationUtil() {
    }

    /**
     * 获取对象字段验证错误信息
     *
     * @param errors Errors对象
     * @return 错误信息
     */
    public static String getFieldErrorMsg(Errors errors) {
        // 输入参数验证
        if (errors == null || !errors.hasFieldErrors()) {
            return null;
        }

        return errors.getFieldErrors().stream().map(error -> error.getField() + ":" + error.getDefaultMessage()).collect(Collectors.joining(";"));
    }
}
