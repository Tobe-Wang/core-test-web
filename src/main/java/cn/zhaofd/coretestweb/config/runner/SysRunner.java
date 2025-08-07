/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.config.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 系统启动时执行
 */
@Component
@Order(1)
public class SysRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(SysRunner.class);

    /**
     * 启动时执行
     *
     * @param args 启动参数
     */
    @Override
    public void run(String... args) {
        logger.info("程序启动后SysRunner执行的代码");
    }
}
