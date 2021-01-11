package org.sunt.identity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunt.common.R;
import org.sunt.identity.entity.Converter;
import org.sunt.identity.entity.Menu;
import org.sunt.identity.entity.MenuNode;
import org.sunt.identity.service.MenuService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
public class MenuController implements MenuApi {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public R<List<MenuNode>> listMenuTree() {
        List<Menu> menus = menuService.listMenus();
        return R.success(menus.stream().map(Converter.MENU_CONV::toMenuNode).collect(Collectors.toList()));
    }

}
