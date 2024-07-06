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

            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
    }

    private void saveFile(MultipartFile file, String fileName) throws IOException {
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        System.out.println(uploadPath);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Path filePath = uploadPath.resolve(fileName);
        file.transferTo(filePath.toFile());
    }
}

