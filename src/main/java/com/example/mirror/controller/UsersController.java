// src/main/java/com/example/mirror/controller/UsersController.java
package com.example.mirror.controller;

import com.example.mirror.entity.Users;
import com.example.mirror.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
public class UsersController {

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
            return ResponseEntity.ok("登录成功");
        } else {
            return ResponseEntity.status(401).body("登录失败，用户名或密码错误");
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
