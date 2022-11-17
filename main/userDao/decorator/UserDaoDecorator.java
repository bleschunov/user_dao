package main.userDao.decorator;

import main.userDao.UserDao;

public abstract class UserDaoDecorator implements UserDao {
    protected final UserDao userDao;

    public UserDaoDecorator(UserDao userDao) {
        this.userDao = userDao;
    }
}
