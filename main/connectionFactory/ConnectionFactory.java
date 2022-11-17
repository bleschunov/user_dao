package main.connectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {
    Connection getConnection(String url, String login, String password)
            throws SQLException;

    void close();
}
