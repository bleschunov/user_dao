package main.userDao;

import java.sql.SQLException;
import main.models.User;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers() throws SQLException;
    User getUserById(int id) throws SQLException;
    User getYoungestUser() throws SQLException;

    User updateUserById(int id, User newUser) throws SQLException;

    boolean deleteUserById(int id) throws SQLException;
}
