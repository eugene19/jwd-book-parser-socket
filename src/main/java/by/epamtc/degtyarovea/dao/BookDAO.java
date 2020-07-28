package by.epamtc.degtyarovea.dao;

import by.epamtc.degtyarovea.entity.TextComponent;

import java.io.IOException;

public interface BookDAO {
    TextComponent createBook() throws IOException;
}
