package by.epamtc.degtyarovea.service;

import by.epamtc.degtyarovea.service.impl.ConsoleClientService;

import java.io.IOException;

public class Runner {

    public static void main(String[] args) throws IOException {
        ClientService service = new ConsoleClientService();
        service.start();
    }
}
