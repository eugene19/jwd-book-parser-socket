package by.epamtc.degtyarovea.service.impl;

import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.service.ClientService;
import by.epamtc.degtyarovea.service.Menu;
import by.epamtc.degtyarovea.service.NoSuchPointException;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ConsoleClientService implements ClientService {

    private static Socket clientSocket;
    private static ObjectInput input;
    private static ObjectOutput output;

    @Override
    public void start() {
        try {
            Menu menu = new Menu();
            String action;

            while (true) {
                printMenu(menu);

                try {
                    action = menu.selectAction();
                } catch (NoSuchPointException e) {
                    System.err.println(e.getMessage());
                    continue;
                }

                if (action.equalsIgnoreCase("Exit.")) {
                    break;
                }

                clientSocket = new Socket(HOST, PORT);

                output = new ObjectOutputStream(clientSocket.getOutputStream());
                input = new ObjectInputStream(clientSocket.getInputStream());

                output.writeBytes(action + "\n");
                output.flush();

                try {
                    TextComponent response = (TextComponent) input.readObject();

                    System.out.println("****************************************");
                    System.out.println("RESPONSE:");
                    System.out.println("****************************************");
                    System.out.println(response.text());
                    System.out.println("****************************************");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                input.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void printMenu(Menu menu) {
        System.out.println("Select number of action:");

        for (Map.Entry<Integer, String> entry : menu.getPoints().entrySet()) {
            System.out.printf("%d. %s\n", entry.getKey(), entry.getValue());
        }
    }
}
