package org.sunt.storage.service;

import org.sunt.storage.entity.FileEntity;

public interface FileService {


    FileEntity save(FileEntity fileEntity);

    FileEntity getFileDetail(Integer fileId);
}
