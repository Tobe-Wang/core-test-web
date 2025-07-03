/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 计划任务配置
 * <br />使用方法：在Bean方法上添加@Scheduled注解来声明该方法是一个计划任务方法
 * <br />@Scheduled(fixedDelay = 5000)  延迟5秒执行，执行完再延迟5秒执行...
 * <br />@Scheduled(fixedRate = 5000)  间隔5秒执行。当超过5秒执行完立即下一执行；当少于5秒执行完等待至5秒再下一执行
 * <br />@Scheduled(cron = "0 28 11 ? * *")  每天11:28执行
 * <br />cron表达式格式：
 * <br />秒   0-59
 * <br />分   0-59
 * <br />时   0-23
 * <br />日   0-31
 * <br />月   0-11
 * <br />周几 可填1-7或SUN/MON/TUE/WED/THU/FRI/SAT
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {
}
