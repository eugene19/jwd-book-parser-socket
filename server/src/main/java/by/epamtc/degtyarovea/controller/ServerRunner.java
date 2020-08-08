package by.epamtc.degtyarovea.controller;

import by.epamtc.degtyarovea.service.BookService;
import by.epamtc.degtyarovea.service.BookServiceFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRunner {

    private static final int PORT = 5555;

    private static ServerSocket server;
    private static BufferedReader input;
    private static BufferedWriter output;

    private static BookService service = BookServiceFactory.getInstance().getBookService();

    public static void main(String[] args) throws IOException {
        try {
            server = new ServerSocket(PORT);

            try (Socket clientSocket = server.accept()) {
                input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String request = input.readLine();
                System.out.println("Client received: " + request);

                switch (request) {
                    case "REPLACE WORD WITH LENGTH":
                        output.write(test1());
                        break;
                    case "GET WORD IN FIRST SENTENCE":
                        output.write(test2());
                        break;
                    case "REPLACE FIRST AND LAST WORDS":
                        output.write(test3());
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                output.flush();

            } finally {
                input.close();
                output.close();
            }
        } finally {
            server.close();
        }
    }


    private static String test1() {
        return service.replaceWordsConcreteLengthInSentence(4, 2, "***").text();
    }

    private static String test2() {
        return service.wordInFirstSentenceAbsentInAnother().text();
    }

    private static String test3() {
        return service.replaceFirstAndLastWordsInSentence().text();
    }
}
