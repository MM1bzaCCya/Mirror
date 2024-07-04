// src/main/java/com/example/mirror/mapper/UsersMapper.java
package com.example.mirror.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mirror.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UsersMapper extends BaseMapper<Users> {
    @Select("SELECT * FROM users WHERE username = #{username} LIMIT 1")
    Users findByUsername(String username);

    @Select("SELECT * FROM users WHERE email = #{email} LIMIT 1")
    Users findByEmail(String email);
}
