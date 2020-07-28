package by.epamtc.degtyarovea.dao.impl.reader;

public final class FileBookReaderFactory {

    private static final FileBookReaderFactory instance = new FileBookReaderFactory();
    private final FileBookReader fileBookReader = new SmallFileBookReader();

    private FileBookReaderFactory() {
    }

    public static FileBookReaderFactory getInstance() {
        return instance;
    }

    public FileBookReader getFileBookReader() {
        return fileBookReader;
    }
}
