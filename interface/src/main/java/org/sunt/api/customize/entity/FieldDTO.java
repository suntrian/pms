package org.sunt.api.customize.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class FieldDTO implements Serializable {

    private static final long serialVersionUID = -7262527839351542950L;
    private Integer id;
    private String name;
    private String label;
    private String comment;
    private Integer entityId;       //所属的实体
    private String dataType;
    private Boolean unique;
    private Boolean nullable;
    private Boolean displayField;
    private String defaultValue;
    private List<String> constraints;
    private String reference;
    private Integer position;
    private String style;

}
