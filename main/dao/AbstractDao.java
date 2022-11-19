package main.dao;

import main.exceptions.DbException;
import main.helpers.PropertiesReader;
import main.helpers.extractor.Extractor;
import main.transactionManager.TransactionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public abstract class AbstractDao<T> {

    public List<T> selectAll(String query, Extractor<T> extractor) throws DbException {
        Connection connection = TransactionManager.getConnection();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            return extractor.extractAll(resultSet);

        } catch (Exception e) {
            throw new DbException(e);
        }
    }

    public T selectById(String query, Extractor<T> extractor) throws DbException {
        Connection connection = TransactionManager.getConnection();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            return extractor.extractOne(resultSet);

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }
}
