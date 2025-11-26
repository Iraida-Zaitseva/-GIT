package com.example.springhibernate.dao;

import com.example.springhibernate.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public void addUser(User user) {
      entityManager.persist(user);
   }

   @Override
   public List<User> listUsers() {
      return entityManager.createQuery("FROM User", User.class).getResultList();
   }

   @Override
   public User findUserByCar(String model, int series) {
      return entityManager
              .createQuery(
                      "SELECT u FROM User u WHERE u.car.model = :model AND u.car.series = :series",
                      User.class
              )
              .setParameter("model", model)
              .setParameter("series", series)
              .getSingleResult();
   }
}
