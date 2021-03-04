module org.sunt.api {

    requires spring.boot;
    requires spring.web;
    requires spring.context;
    requires spring.jdbc;
    requires spring.beans;
    requires spring.core;
    requires spring.cloud.openfeign.core;
    requires lombok;
    requires java.annotation;


    exports org.sunt.dictionary;
    exports org.sunt.exception;
    exports org.sunt.spring;
    exports org.sunt.common;

    exports org.sunt.api.customize.controller;
    exports org.sunt.api.customize.entity;
    exports org.sunt.api.customize.dictionary;

    exports org.sunt.api.git.controller;

    exports org.sunt.api.identity.controller;
    exports org.sunt.api.identity.entity;

    exports org.sunt.api.project.controller;

    exports org.sunt.api.schedule.controller;

    exports org.sunt.api.search.controller;

    exports org.sunt.api.storage.entity;
    exports org.sunt.api.storage.controller;


}