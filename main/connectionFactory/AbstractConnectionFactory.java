package main.connectionFactory;

import main.helpers.Utils;

import java.sql.Connection;

public abstract class AbstractConnectionFactory implements ConnectionFactory {

    protected void close(Connection connection) {
        Utils.closeQuietly(connection);
    }
}
