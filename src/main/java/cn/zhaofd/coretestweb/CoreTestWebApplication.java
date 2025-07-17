/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * 启动类
 * <br />exclude=关闭特定的自动配置
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class})
public class CoreTestWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreTestWebApplication.class, args);
    }
}
