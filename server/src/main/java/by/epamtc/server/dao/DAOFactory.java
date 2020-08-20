package by.epamtc.server.dao;

import by.epamtc.server.dao.impl.FileBookDAO;

public final class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();
    private final BookDAO bookDAO = new FileBookDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public BookDAO getBookDAO() {
        return bookDAO;
    }
}
