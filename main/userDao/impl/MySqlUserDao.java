package main.userDao.impl;

import main.connectionFactory.ConnectionFactory;
import main.connectionFactory.ConnectionFactoryFactory;
import main.userDao.AbstractUserDao;
import main.userDao.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import main.models.User;
import java.util.List;

public class MySqlUserDao extends AbstractUserDao {
    private static final String url = "jdbc:mysql://localhost:3306/sys";

    private static final String GET_ALL_USERS =
            "SELECT * FROM users";
    private static final String GET_USER_BY_ID =
            "SELECT * FROM users WHERE id = ?";
    private static final String GET_YOUNGEST_USER =
            "SELECT * FROM users WHERE birthdate = (SELECT MAX(birthdate) FROM users)";
    private static final String UPDATE_USER_BY_ID =
            "UPDATE users SET name = ?, birthdate = ? WHERE id = ?";
    private static final String DELETE_USER_BY_ID =
            "DELETE FROM users WHERE id = ?";

    public MySqlUserDao(String login, String password) {
        super(url, login, password);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return getAllUsers(GET_ALL_USERS);
    }

    @Override
    public User getUserById(int id) throws SQLException {
        return getUserById(id, GET_USER_BY_ID);
    }

    @Override
    public User getYoungestUser() throws SQLException {
        return getYoungestUser(GET_YOUNGEST_USER);
    }

    @Override
    public User updateUserById(int id, User newUser) throws SQLException {
        return updateUserById(id, newUser, UPDATE_USER_BY_ID);
    }

    @Override
    public boolean deleteUserById(int id) throws SQLException {
        return deleteUserById(id, DELETE_USER_BY_ID);
    }
}
