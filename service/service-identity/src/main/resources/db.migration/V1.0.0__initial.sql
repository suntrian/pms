use `pms`;

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`
(
    `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `name`        VARCHAR(32)  NOT NULL COMMENT '菜单名称',
    `parent_id`   INT UNSIGNED          DEFAULT 0 COMMENT '父菜单ID',
    `permission`  VARCHAR(32)           DEFAULT '' COMMENT '菜单权限标识',
    `route`       VARCHAR(128)          DEFAULT '' COMMENT '前端URL',
    `icon`        VARCHAR(32)           DEFAULT '' COMMENT '图标',
    `component`   VARCHAR(64)           DEFAULT '' COMMENT 'VUE页面',
    `sort`        SMALLINT UNSIGNED     DEFAULT 9 COMMENT '排序值',
    `type`        TINYINT UNSIGNED      DEFAULT 1 COMMENT '菜单类型 （1菜单 2按钮）',
    `create_time` DATETIME     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME     NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `status`      TINYINT               DEFAULT 0 COMMENT '逻辑删除标记(0--正常 -1--删除)',
    `tenant_id`   INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 10000
  DEFAULT CHARSET = UTF8MB4
  ROW_FORMAT = DYNAMIC COMMENT ='菜单权限表';