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
    private UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user) {
        try {
            boolean success = usersService.register(user);
            if (success) {
                return ResponseEntity.ok("注册成功");
            } else {
                return ResponseEntity.status(500).body("注册失败");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        Users loggedInUser = usersService.login(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            return ResponseEntity.ok("登录成功");
        } else {
            return ResponseEntity.status(401).body("登录失败，用户名或密码错误");
        }
    }

    @GetMapping("/check-username")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username) {
        boolean isTaken = usersService.isUsernameTaken(username);
        return ResponseEntity.ok(isTaken);
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        boolean isTaken = usersService.isEmailTaken(email);
        return ResponseEntity.ok(isTaken);
    }
}
