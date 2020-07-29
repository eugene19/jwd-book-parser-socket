package by.epamtc.degtyarovea.dao.impl.reader;

import java.io.IOException;

public interface FileBookReader {

    boolean hasNextPart();

    String nextPart() throws IOException;
}
