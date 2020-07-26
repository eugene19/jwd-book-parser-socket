package by.epamtc.degtyarovea.dao.impl;

import by.epamtc.degtyarovea.dao.BookDAO;
import by.epamtc.degtyarovea.dao.parsers.AbstractParser;
import by.epamtc.degtyarovea.dao.parsers.BookParser;
import by.epamtc.degtyarovea.entity.TextComponent;

import java.io.IOException;

public class FileBookDAO implements BookDAO {

    private AbstractParser parser;
    private FileBookReader reader;

    public FileBookDAO() {
        this.parser = new BookParser();
        this.reader = new FileBookReader();
    }

    @Override
    public TextComponent createBook() {
        TextComponent book = null;

        try {
            String text = reader.readAllText();
            book = parser.parse(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return book;
    }
}
