/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.config;

import cn.zhaofd.core.base.ObjectUtil;
import cn.zhaofd.coretestweb.config.interceptor.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringMvc配置
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    private final PropertyConfig propertyConfig;

    public SpringMvcConfig(@Autowired PropertyConfig propertyConfig) {
        this.propertyConfig = propertyConfig;
    }

    /**
     * 拦截器配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
        // 注册拦截器到Spring MVC机制，返回一个拦截器注册
        InterceptorRegistration ir = registry.addInterceptor(requestInterceptor());
        // 指定拦截匹配模式，限制拦截器拦截请求
        ir.addPathPatterns("/interceptor/*");
        */

        registry.addInterceptor(requestInterceptor()); // requestInterceptor()，Spring会通过CGLIB代理拦截@Bean方法的调用，确保返回单例Bean
    }

    /**
     * RESTFul请求拦截器Bean
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        // RESTFul请求超时的警告日志阈值(毫秒)
        Long warnThreshold = ObjectUtil.convert(propertyConfig.getValue("server.request.timeout.warnThreshold"), Long.class);

        return new RequestInterceptor(warnThreshold);
    }
}
