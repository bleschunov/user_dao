package main.transactionManager;

import main.connectionFactory.ConnectionFactory;
import main.connectionFactory.ConnectionFactoryFactory;
import main.exceptions.DaoException;
import main.exceptions.DbException;
import main.helpers.Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class TransactionManager<R> {
    private final static ConnectionFactory connectionFactory = ConnectionFactoryFactory.getConnectionFactory();
    private final static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public R doInTransaction(Callable<R> unitOfWork) throws DaoException {
        Connection connection;

        try {
            connection = connectionFactory.getConnection();
            threadLocal.set(connection);

            try {
                R result = unitOfWork.call();
                connection.commit();
                return result;
            } catch (Exception e) {
                Utils.rollbackQuietly(connection);
                throw new DbException(e);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    public static Connection getConnection() {
        return threadLocal.get();
    }
}
