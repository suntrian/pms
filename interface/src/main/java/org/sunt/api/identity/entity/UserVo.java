package org.sunt.api.identity.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Setter
@Getter
@Accessors(chain = true)
public class UserVo implements Serializable {

    private Integer id;

    private String account;

    private String mobile;

    private String email;

    private String name;

    private String avatar;

}
