package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                        CREATE TABLE IF NOT EXISTS users (
                            id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(50),
                            last_name VARCHAR(50),
                            age TINYINT
                        )
                    """);
            System.out.println("Таблица пользователей успешно создана!");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы пользователей");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Таблица пользователей удалена!");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы пользователей");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("Пользователь с именем %s добавлен в базу%n", name);
        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении пользователя");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.printf("Пользователь с id=%d удалён%n", id);
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении пользователя");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")
                );
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
            System.out.println("Список пользователей получен!");
        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка пользователей");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
            System.out.println("Таблица пользователей очищена!");
        } catch (SQLException e) {
            System.out.println("Ошибка при очистке таблицы пользователей");
            e.printStackTrace();
        }
    }
}
