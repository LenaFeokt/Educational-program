package com.example.countires.h2database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс для подключения к базе данных
 */
public class H2DatabaseConnection {
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:C:/Users/user/Desktop/Countires/Countires/learnsystem";
    private static final String USER = "";
    private static final String PASSWORD = "";

    private static Connection CONNECTION;

    /** Регистрация драйвера */
    static {
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (CONNECTION == null) {
            CONNECTION = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return CONNECTION;
    }
}
