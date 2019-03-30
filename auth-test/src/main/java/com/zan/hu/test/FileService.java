package com.zan.hu.test;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @version 1.0
 * @Author admin
 * @Date 2019-03-27 09:33
 * @Description todo
 **/
@FeignClient(name = "file-server", configuration = MultipartSupportConfig.class)
public interface FileService {

    @PostMapping(value = "/api/file/upload", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String singleUpload(@RequestPart(value = "file") MultipartFile file);

    @PostMapping(value = "/api/file/uploads", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<String> multiUpload(@RequestPart(value = "files") MultipartFile[] files);

}
