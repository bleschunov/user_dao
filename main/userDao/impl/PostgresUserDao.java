package main.userDao.impl;

import main.models.User;
import main.userDao.AbstractUserDao;
import main.userDao.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostgresUserDao extends AbstractUserDao {
    private static final String url = "jdbc:postgresql://localhost:5432/";

    private static final String GET_ALL_USERS =
            "SELECT * FROM \"user\"";
    private static final String GET_USER_BY_ID =
            "SELECT * FROM \"user\" WHERE id = ?";
    private static final String GET_YOUNGEST_USER =
            "SELECT * FROM \"user\" WHERE birthdate = (SELECT MAX(birthdate) FROM \"user\")";
    private static final String UPDATE_USER_BY_ID =
            "UPDATE \"user\" SET name = ?, birthdate = ? WHERE id = ?";
    private static final String DELETE_USER_BY_ID =
            "DELETE FROM \"user\" WHERE id = ?";


    public PostgresUserDao(String login, String password) throws SQLException {
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
