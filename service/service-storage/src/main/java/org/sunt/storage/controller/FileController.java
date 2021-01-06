package org.sunt.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.sunt.common.R;
import org.sunt.storage.entity.FileInfoDTO;
import org.sunt.storage.service.FileService;

@RestController
public class FileController implements StorageApi {

    @Autowired
    private FileService fileService;

    public R<FileInfoDTO> uploadFile(MultipartFile file) {
        return null;
    }

}
