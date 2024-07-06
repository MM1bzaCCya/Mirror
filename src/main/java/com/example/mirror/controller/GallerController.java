package com.example.mirror.controller;

import com.example.mirror.entity.Galleries;
import com.example.mirror.entity.Images;
import com.example.mirror.mapper.GalleriesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class GallerController {
    @Autowired
    private GalleriesMapper galleriesMapper;

    // 公开相册
    @GetMapping("/api/images/public")
    public List<Galleries> getAllPublicImages() {
        return galleriesMapper.selectAllGalleries();
    }
}
