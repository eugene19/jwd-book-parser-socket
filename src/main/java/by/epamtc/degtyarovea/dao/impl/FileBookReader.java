package by.epamtc.degtyarovea.dao.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class FileBookReader {

    private static final String BOOK_FILENAME = "coding_book.txt";

    private File file;

    public FileBookReader() {
        ClassLoader loader = getClass().getClassLoader();
        String filePath = Objects.requireNonNull(loader.getResource(BOOK_FILENAME)).getFile();
        this.file = new File(filePath);
    }

    public String readAllText() throws IOException {
        return new String(Files.readAllBytes(Paths.get(file.toURI())));
    }
}
