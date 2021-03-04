package org.sunt.api.storage.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FileInfoDTO {

    private Integer id;
    private Integer parentId;
    private String fileName;
    private String filePath;
    private Integer fileSize;
    private Integer fileCrc32;
    private String fileSuffix;
    private LocalDateTime createAt;
    private Integer createBy;
    private String createUser;
    private Integer referenced;

}
