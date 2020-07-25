package by.epamtc.degtyarovea.dao;

public class BookDAOException extends Exception {

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
