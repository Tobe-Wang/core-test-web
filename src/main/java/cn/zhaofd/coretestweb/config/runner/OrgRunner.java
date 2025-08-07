/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.config.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 系统启动时执行
 */
@Component
@Order(2)
public class OrgRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(OrgRunner.class);

    /**
     * 启动时执行
     *
     * @param args 启动参数
     */
    @Override
    public void run(ApplicationArguments args) {
        logger.info("程序启动后OrgRunner执行的代码");
    }
}
