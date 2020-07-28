package by.epamtc.degtyarovea.dao.impl;

import by.epamtc.degtyarovea.dao.BookDAO;
import by.epamtc.degtyarovea.dao.impl.parsers.AbstractParser;
import by.epamtc.degtyarovea.dao.impl.parsers.ParserFactory;
import by.epamtc.degtyarovea.dao.impl.reader.FileBookReader;
import by.epamtc.degtyarovea.dao.impl.reader.FileBookReaderFactory;
import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComponentType;
import by.epamtc.degtyarovea.entity.TextComposite;

import java.io.IOException;

public class FileBookDAO implements BookDAO {

    private FileBookReader reader;
    private AbstractParser parser;

    public FileBookDAO() {
        this.reader = FileBookReaderFactory.getInstance().getFileBookReader();
        this.parser = ParserFactory.getInstance().getParser();
    }

    @Override
    public TextComponent createBook() throws IOException {
        TextComposite book = new TextComposite(TextComponentType.BOOK);

        while (!reader.isEnd()) {
            String partOfText = reader.readText();
            book.addChildren(parser.parse(partOfText));
        }

        return book;
    }
}
