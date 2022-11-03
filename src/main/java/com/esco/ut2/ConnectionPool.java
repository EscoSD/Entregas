package com.esco.ut2;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
    private static ConnectionPool pool;
    private static BasicDataSource dataSource;
    String url = "jdbc:postgresql://localhost:5432/javieregido";

    public ConnectionPool() {
        dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(20);
    }

    public static ConnectionPool getInstance() {
        if (pool == null)
            pool = new ConnectionPool();

        return pool;
    }

    public Connection getConnection() {
        Connection conn = null;

        try {

            conn = dataSource.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
