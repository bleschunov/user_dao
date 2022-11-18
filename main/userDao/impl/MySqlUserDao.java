package main.userDao.impl;

import main.exceptions.DbException;
import main.exceptions.NotUniqueEmailException;
import main.exceptions.NotUniqueNameException;
import main.userDao.AbstractUserDao;

import main.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MySqlUserDao extends AbstractUserDao {
    private static final String url = "jdbc:mysql://localhost:3306/sys";

    private static final String GET_ALL_USERS =
            "SELECT * FROM users";
    private static final String GET_USER_BY_ID =
            "SELECT * FROM users WHERE id = ?";
    private static final String INSERT_USER =
            "INSERT INTO users (name, email) VALUES (?, ?)";
    private static final String UPDATE_USER_BY_ID =
            "UPDATE users SET name = ?, birthdate = ? WHERE id = ?";
    private static final String DELETE_USER_BY_ID =
            "DELETE FROM users WHERE id = ?";

    public MySqlUserDao(String login, String password) {
        super(url, login, password);
    }

    @Override
    public List<User> getAllUsers() throws DbException {
        return getAllUsers(GET_ALL_USERS);
    }

    @Override
    public User getUserById(int id) throws DbException {
        return getUserById(id, GET_USER_BY_ID);
    }

    @Override
    public boolean insertUser(String name, String email)
            throws DbException, NotUniqueNameException, NotUniqueEmailException {

        try {
            return insertUser(name, email, INSERT_USER);
        }
        catch (DbException e) {
            throw e;
        }
        catch (SQLException e) {
            String message = e.getMessage();

            Pattern pattern = Pattern.compile("for key 'users.(.+)'");
            Matcher matcher = pattern.matcher(message);

            matcher.find();

            String field = matcher.group(1);

            if (field.equals("name"))
                throw new NotUniqueNameException("Name = " + name + " is not unique");
            else
                throw new NotUniqueEmailException("Email = " + email + " is not unique");
        }
    }

    @Override
    public boolean updateUserById(User newUser) throws DbException {
        return updateUserById(newUser, UPDATE_USER_BY_ID);
    }

    @Override
    public boolean deleteUserById(int id) throws DbException {
        return deleteUserById(id, DELETE_USER_BY_ID);
    }
}
