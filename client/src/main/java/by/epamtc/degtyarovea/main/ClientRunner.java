package by.epamtc.degtyarovea.main;

import by.epamtc.degtyarovea.entity.TextComponent;

import java.io.*;
import java.net.Socket;

public class ClientRunner {

    private static final String HOST = "localhost";
    private static final int PORT = 5555;

    private static Socket clientSocket;
    private static ObjectInput input;
    private static ObjectOutput output;

    public static void main(String[] args) throws IOException {

        try {
            Menu menu = new Menu();
            String action;

            while ((action = menu.selectAction()) != null) {
                clientSocket = new Socket(HOST, PORT);

                output = new ObjectOutputStream(clientSocket.getOutputStream());
                input = new ObjectInputStream(clientSocket.getInputStream());

                output.writeBytes(action + "\n");
                output.flush();

                try {
                    TextComponent response = (TextComponent) input.readObject();

                    System.out.println("****************************************");
                    System.out.println(response.text());
                    System.out.println("****************************************");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            clientSocket.close();
            input.close();
            output.close();
        }
    }
}
