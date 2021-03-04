package org.sunt.identity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sunt.identity.dao.UserDao;
import org.sunt.identity.entity.User;

@Service
public class UserService {

    @Autowired
    private UserDao userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }
}
