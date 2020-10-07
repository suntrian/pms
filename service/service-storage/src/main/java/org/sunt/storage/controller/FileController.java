package org.sunt.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.sunt.entity.ResultList;
import org.sunt.storage.dto.FileInfoDTO;
import org.sunt.storage.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController implements StorageApi {

    @Autowired
    private FileService fileService;

    @PostMapping("")
    public ResultList<FileInfoDTO> uploadFile(MultipartFile file) {
        return null;
    }

}
