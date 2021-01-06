package org.sunt.storage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.sunt.common.R;
import org.sunt.storage.entity.FileEntity;
import org.sunt.storage.entity.FileInfoDTO;
import org.sunt.storage.service.FileService;

@Slf4j
@RestController
public class FileController implements StorageApi {

    @Autowired
    private FileService fileService;

    public R<FileInfoDTO> uploadFile(MultipartFile file) {
        log.info("上传文件{}", file.getOriginalFilename());
        fileService.save(new FileEntity());
        return R.success(null);
    }

}
