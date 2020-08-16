package by.epamtc.degtyarovea.controller;

import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.service.BookService;
import by.epamtc.degtyarovea.service.BookServiceFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRunner {

    private static final int PORT = 5555;

    private static BookService service = BookServiceFactory.getInstance().getBookService();

    private static ServerSocket server;
    private static ObjectInput input;
    private static ObjectOutput output;

    public static void main(String[] args) throws IOException {
        try {
            server = new ServerSocket(PORT);

            while (true) {
                try (Socket clientSocket = server.accept()) {
                    output = new ObjectOutputStream(clientSocket.getOutputStream());
                    input = new ObjectInputStream(clientSocket.getInputStream());

                    String request = input.readLine();

                    switch (request) {
                        case "Replace words concrete length in sentence.":
                            output.writeObject(test1());
                            break;
                        case "Word In First Sentence Absent In Another.":
                            output.writeObject(test2());
                            break;
                        case "Replace First And Last Words In Sentence.":
                            output.writeObject(test3());
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                    output.flush();
                } finally {
                    input.close();
                    output.close();
                }
            }
        } finally {
            server.close();
        }
    }

    private static TextComponent test1() {
        return service.replaceWordsConcreteLengthInSentence(4, 2, "***");
    }

    private static TextComponent test2() {
        return service.wordInFirstSentenceAbsentInAnother();
    }

    private static TextComponent test3() {
        return service.replaceFirstAndLastWordsInSentence();
    }
}
