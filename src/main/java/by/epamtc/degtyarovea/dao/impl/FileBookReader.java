package by.epamtc.degtyarovea.dao.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileBookReader {

    private static final String BOOK_FILENAME = "coding_book.txt";

    private File file;

    public FileBookReader() {
        ClassLoader loader = getClass().getClassLoader();
        this.file = new File(loader.getResource(BOOK_FILENAME).getFile());
    }

    public String readAllText() throws IOException {
        return new String(Files.readAllBytes(Paths.get(file.toURI())));
    }

}
