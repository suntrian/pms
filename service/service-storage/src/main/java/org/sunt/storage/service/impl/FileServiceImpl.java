package org.sunt.storage.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sunt.exception.NotExistsException;
import org.sunt.spring.I18n;
import org.sunt.storage.IStorage;
import org.sunt.storage.entity.UploadFile;
import org.sunt.storage.service.FileService;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private final IStorage storage;

    private final I18n i18n;

    @Autowired
    public FileServiceImpl(IStorage storage, I18n i18n) {
        this.storage = storage;
        this.i18n = i18n;
    }

    @Override
    public UploadFile insert(UploadFile fileEntity) {
        return null;
    }

    @Override
    public UploadFile getFileDetail(Integer fileId) {
        UploadFile fileRecord = null;
        if (fileRecord == null) {
            throw new NotExistsException(i18n.message("not_found", i18n.message("file"), fileId));
        }
        return fileRecord;
    }

}
