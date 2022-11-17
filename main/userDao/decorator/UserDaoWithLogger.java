package main.userDao.decorator;

import main.models.User;

import main.userDao.UserDao;

import java.sql.SQLException;

import java.util.List;

public class UserDaoWithLogger extends UserDaoDecorator {
    public UserDaoWithLogger(UserDao userDao) {
        super(userDao);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            System.out.println("Read user: " + user);
        }
        System.out.println();

        return users;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User specialUser = userDao.getUserById(id);
        System.out.println("Read user by id = " + id + ": " + specialUser);
        System.out.println();

        return specialUser;
    }

    @Override
    public User getYoungestUser() throws SQLException {
        User youngestUser = userDao.getYoungestUser();
        System.out.println("Read the youngest user: " + youngestUser);
        System.out.println();

        return youngestUser;
    }

    @Override
    public User updateUserById(int id, User newUser) throws SQLException {
        return userDao.updateUserById(id, newUser);
    }

    @Override
    public boolean deleteUserById(int id) throws SQLException {
        boolean isDeleted = userDao.deleteUserById(2);
        System.out.println("Is user with id = 2 deleted? " + isDeleted);
        System.out.println();

        return isDeleted;
    }
}
