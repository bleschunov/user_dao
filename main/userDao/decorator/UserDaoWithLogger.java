package main.userDao.decorator;

import main.exceptions.DbException;
import main.models.User;

import main.userDao.UserDao;

import java.sql.SQLException;

import java.util.List;

public class UserDaoWithLogger extends UserDaoDecorator {
    public UserDaoWithLogger(UserDao userDao) {
        super(userDao);
    }

    @Override
    public List<User> getAllUsers() throws DbException {
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            System.out.println("Read user: " + user);
        }
        System.out.println();

        return users;
    }

    @Override
    public boolean insertUser(String name, String email) throws DbException {

        try {
            boolean result = userDao.insertUser(name, email);

            System.out.println("Inserted new user with name = " + name + " and email = " + email);
            System.out.println();

            return result;
        } catch (DbException e) {
            throw e;
        }
    }

    @Override
    public User getUserById(int id) throws DbException {
        User specialUser = userDao.getUserById(id);
        System.out.println("Read user by id = " + id + ": " + specialUser);
        System.out.println();

        return specialUser;
    }

    @Override
    public boolean updateUserById(User newUser) throws DbException {
        System.out.println("Updated new user: " + newUser);
        System.out.println();

        return userDao.updateUserById(newUser);
    }

    @Override
    public boolean deleteUserById(int id) throws DbException {
        boolean isDeleted = userDao.deleteUserById(2);
        System.out.println("Is user with id = 2 deleted? " + isDeleted);
        System.out.println();

        return isDeleted;
    }

    @Override
    public void close() {
        System.out.println("Close connection");
        System.out.println();

        userDao.close();
    }
}
