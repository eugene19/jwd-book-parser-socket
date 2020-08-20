package by.epamtc.server.dao;

public class BookDAOException extends Exception {

    private static final long serialVersionUID = -8420205656779270129L;

    public BookDAOException() {
    }

    public BookDAOException(String message) {
        super(message);
    }

    public BookDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookDAOException(Throwable cause) {
        super(cause);
    }
}
