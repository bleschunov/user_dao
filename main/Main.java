package main;

import main.connectionFactory.ConnectionFactoryFactory;
import main.tests.Speed;
import main.userDao.impl.MySqlUserDao;
import main.userDao.impl.PostgresUserDao;

public class Main {
    private static final String mySqlLogin = "root";
    private static final String mySqlPassword = "qwerty12345";

    private static final String postgresLogin = "postgres";
    private static final String postgresPassword = "qwerty12345";

    public static void main(String[] args) {

//        try {
//            ConnectionFactoryFactory.setFactoryType(ConnectionFactoryFactory.FactoryType.CACHED);
//            UserDao userDao = new UserDaoWithLogger(new MySqlUserDao(login, password));
//
//            userDao.getUserById(150);
//            userDao.getYoungestUser();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try {

            System.out.println("Simple: ");
            Speed.test(new PostgresUserDao(postgresLogin, postgresPassword));

            ConnectionFactoryFactory.setFactoryType(ConnectionFactoryFactory.FactoryType.CACHED);

            System.out.println("Cached: ");
            Speed.test(new PostgresUserDao(postgresLogin, postgresPassword));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}