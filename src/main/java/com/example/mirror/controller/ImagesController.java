package com.example.mirror.controller;

import com.example.mirror.entity.Images;
import com.example.mirror.mapper.ImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class ImagesController {
    @Autowired
    private ImagesMapper imagesMapper;

    @GetMapping("/images")
    public List<Images> findAllImages(){
        return imagesMapper.selectAllImages();
    }

    @PutMapping("/images")
    public String update(Images images){
        int i = imagesMapper.updateById(images);
        System.out.println(i);
        if(i > 0){
            return "更新成功";
        }else {
            return "更新失败";
        }
    }
}
