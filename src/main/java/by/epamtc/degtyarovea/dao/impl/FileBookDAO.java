package by.epamtc.degtyarovea.dao.impl;

import by.epamtc.degtyarovea.dao.BookDAO;
import by.epamtc.degtyarovea.dao.BookDAOException;
import by.epamtc.degtyarovea.dao.impl.parsers.ParserFactory;
import by.epamtc.degtyarovea.dao.impl.reader.BookReaderFactory;
import by.epamtc.degtyarovea.dao.impl.reader.FileBookReader;
import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComponentType;
import by.epamtc.degtyarovea.entity.TextComposite;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class FileBookDAO implements BookDAO {

    private static final String BOOK_FILENAME = "coding_book.txt";

    private FileBookReader reader;
    private AbstractParser parser;

    public FileBookDAO() {
        ClassLoader loader = getClass().getClassLoader();
        String filePath = Objects.requireNonNull(loader.getResource(BOOK_FILENAME)).getFile();

        this.reader = BookReaderFactory.getInstance().getFileBookReader(new File(filePath));
        this.parser = ParserFactory.getInstance().getParser();
    }

    @Override
    public TextComponent createBook() throws BookDAOException {
        TextComposite book = new TextComposite(TextComponentType.BOOK);

        while (reader.hasNextPart()) {
            try {
                String text = reader.nextPart();
                TextComponent bookPart = parser.parse(text);
                addAllParts(book, (TextComposite) bookPart);
            } catch (IOException e) {
                throw new BookDAOException("Error of reading file: ", e);
            }
        }

        return book;
    }

    private void addAllParts(TextComposite book, TextComposite parse) {
        List<TextComponent> children = parse.getChildren();

        for (TextComponent child : children) {
            book.addChildren(child);
        }
    }
}
