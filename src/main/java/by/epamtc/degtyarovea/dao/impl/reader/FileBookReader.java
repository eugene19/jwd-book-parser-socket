package by.epamtc.degtyarovea.dao.impl.reader;

import java.io.IOException;

public interface FileBookReader {

    String BOOK_FILENAME = "coding_book.txt";

    String readText() throws IOException;

    boolean isEnd();
}
