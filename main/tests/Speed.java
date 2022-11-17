package main.tests;

import main.userDao.UserDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Speed {
    public static void test(UserDao userDao) {

        List<Integer> results = new ArrayList<>();

        try {
            for (int i = 0; i < 1000; i++) {

                long start = System.nanoTime();
                userDao.getAllUsers();
                long end = System.nanoTime();

                int currentResult = (int) ((end - start) / 1000);

                results.add(currentResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int sum = 0;
        for (int x : results)
            sum += x;

        System.out.println("avg = " + sum / results.size());
    }
}
