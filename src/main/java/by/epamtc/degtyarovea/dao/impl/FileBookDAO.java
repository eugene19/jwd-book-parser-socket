package by.epamtc.degtyarovea.dao.impl;

import by.epamtc.degtyarovea.dao.BookDAO;
import by.epamtc.degtyarovea.dao.BookDAOException;
import by.epamtc.degtyarovea.dao.impl.parsers.ParserFactory;
import by.epamtc.degtyarovea.dao.impl.reader.BookReaderFactory;
import by.epamtc.degtyarovea.dao.impl.reader.FileBookReader;
import by.epamtc.degtyarovea.entity.TextComponent;

import java.io.IOException;

public class FileBookDAO implements BookDAO {

    private FileBookReader reader;
    private AbstractParser parser;

    public FileBookDAO() {
        this.reader = BookReaderFactory.getInstance().getFileBookReader();
        this.parser = ParserFactory.getInstance().getParser();
    }

    @Override
    public TextComponent createBook() throws BookDAOException {
        TextComponent book;

        try {
            String text = reader.read();
            book = parser.parse(text);
        } catch (IOException e) {
            throw new BookDAOException("Error of reading file: ", e);
        }

        return book;
    }
}
