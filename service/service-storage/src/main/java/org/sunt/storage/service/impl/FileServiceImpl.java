package org.sunt.storage.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sunt.exception.NotExistsException;
import org.sunt.spring.I18n;
import org.sunt.storage.IStorage;
import org.sunt.storage.dao.FileDao;
import org.sunt.storage.entity.FileEntity;
import org.sunt.storage.service.FileService;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private final IStorage storage;
    private final FileDao fileDao;
    private final I18n i18n;

    @Autowired
    public FileServiceImpl(IStorage storage, FileDao fileDao, I18n i18n) {
        this.storage = storage;
        this.fileDao = fileDao;
        this.i18n = i18n;
    }

    @Override
    public FileEntity save(FileEntity fileEntity) {
        return fileEntity;
    }

    @Override
    public FileEntity getFileDetail(Integer fileId) {
        FileEntity fileRecord = fileDao.getFileById(fileId);
        if (fileRecord == null) {
            throw new NotExistsException(i18n.message("not_found", i18n.message("file"), fileId));
        }
        return fileRecord;
    }

}
