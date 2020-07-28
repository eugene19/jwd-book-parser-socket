package by.epamtc.degtyarovea.dao.impl.reader;

public final class BookReaderFactory {

    private static final BookReaderFactory instance = new BookReaderFactory();
    private final FileBookReader fileBookReader = new SmallFileBookReader();

    private BookReaderFactory() {
    }

    public static BookReaderFactory getInstance() {
        return instance;
    }

    public FileBookReader getFileBookReader() {
        return fileBookReader;
    }
}
