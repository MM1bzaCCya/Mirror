package com.example.mirror.mapper;

import com.example.mirror.entity.Images;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImagesMapper extends BaseMapper<Images> {
    @Select("SELECT * FROM images")
    List<Images> selectAllImages();
    @Select("SELECT * FROM images WHERE userid = #{userId}")
    List<Images> selectImagesByUserId(int userId);

    // 查询所有公开的图片
    @Select("SELECT id, userid, url FROM images WHERE public = 1")
    List<Images> selectPublicImages();

    // 查询某图片在 galleries 表中的记录数量
    @Select("SELECT COUNT(*) FROM galleries WHERE imageid = #{imageid}")
    int countImageInGalleries(@Param("imageid") int imageid);

    // 将图片插入到 galleries 表中
    @Insert("INSERT INTO galleries (imageid, userid, url) VALUES (#{imageid}, #{userid}, #{url})")
    void insertIntoGalleries(@Param("imageid") int imageid, @Param("userid") int userid, @Param("url") String url);
}

