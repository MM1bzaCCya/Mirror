// src/main/java/com/example/mirror/service/impl/UsersServiceImpl.java
package com.example.mirror.service.impl;

import com.example.mirror.entity.Users;
import com.example.mirror.mapper.UsersMapper;
import com.example.mirror.service.UsersService;
import com.example.mirror.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public boolean register(Users user) {
        user.setCreated(java.time.LocalDateTime.now());
        user.setPassword(PasswordUtil.encodePassword(user.getPassword()));
        int result = usersMapper.insert(user);
        return result > 0;
    }

    @Override
    public Users login(String username, String password) {
        Users user = usersMapper.findByUsername(username);
        if (user != null && PasswordUtil.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
