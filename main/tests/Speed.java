package main.tests;

import main.exceptions.DbException;
import main.dao.userDao.UserDao;

import java.util.ArrayList;
import java.util.List;

public class Speed {
    public static void test(UserDao userDao) {

        List<Integer> results = new ArrayList<>();

        try {
            for (int i = 0; i < 1000; i++) {

                long start = System.nanoTime();
                userDao.selectAll();
                long end = System.nanoTime();

                int currentResult = (int) ((end - start) / 1000);

                results.add(currentResult);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

        int sum = 0;
        for (int x : results)
            sum += x;

        System.out.println("avg = " + sum / results.size());
    }
}
