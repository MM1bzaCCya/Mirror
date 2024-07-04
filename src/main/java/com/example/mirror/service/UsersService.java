// src/main/java/com/example/mirror/service/UsersService.java
package com.example.mirror.service;

import com.example.mirror.entity.Users;

public interface UsersService {
    // 用户注册方法，返回注册是否成功
    boolean register(Users user);
}
