package com.example.springhibernate.service;

import com.example.springhibernate.model.User;
import java.util.List;

public interface UserService {
    void saveUser(User user);
    List<User> getAllUsers();
    User getUserByCar(String model, int series);
}
