DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu`
(
    `id`         INT UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `name`       VARCHAR(32)      NOT NULL COMMENT '菜单名称',
    `parent_id`  INT UNSIGNED              DEFAULT 0 COMMENT '父菜单ID',
    `permission` VARCHAR(32)               DEFAULT '' COMMENT '菜单权限标识',
    `route`      VARCHAR(128)              DEFAULT '' COMMENT '前端URL',
    `icon`       VARCHAR(32)               DEFAULT '' COMMENT '图标',
    `component`  VARCHAR(64)               DEFAULT '' COMMENT 'VUE页面',
    `sort`       SMALLINT UNSIGNED         DEFAULT 9 COMMENT '排序值',
    `type`       TINYINT UNSIGNED          DEFAULT 1 COMMENT '菜单类型 （1菜单 2按钮）',
    `created_at` TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `created_by` INTEGER UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人ID',
    `updated_at` TIMESTAMP        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `status`     TINYINT                   DEFAULT 0 COMMENT '逻辑删除标记(0--正常 -1--删除)',
    `tenant_id`  INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX (parent_id)
) ENGINE = INNODB
  AUTO_INCREMENT = 10000
  DEFAULT CHARSET = UTF8MB4
  ROW_FORMAT = DYNAMIC COMMENT ='菜单权限表';

DROP TABLE IF EXISTS `user_account`;
CREATE TABLE IF NOT EXISTS `user_account`
(
    `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `account`    NVARCHAR(64) NOT NULL COMMENT '用户名',
    `mobile`     VARCHAR(16)  NOT NULL DEFAULT '' COMMENT '手机号',
    `email`      VARCHAR(64)  NOT NULL DEFAULT '' COMMENT 'email',
    `wechat`     VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '微信',
    `name`       NVARCHAR(32) NOT NULL DEFAULT '' COMMENT '姓名',
    `password`   VARCHAR(128) NOT NULL COMMENT '密码',
    `created_at` TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `status`     TINYINT      NOT NULL DEFAULT 0 COMMENT '用户状态',
    `tenant_id`  INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY (`account`)
) ENGINE = INNODB
  AUTO_INCREMENT = 10000
  DEFAULT CHARSET = UTF8MB4
  ROW_FORMAT = DYNAMIC COMMENT ='用户账号表';

DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE IF NOT EXISTS `user_profile`
(
    `id`           INT UNSIGNED     NOT NULL COMMENT '用户ID',
    `avatar`       VARCHAR(128)     NOT NULL DEFAULT '' COMMENT '头像url',
    `home_address` NVARCHAR(256)    NOT NULL DEFAULT '' COMMENT '地址',
    `gender`       TINYINT          NOT NULL DEFAULT 0 COMMENT '性别 0-未知 1-男，2-女',
    `birth`        DATE             NULL COMMENT '生日',
    `education`    TINYINT UNSIGNED NOT NULL COMMENT '学历',
    `title`        NVARCHAR(16)     NOT NULL DEFAULT '' COMMENT '头衔',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8MB4
  ROW_FORMAT = DYNAMIC COMMENT ='用户详情表';

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role`
(
    `id`        INT UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `name`      NVARCHAR(32)     NOT NULL COMMENT '角色名称',
    `desc`      NVARCHAR(128)    NOT NULL DEFAULT '' COMMENT '角色说明',
    created_at  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by  INTEGER UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人ID',
    updated_at  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `tenant_id` INTEGER UNSIGNED NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX (`name`)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8MB4
  ROW_FORMAT = DYNAMIC COMMENT ='角色表';

DROP TABLE IF EXISTS `r_user_role`;
CREATE TABLE IF NOT EXISTS `r_user_role`
(
    `user_id`    INTEGER UNSIGNED NOT NULL,
    `role_id`    INTEGER UNSIGNED NOT NULL,
    `created_at` TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`, `role_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8MB4
  ROW_FORMAT = DYNAMIC COMMENT ='用户角色关联表';






