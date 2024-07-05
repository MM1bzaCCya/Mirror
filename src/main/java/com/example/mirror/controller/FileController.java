package com.example.mirror.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class FileController {

   @PostMapping("/upload")
   public String upload(MultipartFile photo, HttpServletRequest request)throws IOException {
      System.out.println("文件大小:"+photo.getSize());
      System.out.println(photo.getContentType());

      System.out.println(photo.getOriginalFilename());
      String path = request.getServletContext().getRealPath("/upload/");
      System.out.println(path);
      saveFile(photo, path);
      return "上传成功";
   }

   private void saveFile(MultipartFile photo, String path) throws IOException {
      File Dir = new File(path);
      if (!Dir.exists()) {
         Dir.mkdir();
      }
      File file = new File(path + photo.getOriginalFilename());
      photo.transferTo(file);
   }
}
