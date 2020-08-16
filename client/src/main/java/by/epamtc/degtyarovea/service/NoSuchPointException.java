package by.epamtc.degtyarovea.service;

import java.io.Serializable;

public class NoSuchPointException extends Exception implements Serializable {

    private static final long serialVersionUID = 6769427340087549159L;

    public NoSuchPointException() {
        super();
    }

    public NoSuchPointException(String message) {
        super(message);
    }

    public NoSuchPointException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchPointException(Throwable cause) {
        super(cause);
    }
}
