package org.sunt.api.identity.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class MenuNode implements Serializable {


    public static final Map<Short, String> typeMap = new HashMap<>() {{
        put((short) 1, "菜单");
        put((short) 2, "按钮");
    }};
    private Integer id;
    private Integer parentId;
    private String name;
    private String permission;
    private String route;
    private String icon;
    private String component;
    private Short sort;
    private Short type;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Short status;

    public String getTypeName() {
        return typeMap.get(this.type);
    }

}
