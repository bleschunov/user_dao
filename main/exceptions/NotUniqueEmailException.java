package main.exceptions;

public class NotUniqueEmailException extends DbException {

    public NotUniqueEmailException(String reason) {
        super(reason);
    }

    public NotUniqueEmailException(Throwable cause) {
        super(cause);
    }

    public NotUniqueEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
