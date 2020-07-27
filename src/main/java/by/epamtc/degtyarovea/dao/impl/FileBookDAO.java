package by.epamtc.degtyarovea.dao.impl;

import by.epamtc.degtyarovea.dao.BookDAO;
import by.epamtc.degtyarovea.dao.parsers.AbstractParser;
import by.epamtc.degtyarovea.dao.parsers.ParserFactory;
import by.epamtc.degtyarovea.entity.TextComponent;

import java.io.IOException;

public class FileBookDAO implements BookDAO {

    private FileBookReader reader;
    private AbstractParser parser;

    public FileBookDAO() {
        this.reader = new FileBookReader();
        this.parser = ParserFactory.getInstance().getParser();
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
