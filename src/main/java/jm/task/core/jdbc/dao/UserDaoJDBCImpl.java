package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    private static final String CREATE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS users (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(50),
                lastName VARCHAR(50),
                age TINYINT
            )
            """;

    private static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS users";
    private static final String INSERT_USER_SQL = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
    private static final String DELETE_USER_BY_ID_SQL = "DELETE FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM users";
    private static final String TRUNCATE_TABLE_SQL = "TRUNCATE TABLE users";

    public UserDaoJDBCImpl() {
        try {
            this.connection = new Util().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при установлении соединения с БД", e);
        }
    }

    @Override
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании таблицы users", e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(DROP_TABLE_SQL);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении таблицы users", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении пользователя", e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_USER_BY_ID_SQL)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении пользователя", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_ALL_USERS_SQL)) {
            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getByte("age")
                );
                user.setId(rs.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении пользователей", e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(TRUNCATE_TABLE_SQL);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при очистке таблицы users", e);
        }
    }
}
