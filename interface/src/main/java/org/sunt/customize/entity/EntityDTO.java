package org.sunt.customize.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EntityDTO implements Serializable {

    private static final long serialVersionUID = -7876042328728508113L;
    private Integer id;
    private String name;
    private String label;
    private String comment;

}
