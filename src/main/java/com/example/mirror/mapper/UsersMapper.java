package com.example.mirror.mapper;

import com.example.mirror.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper extends BaseMapper<Users> {
    // 继承BaseMapper接口，提供基本的CRUD操作
}
