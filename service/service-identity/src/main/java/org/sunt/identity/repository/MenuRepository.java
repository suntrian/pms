package org.sunt.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sunt.identity.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
