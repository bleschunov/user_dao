package main.connectionFactory.impl;

import main.connectionFactory.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RawConnectionFactory implements ConnectionFactory {

    private Connection connection = null;

    @Override
    public Connection getConnection(String url, String login, String password)
            throws SQLException {

        if (connection != null)
            close();

        connection = DriverManager.getConnection(url, login, password);
        connection.setAutoCommit(false);

        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (Exception ignored) {}
    }
}
