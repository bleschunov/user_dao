package main.userDao;

import main.connectionFactory.ConnectionFactory;
import main.connectionFactory.ConnectionFactoryFactory;
import main.models.User;

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

    protected Connection getConnection() throws SQLException {
        return connectionFactory.getConnection(url, login, password);
    }

    protected AbstractUserDao(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    protected List<User> getAllUsers(String query) throws SQLException {
        Connection connection = getConnection();

        List<User> result = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            try {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Date birthDate = resultSet.getDate("birthDate");

                    result.add(new User(id, name, birthDate));
                }

                connection.commit();
                return result;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    public User getUserById(int id, String query) throws SQLException {
        Connection connection = getConnection();

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (!resultSet.next())
                    return new User(-1, "", new Date());

                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date birthDate = resultSet.getDate("birthDate");

                connection.commit();
                return new User(userId, name, birthDate);
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    public User getYoungestUser(String query) throws SQLException {
        Connection connection = getConnection();

        try (   Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            try {
                if (!resultSet.next())
                    return new User(-1, "", new Date());

                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date birthDate = resultSet.getDate("birthDate");

                connection.commit();
                return new User(userId, name, birthDate);
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    public User updateUserById(int id, User newUser, String query) throws SQLException {
        Connection connection = getConnection();

        User oldUser = getUserById(id);

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try {
                preparedStatement.setString(1, newUser.getName());
                preparedStatement.setDate(2, new java.sql.Date(newUser.getBirthDate().getTime()));
                preparedStatement.setInt(3, newUser.getId());
                preparedStatement.executeUpdate();

                connection.commit();
                return oldUser;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    public boolean deleteUserById(int id, String query) throws SQLException {
        Connection connection = getConnection();

        int oldCount = getAllUsers().size();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try {
                preparedStatement.setInt(1, id);

                preparedStatement.executeUpdate();

                int newCount = getAllUsers().size();
                if (newCount == oldCount)
                    return false;

                connection.commit();
                return true;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    protected void close() {
        connectionFactory.close();
    }
}
