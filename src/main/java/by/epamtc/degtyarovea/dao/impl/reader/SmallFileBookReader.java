package by.epamtc.degtyarovea.dao.impl.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Read all file as one part.
 */
public class SmallFileBookReader implements FileBookReader {

    private File file;
    private boolean allFileRead;

    public SmallFileBookReader(File file) {
        this.file = file;
        allFileRead = false;
    }

    @Override
    public boolean hasNextPart() {
        return !allFileRead;
    }

    @Override
    public String nextPart() throws IOException {
        allFileRead = true;
        return new String(Files.readAllBytes(Paths.get(file.toURI())));
    }
}
