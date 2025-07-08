/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.config.aop.annotation;

import java.lang.annotation.*;

/**
 * 日志切点的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LogAction {
    /**
     * 日志名称
     * @return String
     */
    public String value();
}
