package org.sunt.identity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.sunt.identity.dao.MenuDao;
import org.sunt.identity.entity.Menu;

import java.util.List;

@Service
public class MenuService {

    private final MenuDao menuRepository;

    @Autowired
    public MenuService(MenuDao menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> listMenus() {
        return menuRepository.findAll();
    }

    public Menu get(Integer id) {
        return menuRepository.getOne(id);
    }

    public List<Menu> listByParentId(Integer parentId) {
        return menuRepository.findAll(Example.of(new Menu().setParentId(parentId)));
    }

}
