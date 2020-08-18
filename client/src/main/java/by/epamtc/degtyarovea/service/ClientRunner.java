package by.epamtc.degtyarovea.service;

import by.epamtc.degtyarovea.service.impl.ConsoleClientService;

public class ClientRunner {

    public static void main(String[] args) {
        ClientService service = new ConsoleClientService();
        service.start();
    }
}
