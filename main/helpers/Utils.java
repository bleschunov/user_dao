package main.helpers;

import java.sql.Connection;
import java.sql.SQLException;

public class Utils {

    public static void closeQuietly(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ignored) {}
    }

    public static void rollbackQuietly(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ignored) {}
    }
}
