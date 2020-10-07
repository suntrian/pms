package org.sunt.storage.entity;

import org.sunt.entity.BaseEntity;

public class FileEntity extends BaseEntity {

    private static final long serialVersionUID = -269022953824371600L;

    private Integer id;
    private String name;
    private String suffix;
    //byte数
    private Long size;
    private String url;
    private Long crc32;
    private String md5sum;
    private String sha1sum;
    private String serviceId;


}
