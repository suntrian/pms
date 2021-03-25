package org.sunt.identity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sunt.identity.dao.MenuDao;
import org.sunt.identity.entity.Menu;

import java.util.List;

@Service
public class MenuService {

    private final MenuDao menuDao;

    @Autowired
    public MenuService(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public List<Menu> listMenus() {
        return menuDao.getAll();
    }

    public Menu get(Integer id) {
        return menuDao.getById(id);
    }

    public Integer insert(Menu menu) {
        return menuDao.insert(menu);
    }
}
