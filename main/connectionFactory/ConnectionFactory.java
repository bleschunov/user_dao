package main.connectionFactory;

import main.exceptions.DbException;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection getConnection() throws DbException;
}
