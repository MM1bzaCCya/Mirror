// src/main/java/com/example/mirror/controller/UsersController.java
package com.example.mirror.controller;

import com.example.mirror.entity.Users;
import com.example.mirror.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user) {
        boolean success = usersService.register(user);
        if (success) {
            return ResponseEntity.ok("注册成功");
        } else {
            return ResponseEntity.status(500).body("注册失败");
        }
    }

@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody Users user, HttpSession session) {
    Users loggedInUser = usersService.login(user.getUsername(), user.getPassword());
    if (loggedInUser != null) {
        session.setAttribute("user", loggedInUser); // 在session中设置用户信息
        logger.info("用户 {} 登录成功", user.getUsername());
        logger.info("用户信息: {}", loggedInUser);
        return ResponseEntity.ok("登录成功");
    } else {
        logger.info("用户 {} 登录失败", user.getUsername());
        return ResponseEntity.status(401).body("登录失败，用户名或密码错误");
    }
}

    @GetMapping("/session")
    public ResponseEntity<String> getSessionInfo(HttpSession session) {
        // 获取用户信息
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            logger.info("用户未登录");
            return ResponseEntity.status(401).body("用户未登录");
        } else {
            logger.info("用户信息: {}", user);
            // 输出会话信息到控制台
            logger.info("会话 ID: {}", session.getId());
            logger.info("会话创建时间: {}", session.getCreationTime());
            logger.info("会话最后访问时间: {}", session.getLastAccessedTime());
            logger.info("会话最大不活动间隔时间: {}", session.getMaxInactiveInterval());

            // 输出会话中所有属性的名称
            Enumeration<String> attributeNames = session.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String attributeName = attributeNames.nextElement();
                logger.info("会话属性: {} = {}", attributeName, session.getAttribute(attributeName));
            }
            logger.info("用户信息: {}", user);
            return ResponseEntity.ok("用户已登录\n会话信息已输出到控制台");
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user != null) {
            return ResponseEntity.ok("用户已登录");
        } else {
            return ResponseEntity.status(401).body("用户未登录");
        }
    }
}
