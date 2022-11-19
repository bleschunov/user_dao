package main.connectionFactory.impl;

import main.connectionFactory.AbstractConnectionFactory;
import main.connectionFactory.ConnectionFactoryFactory;
import main.exceptions.DbException;
import main.models.DbName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CachedConnectionFactory extends AbstractConnectionFactory {
    private Connection connection = null;

    @Override
    public Connection getConnection()
            throws DbException {

        if (connection != null)
            return connection;

        try {
            connection = DriverManager.getConnection(url, login, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DbException(e);
        }

        return connection;
    }
}
