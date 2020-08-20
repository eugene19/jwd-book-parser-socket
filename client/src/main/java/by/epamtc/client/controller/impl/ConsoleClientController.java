package by.epamtc.client.controller.impl;

import by.epamtc.client.controller.ClientController;
import by.epamtc.client.view.ConsoleMenu;
import by.epamtc.server.entity.TextComponent;

import java.io.*;
import java.net.Socket;

public class ConsoleClientController implements ClientController {

    private static final String HOST = "localhost";
    private static final int PORT = 5555;

    private ConsoleMenu consoleMenu = new ConsoleMenu();

    @Override
    public void start() {
        while (true) {
            String request = consoleMenu.selectAction();

            if (request.equalsIgnoreCase("Exit.")) {
                break;
            }

            String response = responseForRequest(request);

            System.out.printf("%nResponse:%n%s%n%n", response);
        }
    }

    private String responseForRequest(String action) {
        String response;

        try (Socket clientSocket = new Socket(HOST, PORT);
             ObjectInput input = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutput output = new ObjectOutputStream(clientSocket.getOutputStream())
        ) {
            output.writeBytes(action + "\n");
            output.flush();

            try {
                TextComponent responseComponent = (TextComponent) input.readObject();
                response = responseComponent.text();
            } catch (ClassNotFoundException e) {
                response = e.getMessage();
            }
        } catch (IOException e) {
            response = e.getMessage();
        }

        return response;
    }
}
