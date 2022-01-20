package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach((s) -> {
                if (!s.isEmpty()
                        && s.matches("^([^;]+);([0-9a-zA-Z_\\.\\-%]+@.+\\.[a-zA-Z]+);$")) {
                    String[] spam = s.trim().split(";");
                    users.add(new User(spam[0], spam[1]));
                }
            });
        } catch (IOException err) {
            err.printStackTrace();
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            createTable(cnt, "users");
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "INSERT INTO users(name, email) VALUES(?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private void createTable(Connection cnt, String tableName) throws SQLException {
        try (Statement cs = cnt.createStatement()) {
            cs.execute(String.format("DROP TABLE IF EXISTS %s;",
                    tableName
            ));
            cs.execute(String.format("CREATE TABLE IF NOT EXISTS %s (%s, %s, %s);",
                    tableName,
                    "id SERIAL PRIMARY KEY",
                    "name VARCHAR(50)",
                    "email VARCHAR(50)"
            ));
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream(
                String.format("src%1$smain%1$sresources%1$sspammer.properties", File.separator))
        ) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, String.format("data%1$sdump.txt", File.separator));
        db.save(db.load());
    }
}
