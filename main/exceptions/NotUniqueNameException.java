package main.exceptions;

public class NotUniqueNameException extends DbException {

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
