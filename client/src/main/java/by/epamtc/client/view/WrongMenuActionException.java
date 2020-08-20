package by.epamtc.client.view;

public class WrongMenuActionException extends Exception {

    private static final long serialVersionUID = -7899636590337639978L;

    public WrongMenuActionException() {
    }

    public WrongMenuActionException(String message) {
        super(message);
    }

    public WrongMenuActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongMenuActionException(Throwable cause) {
        super(cause);
    }
}
