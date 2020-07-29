package by.epamtc.degtyarovea.dao.impl.reader;

import java.io.*;

public class BigFileBookReader implements FileBookReader {

    private static final int PART_SIZE = 10;

    private File file;
    private long fileSize;
    private long readSize;

    public BigFileBookReader(File file) {
        this.file = file;
        fileSize = file.length();
    }

    @Override
    public boolean hasNextPart() {
        return fileSize > readSize;
    }

    @Override
    public String nextPart() throws IOException {
        Reader input = new BufferedReader(new FileReader(file));
        StringBuilder paragraph = new StringBuilder();

        //noinspection ResultOfMethodCallIgnored
        input.skip(readSize);

        // TODO: make decomposition
        for (int i = 0; i < PART_SIZE; i++) {
            while (true) {
                int read = input.read();
                readSize++;

                if (read == '{') {
                    int bracketsCount = 1;
                    StringBuilder codeLines = new StringBuilder();
                    codeLines.append((char) read);

                    while (true) {
                        read = input.read();
                        readSize++;
                        if (read == '}') {
                            bracketsCount--;
                        } else if (read == '{') {
                            bracketsCount++;
                        }
                        if (read == '}' && bracketsCount == 0) {
                            break;
                        }
                        codeLines.append((char) read);
                    }
                    paragraph.append(codeLines);
                }
                if (read == '\n' || read == -1) {
                    break;
                }
                paragraph.append((char) read);
            }
            paragraph.append('\n');
        }

        return paragraph.toString();
    }
}
