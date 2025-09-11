/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.time.Instant;

/**
 * RESTFul请求拦截器
 * <br />另需配置SpringMvcConfig
 */
public class RequestInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
    private final Long warnThreshold; // RESTFul请求超时的警告日志阈值(毫秒)

    /**
     * 构造函数
     *
     * @param warnThreshold RESTFul请求超时的警告日志阈值(毫秒)
     */
    public RequestInterceptor(Long warnThreshold) {
        this.warnThreshold = warnThreshold;
    }

    /**
     * Controller方法调用之前拦截器处理
     *
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @param handler  处理器对象
     * @return true拦截器继续往下执行；false请求结束
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        // 配置属性中设置了RESTFul请求超时的警告日志阈值
        if (warnThreshold != null) {
            // 记录请求开始时刻
            request.setAttribute("startInstant", Instant.now());
        }

        return true;
    }

    /**
     * Controller方法调用之后拦截器处理
     *
     * @param request      HttpServletRequest对象
     * @param response     HttpServletResponse对象
     * @param handler      处理器对象
     * @param modelAndView ModelAndView对象
     */
    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, @NonNull ModelAndView modelAndView) {
        // 配置属性中设置了RESTFul请求超时的警告日志阈值
        if (warnThreshold != null) {
            // 计算请求耗时(毫秒)
            Instant endInstant = Instant.now(); // 请求结束时刻
            Instant startInstant = (Instant) request.getAttribute("startInstant"); // 请求开始时刻
            request.removeAttribute("startInstant");
            long millisecond = Duration.between(startInstant, endInstant).toMillis();

            // 请求耗时超过阈值则记录警告日志
            if (millisecond >= warnThreshold) {
                // 获取拦截器拦截的请求方法名称
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                String methodName = handlerMethod.toString();

                logger.warn("【RESTFul请求耗时超过阈值】耗时：{}毫秒，请求方法：{}", millisecond, methodName);
            }
        }
    }

    /**
     * 请求处理完毕，返回给用户之后执行，主要作用是用于清理资源的
     *
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @param handler  处理器对象
     * @param ex       异常对象
     */
    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, @NonNull Exception ex) {
    }
}
