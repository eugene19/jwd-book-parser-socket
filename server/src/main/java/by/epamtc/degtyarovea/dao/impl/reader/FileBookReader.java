package by.epamtc.degtyarovea.dao.impl.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Read all file as one part.
 */
public class FileBookReader {

    private File file;

    public FileBookReader(File file) {
        this.file = file;
    }

    public String readAllText() throws IOException {
        return new String(Files.readAllBytes(Paths.get(file.toURI())));
    }
}
