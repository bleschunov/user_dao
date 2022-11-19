package main.exceptions;

public class NotUniqueNameException extends DaoException {

    public NotUniqueNameException(String reason) {
        super(reason);
    }

    public NotUniqueNameException(Throwable cause) {
        super(cause);
    }

    public NotUniqueNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
