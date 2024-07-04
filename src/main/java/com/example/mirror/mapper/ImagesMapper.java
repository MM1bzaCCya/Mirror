package com.example.mirror.mapper;

import com.example.mirror.entity.Images;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImagesMapper extends BaseMapper<Images> {
    @Select("SELECT * FROM images")
    List<Images> selectAllImages();
}
