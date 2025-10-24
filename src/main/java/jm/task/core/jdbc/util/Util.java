package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/test_schema?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "RaechkaTogo020392";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение с базой установлено!");
            return connection;
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных");
            e.printStackTrace();
            return null;
        }
    }
}
