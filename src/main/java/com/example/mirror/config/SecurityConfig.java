// src/main/java/com/example/mirror/config/SecurityConfig.java
package com.example.mirror.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring security settings");

        http
                .csrf(AbstractHttpConfigurer::disable) // 关闭CSRF保护
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/users/**", "/api/images","/images/**").permitAll() // 允许所有人访问注册、登录和查询图片端点
                        .anyRequest().permitAll()// 其他请求需要认证
                )
                .formLogin(formLogin -> formLogin // 使用lambda表达式配置formLogin/
                       .loginPage("/api/users/login") // 指定自定义登录页面
                        .permitAll() // 允许所有人访问登录页面
                );

        logger.info("Security settings configured");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}