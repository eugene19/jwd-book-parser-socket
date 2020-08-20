package by.epamtc.server.service;

public class BookServiceException extends Exception {

    private static final long serialVersionUID = 3338064374703246359L;

    public BookServiceException() {
    }

    public BookServiceException(String message) {
        super(message);
    }

    public BookServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookServiceException(Throwable cause) {
        super(cause);
    }
}
