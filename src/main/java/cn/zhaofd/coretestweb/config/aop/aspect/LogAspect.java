/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.config.aop.aspect;

import cn.zhaofd.core.base.ObjectUtil;
import cn.zhaofd.core.json.JacksonUtil;
import cn.zhaofd.coretestweb.config.aop.annotation.LogAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 日志切面
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LogManager.getLogger(LogAspect.class);

    /**
     * 配置日志切点
     */
    @Pointcut("@annotation(cn.zhaofd.coretestweb.config.aop.annotation.LogAction)")
    public void annotationPointCut() {
    }

    /**
     * AOP事后通知
     *
     * @param joinPoint 连接点
     * @param result    通知方法的返回值
     */
    @AfterReturning(pointcut = "annotationPointCut()", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        // 获取所有输入参数
        Object[] args = joinPoint.getArgs();

        // 获取第一个请求参数
        Integer id = null;
        if (args.length > 0 && args[0] instanceof Integer) { // 处理第一个参数
            id = ObjectUtil.convert(args[0], Integer.class);
        }
        // 获取方法对象及方法注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAction logAction = method.getAnnotation(LogAction.class);

        logger.info("【AOP事后通知】类：{}，请求方法：{}，方法说明：{}，请求参数id：{}，结果返回：{}", method.getDeclaringClass().getName(), method.getName(), logAction.value(), id, JacksonUtil.writeValueAsString(result));
    }
}
