package com.example.springhibernate.dao;

import com.example.springhibernate.model.User;
import java.util.List;

public interface UserDao {
   void addUser(User user);
   List<User> listUsers();
   User findUserByCar(String model, int series);
}
