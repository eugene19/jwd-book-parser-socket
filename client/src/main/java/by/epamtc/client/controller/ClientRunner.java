package by.epamtc.client.controller;

import by.epamtc.client.controller.impl.ConsoleClientController;

public class ClientRunner {

    public static void main(String[] args) {
        ClientController service = new ConsoleClientController();
        service.start();
    }
}
