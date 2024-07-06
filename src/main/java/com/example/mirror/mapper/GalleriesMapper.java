// src/main/java/com/example/mirror/mapper/GalleriesMapper.java
package com.example.mirror.mapper;

import com.example.mirror.entity.Galleries;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GalleriesMapper {

    @Select("SELECT * FROM galleries")
    List<Galleries> selectAllGalleries();
}
