package org.sunt.identity.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.sunt.common.R;
import org.sunt.identity.entity.MenuNode;

import java.util.List;

@FeignClient(name = "menu", url = "/menu")
public interface MenuApi {

    @GetMapping("")
    R<List<MenuNode>> listMenuTree();

}
