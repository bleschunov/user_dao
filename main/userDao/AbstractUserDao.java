package main.userDao;

import main.connectionFactory.ConnectionFactory;
import main.connectionFactory.ConnectionFactoryFactory;
import main.exceptions.DbException;
import main.exceptions.NotUniqueEmailException;
import main.helpers.Utils;
import main.models.User;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AbstractUserDao implements UserDao {
    private final String url;
    private final String login;
    private final String password;

    private final ConnectionFactory connectionFactory =
            ConnectionFactoryFactory.getConnectionFactory();

    protected Connection getConnection() throws DbException {
        try {
            return connectionFactory.getConnection(url, login, password);
        } catch (SQLException e) {
            throw new DbException("Cannot connect", e);
        }
    }

    protected AbstractUserDao(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    protected List<User> getAllUsers(String query) throws DbException {
        Connection connection = getConnection();
        List<User> result = new ArrayList<>();

        try (   Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                result.add(new User(id, name, email));
            }

            connection.commit();
            return result;
        } catch (SQLException e) {
            Utils.rollbackQuietly(connection);
            throw new DbException(e);
        }
    }

    public User getUserById(int id, String query) throws DbException {
        Connection connection = getConnection();

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (!resultSet.next())
                    return new User(-1, "", "");

                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                connection.commit();
                return new User(userId, name, email);
            }
        } catch (SQLException e) {
            Utils.rollbackQuietly(connection);
            throw new DbException(e);
        }
    }

    public boolean insertUser(String name, String email, String query) throws SQLException {
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();

            connection.commit();
            return true;
        }
        catch (SQLException e) {
            Utils.rollbackQuietly(connection);

            if (    e instanceof PSQLException ||
                    e instanceof SQLIntegrityConstraintViolationException)
                throw e;

            throw new DbException(e);
        }
    }

    public boolean updateUserById(User newUser, String query) throws DbException {
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newUser.getName());
            preparedStatement.setString(2, newUser.getEmail());
            preparedStatement.setInt(3, newUser.getId());
            int updatedCount = preparedStatement.executeUpdate();

            connection.commit();
            return updatedCount == 1;
        } catch (SQLException e) {
            Utils.rollbackQuietly(connection);
            throw new DbException(e);
        }
    }

    public boolean deleteUserById(int id, String query) throws DbException {
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            int deletedCount = preparedStatement.executeUpdate();

            connection.commit();
            return deletedCount == 1;
        } catch (SQLException e) {
            Utils.rollbackQuietly(connection);
            throw new DbException(e);
        }
    }

    public void close() {
        connectionFactory.close();
    }
}
