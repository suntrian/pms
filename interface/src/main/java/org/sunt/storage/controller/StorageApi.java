package org.sunt.storage.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.sunt.common.R;
import org.sunt.storage.entity.FileInfoDTO;


@FeignClient(name = "storage", contextId = "storageApi", path = "/storage")
public interface StorageApi {


    @GetMapping("/hello/{world}")
    default R<String> pong(@PathVariable("world") String world) {
        return R.success("hello " + world);
    }

    @GetMapping("/port")
    R<Integer> getPort();

    @PostMapping("")
    R<FileInfoDTO> uploadFile(MultipartFile file);


}
