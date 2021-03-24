package org.sunt.identity.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.sunt.identity.TestIdentityApplication;
import org.sunt.identity.entity.Menu;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMenuService extends TestIdentityApplication {

    private final static AtomicInteger id = new AtomicInteger(0);

    @Autowired
    private MenuService menuService;

    @Test
    @Order(1)
    public void testInsertMenu() {
        Menu menu = new Menu();
        menu.setName("test");
        menu.setParentId(id.getAndIncrement());
        menu.setComponent("/afaf");
        menu.setPermission("abc:def");
        menu.setStatus((short) 0);
        menu.setIcon("");
        menu.setCreatedAt(LocalDateTime.now());
        menu.setCreatedBy(1);
        menu.setRoute("/afaf");
        menu.setSort((short) 9);
        menu.setType((short) 1);
        int inserted = menuService.insert(menu);
        System.out.println("INSERTED:" + inserted);
    }

    @Test
    @Order(2)
    public void testListMenus() {
        List<Menu> menus = menuService.listMenus();
        Assertions.assertEquals(1, menus.size());
        for (Menu menu : menus) {
            System.out.println(menu);
        }
    }


}
