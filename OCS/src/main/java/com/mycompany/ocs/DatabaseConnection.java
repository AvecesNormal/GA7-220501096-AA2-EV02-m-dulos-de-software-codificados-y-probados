package com.mycompany.ocs;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                Properties props = new Properties();
                InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("com/mycompany/ocs/db.properties");
                props.load(inputStream);
                String driver = props.getProperty("jdbc.driver");
                String url = props.getProperty("jdbc.url");
                String user = props.getProperty("jdbc.username");
                String password = props.getProperty("jdbc.password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (IOException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
}