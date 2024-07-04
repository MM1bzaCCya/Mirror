package com.example.mirror.controller;

import com.example.mirror.entity.Users;
import com.example.mirror.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user) {
        try {
            System.out.println(user);
            if (usersService.register(user)) {
                return ResponseEntity.ok("注册成功");
            } else {
                return ResponseEntity.status(400).body("注册失败，未知错误");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user, HttpSession session) {
        Users loggedInUser = usersService.login(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            session.setAttribute("user", loggedInUser);
            return ResponseEntity.ok("登录成功");
        } else {
            return ResponseEntity.status(401).body("登录失败，用户名或密码错误");
        }
    }
    @GetMapping("/login")
    public String loginPage() {
        // 返回登录页面的视图
        return "login";
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("注销成功");
    }
}
