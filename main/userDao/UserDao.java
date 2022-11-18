package main.userDao;

import main.exceptions.DbException;
import main.exceptions.NotUniqueEmailException;
import main.exceptions.NotUniqueNameException;
import main.models.User;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers() throws DbException;
    User getUserById(int id) throws DbException;

    // return true if user is inserted
    boolean insertUser(String name, String email)
            throws DbException, NotUniqueNameException, NotUniqueEmailException;

    // returns old user
    boolean updateUserById(User newUser) throws DbException;

    // returns true if user is deleted
    boolean deleteUserById(int id) throws DbException;

    void close();
}
