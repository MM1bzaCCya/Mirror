// src/main/java/com/example/mirror/mapper/UsersMapper.java
package com.example.mirror.mapper;

import com.example.mirror.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UsersMapper extends BaseMapper<Users> {
    @Select("SELECT * FROM users WHERE username = #{username} LIMIT 1")
    Users findByUsername(String username);
}
