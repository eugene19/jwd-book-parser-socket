package by.epamtc.server.controller.impl;

import by.epamtc.server.controller.ServerController;
import by.epamtc.server.entity.TextComponent;
import by.epamtc.server.service.BookService;
import by.epamtc.server.service.BookServiceFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerControllerImpl implements ServerController {

    private int port;
    private BookService service;

    public ServerControllerImpl(int port) {
        this.port = port;
        this.service = BookServiceFactory.getInstance().getBookService();
    }

    public void run() {
        try (ServerSocket server = new ServerSocket(port)) {

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
