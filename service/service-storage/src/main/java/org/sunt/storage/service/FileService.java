package org.sunt.storage.service;

import org.sunt.storage.entity.UploadFile;

public interface FileService {


    UploadFile insert(UploadFile fileEntity);

    UploadFile getFileDetail(Integer fileId);
}
