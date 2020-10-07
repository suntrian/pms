package org.sunt.entity;

import java.time.LocalDateTime;

public abstract class BaseDTO {

    protected LocalDateTime createTime;
    protected LocalDateTime updateTime;

    protected Integer createUserId;
    protected String createUserName;
    protected Integer updateUserId;
    protected String updateUserName;

}
