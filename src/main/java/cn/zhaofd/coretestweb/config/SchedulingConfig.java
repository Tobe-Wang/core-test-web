/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 计划任务配置
 * <br />使用方法：在Bean方法上添加@Scheduled注解来声明该方法是一个计划任务方法(建议组合使用@Scheduled+@Async，每次任务触发都独立并行执行；单独使用@Scheduled，所有任务在同一个线程中串行执行)
 * <br />【参数名称】	        【类型】    	【说明】
 * <br />cron	            String	    使用表达式的方式定义任务执行时间
 * <br />zone	            String	    可以通过它设定区域时间
 * <br />fixedDelay	        long	    表示从上一个任务完成到下一个任务开始的间隔，单位为毫秒（ms）
 * <br />fixedDelayString	String	    与fixedDelay相同，只是使用字符串，这样可以使用SpEL来引入配置文件的配置
 * <br />initialDelay	    long	    在IoC容器完成初始化后，首次任务执行延迟时间，单位为毫秒（ms）
 * <br />initialDelayString	String	    与initialDelay相同，只是使用字符串，这样可以使用SpEL来引入配置文件的配置
 * <br />fixedRate	        long	    从上一个任务开始到下一个任务开始的间隔，单位为毫秒（ms）
 * <br />fixedRateString	String	    与fixedRate相同，只是使用字符串，这样可以使用SpEL来引入配置文件的配置
 * <br />timeUnit	        TimeUnit	时间单位，默认值为TimeUnit.MILLISECONDS
 * <br />
 * <br />@Scheduled(fixedDelay = 5000)  延迟5秒执行，执行完再延迟5秒执行...
 * <br />@Scheduled(fixedRate = 5000)  间隔5秒执行。当超过5秒执行完立即下一执行；当少于5秒执行完等待至5秒再下一执行
 * <br />
 * <br />@Scheduled(cron = "0 28 11 ? * *")  每天11:28执行
 * <br />cron表达式格式：“秒 分 时 天 月 星期 年”
 * <br />【通配符】	【描述】
 * <br />*	        表示任意值
 * <br />?	        不指定值，用于处理天和星期的定义冲突
 * <br />-	        指定时间区间
 * <br />/	        指定时间间隔执行
 * <br />L	        最后的
 * <br />#	        第几个
 * <br />,	        列举多个项
 * <br />
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
