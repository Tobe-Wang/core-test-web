/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * <br />exclude=关闭特定的自动配置(推荐方法：通过yaml的spring.autoconfigure.exclude配置)
 */
@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class})
public class CoreTestWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreTestWebApplication.class, args);
    }
}
