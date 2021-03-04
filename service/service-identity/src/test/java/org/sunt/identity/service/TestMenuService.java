package org.sunt.identity.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.sunt.identity.TestIdentityApplication;
import org.sunt.identity.dao.MenuDao;
import org.sunt.identity.entity.Menu;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestMenuService extends TestIdentityApplication {

    private final static AtomicInteger id = new AtomicInteger(0);
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuDao menuRepository;

    @BeforeEach
    public void setUp() {
        Menu menu = new Menu();
        menu.setName("test");
        menu.setParentId(id.getAndIncrement());
        menu.setComponent("/afaf");
        menu.setPermission("abc:def");
        menu.setStatus((short) 0);
        menu.setIcon("");
        menu.setCreateTime(LocalDateTime.now());
        menu.setRoute("/afaf");
        menu.setSort((short) 9);
        menu.setType((short) 1);
        menuRepository.save(menu);
    }

    @Test
    public void testListMenus() {
        List<Menu> menus = menuService.listMenus();
        Assertions.assertEquals(1, menus.size());
        for (Menu menu : menus) {
            System.out.println(menu);
        }
    }

    @Test
    public void testListMenuByParentId() {
        List<Menu> menus = menuService.listByParentId(1);
        Assertions.assertEquals(1, menus.size());
        System.out.println(menus.get(0));
    }

}
