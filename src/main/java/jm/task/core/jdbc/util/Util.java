package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/test_schema?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASSWORD = "RaechkaTogo020392";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // важно для тестов
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(true);
            return connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при подключении к базе", e);
        }
    }
}
