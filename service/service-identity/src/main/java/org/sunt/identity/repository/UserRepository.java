package org.sunt.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.sunt.identity.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT password FROM user_account WHERE account = :account LIMIT 1", nativeQuery = true)
    String getPasswordByAccount(@Param("account") String account);

}
