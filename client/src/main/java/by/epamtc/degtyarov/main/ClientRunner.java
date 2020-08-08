package by.epamtc.degtyarov.main;

import java.io.*;
import java.net.Socket;

public class ClientRunner {

    private static final String HOST = "localhost";
    private static final int PORT = 5555;

    private static Socket clientSocket;
    private static BufferedReader input;
    private static BufferedWriter output;

    public static void main(String[] args) throws IOException {

        try {
            Menu menu = new Menu();
            String action;

            while ((action = menu.selectAction()) != null) {
                clientSocket = new Socket(HOST, PORT);

                input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                output.write(action + "\n");
                output.flush();

                StringBuilder response = new StringBuilder();
                String line;

                while ((line = input.readLine()) != null) {
                    response.append(line).append("\n");
                }

                System.out.println("****************************************");
                System.out.println(response);
                System.out.println("****************************************");
            }
        } finally {
            clientSocket.close();
            input.close();
            output.close();
        }
    }
}
