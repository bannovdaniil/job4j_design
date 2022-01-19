package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config cfg = new Config(
                String.format("src%1$smain%1$sresources%1$sapp.properties", File.separator)
        );
        String driver = cfg.value("driver");
        String url = cfg.value("url");
        String login = cfg.value("login");
        String password = cfg.value("password");
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
