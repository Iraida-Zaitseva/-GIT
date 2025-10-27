package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("""
                    CREATE TABLE IF NOT EXISTS users (
                        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(50),
                        last_name VARCHAR(50),
                        age TINYINT
                    )
                    """).executeUpdate();
            transaction.commit();
            System.out.println("Таблица пользователей успешно создана!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Ошибка при создании таблицы пользователей");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            System.out.println("Таблица пользователей удалена!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Ошибка при удалении таблицы пользователей");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.printf("Пользователь с именем %s добавлен в базу%n", name);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Ошибка при сохранении пользователя");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                System.out.printf("Пользователь с id=%d удалён%n", id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Ошибка при удалении пользователя");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            List<User> users = session.createQuery("FROM User", User.class).list();
            System.out.println("Список пользователей получен!");
            return users;
        } catch (Exception e) {
            System.out.println("Ошибка при получении списка пользователей");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
            System.out.println("Таблица пользователей очищена!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Ошибка при очистке таблицы пользователей");
            e.printStackTrace();
        }
    }
}
