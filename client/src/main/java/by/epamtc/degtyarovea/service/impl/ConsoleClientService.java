package by.epamtc.degtyarovea.service.impl;

import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.service.ClientService;

import java.io.*;
import java.net.Socket;

public class ConsoleClientService implements ClientService {

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
