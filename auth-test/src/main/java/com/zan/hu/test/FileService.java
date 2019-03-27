package com.zan.hu.test;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @version 1.0
 * @Author admin
 * @Date 2019-03-27 09:33
 * @Description todo
 **/
@FeignClient(value = "file-server", configuration = MultipartSupportConfig.class)
public interface FileService {

    @PostMapping(value = "/api/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String handleFileUpload(@RequestPart(value = "file") MultipartFile file);

}
