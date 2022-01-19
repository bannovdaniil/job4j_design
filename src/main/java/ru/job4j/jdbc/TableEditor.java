package ru.job4j.jdbc;

import org.postgresql.util.PSQLException;
import ru.job4j.io.Config;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private final Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        this.connection = DriverManager.getConnection(url, login, password);
    }

    private void execSQL(String tableName, String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
        System.out.println(getTableScheme(connection, tableName));
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s (%s, %s);",
                tableName,
                "id SERIAL PRIMARY KEY",
                "name VARCHAR(50)"
        );
        execSQL(tableName, sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format("DROP TABLE IF EXISTS %s;",
                tableName
        );
        String result = "DROP TABLE don't work";
        try {
            execSQL(tableName, sql);
        } catch (PSQLException err) {
            result = "DROP TABLE - ok";
        }
        System.out.println(result);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format("ALTER TABLE IF EXISTS %s ADD %s %s;",
                tableName,
                columnName,
                type
        );
        execSQL(tableName, sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format("ALTER TABLE IF EXISTS %s DROP COLUMN %s;",
                tableName,
                columnName
        );
        execSQL(tableName, sql);
    }

    public void renameColumn(String tableName, String columnName,
                             String newColumnName) throws Exception {
        String sql = String.format("ALTER TABLE IF EXISTS %s RENAME %s TO %s;",
                tableName,
                columnName,
                newColumnName
        );
        execSQL(tableName, sql);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Path fileProperties = Paths.get("data\\app.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(fileProperties.toFile()));
        TableEditor tblEditor = new TableEditor(properties);
        String tName = "newtable";
        String cName = "newcolumn";
        String rName = "rename";
        String type = "VARCHAR(255) NOT NULL";
        tblEditor.dropTable("newtable");
        System.out.println("Create table");
        tblEditor.createTable(tName);
        System.out.println("Add column:");
        tblEditor.addColumn(tName, cName, type);
        System.out.println("Rename column:");
        tblEditor.renameColumn(tName, cName, rName);
        System.out.println("Drop column:");
        tblEditor.dropColumn(tName, rName);
        System.out.println("Drop table:");
        tblEditor.dropTable("newtable");
    }
}
