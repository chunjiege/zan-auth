package com.zan.hu.test;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;
import java.io.OutputStream;

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
    public String singleFileUpload(MultipartFile file) {
        MultipartFile multi = null;
        try {
            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                    MediaType.ALL_VALUE, true, file.getOriginalFilename());
            InputStream input = file.getInputStream();
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);
            multi = new CommonsMultipartFile(fileItem);
        } catch (Exception e) {

        }
        return fileService.handleFileUpload(multi);
    }

}
