package com.hfut.test.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {
    @RequestMapping("/")
    public String edit() {
        return "summernote";
    }

    @ResponseBody
    @RequestMapping("/img")
    public Map<String, Object> uploadImgQiniu(@RequestParam("files") MultipartFile
                                                     file) throws IOException {
        Map<String,Object> map = new HashMap();
        if(!file.isEmpty()) {
            System.out.println(file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
            // 获取后缀
            //String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 文件上传的路径
            String filePath = "E:/";
            // fileName处理
            fileName = filePath+fileName;
            // 文件对象
            File dest = new File(fileName);
            // 创建路径
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();
            }
            try {
                //保存文件
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        map.put("filename", "/images/"+file.getOriginalFilename());
        return map;
    }
}
