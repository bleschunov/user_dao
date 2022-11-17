package main;

import java.sql.Driver;
import java.util.ServiceLoader;

public class ServiceLoaderExample {
    public static void main(String[] args) {
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        for (Driver driver : loader) {
            System.out.println(driver);
        }
    }
}
