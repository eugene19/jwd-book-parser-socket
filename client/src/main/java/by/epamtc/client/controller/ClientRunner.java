package by.epamtc.client.controller;

import by.epamtc.client.controller.impl.ConsoleClientController;

public class ClientRunner {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 5555;

        ClientController service = new ConsoleClientController(host, port);
        service.run();
    }
}
