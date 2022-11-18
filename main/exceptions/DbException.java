package main.exceptions;

import java.sql.SQLException;

public class DbException extends SQLException {

    public DbException(String reason) {
        super(reason);
    }

    public DbException(Throwable cause) {
        super(cause);
    }

    public DbException(String message, Throwable cause) {
        super(message, cause);
    }
}
