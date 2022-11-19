package main.connectionFactory.impl;

import main.connectionFactory.AbstractConnectionFactory;
import main.connectionFactory.ConnectionFactory;
import main.connectionFactory.ConnectionFactoryFactory;
import main.exceptions.DbException;
import main.helpers.Utils;
import main.models.DbName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RawConnectionFactory extends AbstractConnectionFactory {

    private static Connection connection = null;

    @Override
    public Connection getConnection()
            throws DbException {

        if (connection != null)
            Utils.closeQuietly(connection);

        try {
            connection = DriverManager.getConnection(url, login, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DbException(e);
        }

        return connection;
    }
}
