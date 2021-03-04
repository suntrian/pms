package org.sunt.api.identity.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.sunt.api.identity.entity.MenuNode;
import org.sunt.common.mvc.R;

import java.util.List;

@FeignClient(name = "menu", path = "/menu")
public interface MenuApi {

    @GetMapping("")
    R<List<MenuNode>> listMenuTree();

}
