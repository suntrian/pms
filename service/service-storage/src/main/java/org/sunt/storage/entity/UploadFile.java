package org.sunt.storage.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UploadFile {

    private static final long serialVersionUID = -269022953824371600L;

    private Integer id;
    private Integer parentId;
    private String fileName;
    private String filePath;
    private Integer fileSize;
    private Integer fileCrc32;
    private String fileSuffix;
    private LocalDateTime createAt;
    private Integer createBy;
    private Integer referenced;
    private Integer tenantId;

}
