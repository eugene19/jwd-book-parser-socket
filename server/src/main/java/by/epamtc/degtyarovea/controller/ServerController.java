package by.epamtc.degtyarovea.controller;

import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.service.BookService;
import by.epamtc.degtyarovea.service.BookServiceFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    private static final int PORT = 5555;
    private BookService service = BookServiceFactory.getInstance().getBookService();

    public void start() {
        try (ServerSocket server = new ServerSocket(PORT)) {

            while (true) {
                try (Socket clientSocket = server.accept();
                     ObjectOutput output = new ObjectOutputStream(clientSocket.getOutputStream());
                     ObjectInput input = new ObjectInputStream(clientSocket.getInputStream())
                ) {
                    String request = input.readLine();

                    switch (request) {
                        case "Replace words concrete length in sentence.":
                            output.writeObject(replaceWordsConcreteLength());
                            break;
                        case "Word in first sentence absent in another.":
                            output.writeObject(wordInFirstSentenceAbsentInAnother());
                            break;
                        case "Replace first and last words in sentence.":
                            output.writeObject(replaceFirstAndLastWordsInSentence());
                            break;
                    }
                    output.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TextComponent replaceWordsConcreteLength() {
        return service.replaceWordsConcreteLengthInSentence(4, 2, "***");
    }

    private TextComponent wordInFirstSentenceAbsentInAnother() {
        return service.wordInFirstSentenceAbsentInAnother();
    }

    private TextComponent replaceFirstAndLastWordsInSentence() {
        return service.replaceFirstAndLastWordsInSentence();
    }
}
