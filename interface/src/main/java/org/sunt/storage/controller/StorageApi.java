package org.sunt.storage.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.sunt.common.R;
import org.sunt.storage.entity.FileInfoDTO;


@RequestMapping("/storage")
@FeignClient(name = "", url = "")
public interface StorageApi {

    @GetMapping("/ping")
    default R<String> pong() {
        return R.success("pong");
    }

    @PostMapping("")
    R<FileInfoDTO> uploadFile(MultipartFile file);


}
