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
            clientSocket = new Socket(HOST, PORT);

            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            Menu menu = new Menu();
            String action = menu.selectAction();
            System.out.println("ACTION " + action);

            output.write(action + "\n");
            output.flush();

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = input.readLine()) != null) {
                response.append(line).append("\n");
            }

            System.out.println(response);

        } finally {
            clientSocket.close();
            input.close();
            output.close();
        }

//        try {
//            Socket socket = new Socket(HOST, PORT);
//            Menu menu = new Menu();
//            String action = menu.selectAction();
//
//            System.out.println("ACTION " + action);
//
//            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            output.write(action);
//            output.flush();
//
//
//            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            String response = input.readLine();
//
//            System.out.println("RESPONSE: " + response);
//
//            Thread.currentThread().wait(5000);
//
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
