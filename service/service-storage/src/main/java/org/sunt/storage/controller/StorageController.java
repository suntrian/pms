package org.sunt.storage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.sunt.api.storage.controller.StorageApi;
import org.sunt.api.storage.entity.FileInfoDTO;
import org.sunt.common.mvc.R;
import org.sunt.storage.entity.UploadFile;
import org.sunt.storage.service.FileService;

@Slf4j
@RestController
@RefreshScope
@RequestMapping("/storage")
public class StorageController implements StorageApi {

    @Value("${pms.abcd}")
    private Integer port;

    @Autowired
    private FileService fileService;


    @Override
    public R<Integer> getPort() {
        return R.success(this.port);
    }

    public R<FileInfoDTO> uploadFile(MultipartFile file) {
        log.info("上传文件{}", file.getOriginalFilename());
        fileService.insert(new UploadFile());
        return R.success(null);
    }

}
