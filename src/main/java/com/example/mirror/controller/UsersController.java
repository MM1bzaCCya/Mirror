// src/main/java/com/example/mirror/controller/UsersController.java
package com.example.mirror.controller;

import com.example.mirror.entity.Users;
import com.example.mirror.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;  // 注入UsersService

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user) {
        boolean success = usersService.register(user);  // 调用注册服务
        if (success) {
            return ResponseEntity.ok("注册成功");  // 注册成功返回200状态和成功消息
        } else {
            return ResponseEntity.status(500).body("注册失败");  // 注册失败返回500状态和失败消息
        }
    }
}
