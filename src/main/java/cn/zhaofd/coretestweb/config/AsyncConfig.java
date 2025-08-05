/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.config;

import cn.zhaofd.core.base.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 异步任务多线程配置
 * <br />使用方法：在Bean方法上添加@Async注解来声明该方法是一个异步方法
 * <br />注意：异步方法和调用方法要在不同的类中，如果在同一类中是没有效果的。
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    private final PropertyConfig propertyConfig;

    public AsyncConfig(@Autowired PropertyConfig propertyConfig) {
        this.propertyConfig = propertyConfig;
    }

    @Override
    @NonNull
    public Executor getAsyncExecutor() {
        // 获取配置文件中的参数
        Integer corePoolSize = ObjectUtil.convert(propertyConfig.getValue("server.thread.corePoolSize"), Integer.class); // 核心线程数
        Integer maxPoolSize = ObjectUtil.convert(propertyConfig.getValue("server.thread.maxPoolSize"), Integer.class); // 最大线程数
        Integer queueCapacity = ObjectUtil.convert(propertyConfig.getValue("server.thread.queueCapacity"), Integer.class); // 线程队列大小
        Integer keepAliveSeconds = ObjectUtil.convert(propertyConfig.getValue("server.thread.keepAliveSeconds"), Integer.class); // 线程最大空闲时间(秒)，超过这个时间就回收该线程

        // 基于线程池的TaskExecutor
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        if (corePoolSize != null) {
            taskExecutor.setCorePoolSize(corePoolSize); // 核心线程数
        }
        if (maxPoolSize != null) {
            taskExecutor.setMaxPoolSize(maxPoolSize); // 最大线程数
        }
        if (queueCapacity != null) {
            taskExecutor.setQueueCapacity(queueCapacity); // 线程队列大小
        }
        if (keepAliveSeconds != null) {
            taskExecutor.setKeepAliveSeconds(keepAliveSeconds); // 线程最大空闲时间(秒)，超过这个时间就回收该线程
        }
        taskExecutor.initialize();

        return taskExecutor;
    }
}
