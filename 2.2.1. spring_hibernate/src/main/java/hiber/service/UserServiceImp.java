package com.example.springhibernate.service;

import com.example.springhibernate.dao.UserDao;
import com.example.springhibernate.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.listUsers();
    }

    @Override
    public User getUserByCar(String model, int series) {
        return userDao.findUserByCar(model, series);
    }
}
