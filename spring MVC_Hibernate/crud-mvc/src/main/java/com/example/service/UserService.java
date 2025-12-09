package com.example.service;

import com.example.dao.UserDao;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Transactional
    public User getUserById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Transactional
    public void saveUser(User user) {

        user.setId(null);
        userDao.save(user);
    }

    @Transactional
    public void updateUser(User user) {

        userDao.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
}
