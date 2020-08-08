package by.epamtc.degtyarovea.dao.impl.reader;

import java.io.File;

public final class BookReaderFactory {

    private static final int BIG_FILE_SIZE = 10_000;

    private static final BookReaderFactory instance = new BookReaderFactory();

    private BookReaderFactory() {
    }

    public static BookReaderFactory getInstance() {
        return instance;
    }

    public FileBookReader getFileBookReader(File file) {
        return (file.length() > BIG_FILE_SIZE) ? new BigFileBookReader(file) : new SmallFileBookReader(file);
    }
}
