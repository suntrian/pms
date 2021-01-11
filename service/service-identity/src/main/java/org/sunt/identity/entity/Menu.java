package org.sunt.identity.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "menu")
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private Integer parentId;

    @Column
    private String name;

    @Column
    private String permission;

    @Column
    private String route;

    @Column
    private String icon;

    @Column
    private String component;

    @Column
    private Short sort;

    @Column
    private Short type;

    @Column
    private LocalDateTime createTime;

    @Column
    private LocalDateTime updateTime;

    @Column
    private Short status;

}
