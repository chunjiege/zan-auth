package com.zan.hu.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @version 1.0
 * @Author admin
 * @Date 2019-03-27 09:32
 * @Description todo
 **/
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public String singleUpload(@RequestPart(value = "file") MultipartFile file) {
        return fileService.singleUpload(file);
    }

    @PostMapping("/multi")
    public List<String> singleUpload(@RequestParam(value = "files") MultipartFile[] files) {
        return fileService.multiUpload(files);
    }

}
