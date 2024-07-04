// src/main/java/com/example/mirror/service/impl/UsersServiceImpl.java
package com.example.mirror.Impl;

import com.example.mirror.entity.Users;
import com.example.mirror.mapper.UsersMapper;
import com.example.mirror.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;  // 注入UsersMapper

    @Override
    public boolean register(Users user) {
        user.setCreated(LocalDateTime.now());  // 设置创建时间为当前时间
        int result = usersMapper.insert(user);  // 插入用户数据
        return result > 0;  // 返回是否插入成功
    }
}
