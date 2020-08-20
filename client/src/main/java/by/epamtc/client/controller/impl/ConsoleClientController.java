package by.epamtc.client.controller.impl;

import by.epamtc.client.controller.ClientController;
import by.epamtc.client.view.ConsoleMenu;
import by.epamtc.client.view.ConsolePrinter;
import by.epamtc.client.view.WrongMenuActionException;
import by.epamtc.server.entity.TextComponent;

import java.io.*;
import java.net.Socket;

public class ConsoleClientController implements ClientController {

    private String host;
    private int port;
    private ConsoleMenu menu;
    private ConsolePrinter printer;

    public ConsoleClientController(String host, int port) {
        this.host = host;
        this.port = port;
        this.menu = new ConsoleMenu();
        this.printer = new ConsolePrinter();
    }

    @Override
    public void run() {
        while (true) {
            printer.showMenu(menu);

            String request;
            try {
                request = menu.selectAction();
            } catch (WrongMenuActionException e) {
                printer.printError(e.getMessage());
                continue;
            }

            if (request.equalsIgnoreCase("Exit.")) {
                break;
            }

            String response = sendRequest(request);
            printer.printText("\nResponse:\n" + response + "\n");
        }
    }

    private String sendRequest(String request) {
        String response;

        try (Socket clientSocket = new Socket(host, port);
             ObjectInput input = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutput output = new ObjectOutputStream(clientSocket.getOutputStream())
        ) {
            output.writeBytes(request + "\n");
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
