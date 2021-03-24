package org.sunt.identity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sunt.identity.dao.UserDao;
import org.sunt.identity.entity.User;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User create(User user) {
        userDao.insert(user);
        return user;
    }
}
