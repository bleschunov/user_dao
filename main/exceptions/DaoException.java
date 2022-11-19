package main.exceptions;

public class DaoException extends DbException {
    public DaoException(String reason) {
        super(reason);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
