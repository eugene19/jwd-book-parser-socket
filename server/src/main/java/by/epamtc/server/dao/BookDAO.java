package by.epamtc.server.dao;

import by.epamtc.server.entity.TextComponent;

public interface BookDAO {
    TextComponent createBook() throws BookDAOException;
}
