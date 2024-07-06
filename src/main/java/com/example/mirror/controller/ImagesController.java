// src/main/java/com/example/mirror/controller/ImagesController.java
package com.example.mirror.controller;

import com.example.mirror.entity.Images;
import com.example.mirror.entity.Users;
import com.example.mirror.mapper.ImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class ImagesController {

    @Autowired
    private ImagesMapper imagesMapper;
    @Value("${file.upload-path}")
    private String uploadDir;

    @GetMapping("/api/images")
    public List<Images> findAllImages(){
        return imagesMapper.selectAllImages();
    }

    @PutMapping("/api/images")
    public String update(Images images){
        int i = imagesMapper.updateById(images);
        System.out.println(i);
        if(i > 0){
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    @GetMapping("/api/images/user")
    public List<Images> findImagesByUserId(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user != null) {
            int userId = user.getId();
            return imagesMapper.selectImagesByUserId(userId);
        } else {
            return null;
        }
    }
    /**
     * 处理图片上传请求
     *
     * @param file        上传的图片文件
     * @param description 图片描述
     * @param Public      图片是否公开
     * @param session     HttpSession 对象
     * @return 上传结果信息
     */
    @PostMapping("/api/images/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file,
                              @RequestParam("description") String description,
                              @RequestParam("Public") boolean Public,
                              HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "用户未登录";
        }

        if (file.isEmpty()) {
            return "上传失败，请选择一个文件";
        }

        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        try {
            saveFile(file, newFileName);

            Images image = new Images();
            image.setUserid(user.getId());
            image.setUrl("/images/" + newFileName);
            image.setDescription(description);
            image.setPublic(Public);
            image.setCreated(LocalDateTime.now());

            imagesMapper.insert(image);
            // 如果图片是公开的，则将其插入到 galleries 表中
            copyPublicImageToGalleries(image);

            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
    }
    /**
     * 保存文件到指定目录
     *
     * @param file     上传的文件
     * @param fileName 保存的文件名
     * @throws IOException 发生 I/O 错误时抛出
     */
    private void saveFile(MultipartFile file, String fileName) throws IOException {
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        System.out.println(uploadPath);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Path filePath = uploadPath.resolve(fileName);
        file.transferTo(filePath.toFile());
    }
    /**
     * 检查图片是否公开，如果是公开的且在 galleries 表中不存在，则插入到 galleries 表中
     *
     * @param image 图片记录
     */
    private void copyPublicImageToGalleries(Images image) {
        if (image.getPublic()) { // 使用 Public
            if (imagesMapper.countImageInGalleries(image.getId()) == 0) {
                imagesMapper.insertIntoGalleries(image.getId(), image.getUserid(), image.getUrl());
            }
        }
    }
}


