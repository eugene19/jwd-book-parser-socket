package by.epamtc.server.controller;

import by.epamtc.server.controller.impl.ServerControllerImpl;

public class ServerRunner {

    public static void main(String[] args) {
        ServerController server = new ServerControllerImpl();
        server.run();
    }
}
