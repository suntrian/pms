package org.sunt.identity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sunt.identity.entity.Menu;

@Repository
public interface MenuDao extends JpaRepository<Menu, Integer> {

}
