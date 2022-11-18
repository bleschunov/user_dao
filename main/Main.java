package main;

import main.connectionFactory.ConnectionFactoryFactory;
import main.exceptions.DbException;
import main.tests.Speed;
import main.userDao.UserDao;
import main.userDao.decorator.UserDaoWithLogger;
import main.userDao.impl.MySqlUserDao;
import main.userDao.impl.PostgresUserDao;

public class Main {
    private static final String mySqlLogin = "root";
    private static final String mySqlPassword = "qwerty12345";

    private static final String postgresLogin = "postgres";
    private static final String postgresPassword = "qwerty12345";

    public static void main(String[] args) {

        try {
            ConnectionFactoryFactory.setFactoryType(ConnectionFactoryFactory.FactoryType.CACHED);

//            UserDao userDao = new UserDaoWithLogger(new PostgresUserDao(postgresLogin, postgresPassword));
            UserDao userDao = new UserDaoWithLogger(new MySqlUserDao(mySqlLogin, mySqlPassword));

//            userDao.getAllUsers();

            userDao.insertUser("ataturk", "putin@gmail.com");

            userDao.close();

        } catch (DbException e) {
            e.printStackTrace();
        }

//        try {
//
//            System.out.println("Simple: ");
//            Speed.test(new PostgresUserDao(postgresLogin, postgresPassword));
//
//            ConnectionFactoryFactory.setFactoryType(ConnectionFactoryFactory.FactoryType.CACHED);
//
//            System.out.println("Cached: ");
//            Speed.test(new PostgresUserDao(postgresLogin, postgresPassword));
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}