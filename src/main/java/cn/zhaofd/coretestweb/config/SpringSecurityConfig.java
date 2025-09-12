/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.config;

import cn.zhaofd.coretestweb.modules.demo.dto.SysUser;
import cn.zhaofd.coretestweb.modules.demo.dto.UserRole;
import cn.zhaofd.coretestweb.modules.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring Security配置
 */
@Configuration
public class SpringSecurityConfig {
    private final PropertyConfig propertyConfig;

    public SpringSecurityConfig(@Autowired PropertyConfig propertyConfig) {
        this.propertyConfig = propertyConfig;
    }

    /**
     * 创建密码编码器
     *
     * @return 创建密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        /*
        // 方式一、BCryptPasswordEncoder密码编码器(Spring Security不可逆的加密方法)，加密后密文长度60
        return new BCryptPasswordEncoder();
        */

        // 方式二、Pbkdf2PasswordEncoder密码编码器(Spring Security不可逆的加密方法，通过密码编辑器密钥加密)
        // Spring Security的Pbkdf2PasswordEncoder密码编辑器密钥
        // 16：盐值长度（范围建议8-32，默认推荐16）
        // 310000：迭代次数(<10000易被GPU暴力破解，50000-100000勉强可用，默认推荐310000)，主要影响计算性能(耗时)
        // 配置16,310000    加密后密文长度160
        String secret = propertyConfig.getValue("spring.security.encoder.secret");
        return new Pbkdf2PasswordEncoder(secret, 16, 10000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512);
    }

//    /**
//     * 使用内存用户信息服务，设置登录用户名、密码和角色权限
//     *
//     * @return 内存用户信息服务
//     */
//    @Bean
//    public UserDetailsService userDetailsService(@Autowired PasswordEncoder passwordEncoder) {
//        // 定义角色权限，必须ROLE_开头
//        GrantedAuthority roleUser = () -> "ROLE_USER"; // 用户角色权限
//        GrantedAuthority roleAdmin = () -> "ROLE_ADMIN"; // 管理员角色权限
//
//        // 创建用户列表
//        List<UserDetails> userList = List.of(
//                // 创建普通用户
//                new User("user1", passwordEncoder.encode("123456"), List.of(roleUser)),
//                // 创建管理员用户，赋予多个角色权限
//                new User("admin", passwordEncoder.encode("abcdef"), List.of(roleUser, roleAdmin)));
//        return new InMemoryUserDetailsManager(userList);
//    }

    /**
     * 自定义用户信息服务
     *
     * @param userService 用户服务类
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService(@Autowired UserService userService) {
        return username -> { // 使用Lambda表达式
            // 获取数据库用户信息
            SysUser sysUser = userService.getUserByName(username);
            if (sysUser == null) {
                return null;
            }

            // 角色权限列表
            List<GrantedAuthority> authList = new ArrayList<>();
            // 转换为权限列表
            for (UserRole role : sysUser.getRoleList()) {
                //noinspection Convert2MethodRef
                GrantedAuthority auth = () -> role.getRoleName();
                authList.add(auth);
            }
            // 创建用户详情
            return new User(sysUser.getUserName(), sysUser.getPwd(), authList);
        };
    }
}
