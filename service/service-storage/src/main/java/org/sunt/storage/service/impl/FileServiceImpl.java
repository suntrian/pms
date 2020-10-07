package org.sunt.storage.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.sunt.exception.ResourceNotFoundException;
import org.sunt.spring.I18n;
import org.sunt.storage.IStorage;
import org.sunt.storage.dao.FileDao;
import org.sunt.storage.dto.FileDetailDTO;
import org.sunt.storage.entity.FileEntity;
import org.sunt.storage.service.FileService;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private final IStorage storage;
    private final FileDao fileDao;
    private final I18n i18n;

    @Autowired
    public FileServiceImpl(IStorage storage, FileDao fileDao, MessageSource messageSource) {
        this.storage = storage;
        this.fileDao = fileDao;
        this.i18n = new I18n(messageSource);
    }

    public FileDetailDTO getFileDetail(Integer fileId) {
        FileEntity fileRecord = fileDao.getFileById(fileId);
        if (fileRecord == null) {
            throw new ResourceNotFoundException(i18n.message("resource_not_found", i18n.message("file"), fileId));
        }

        return null;
    }

}
