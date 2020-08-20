package by.epamtc.client.view;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleMenu {

    private Map<Integer, String> actions = new LinkedHashMap<>();
    private Scanner scanner = new Scanner(System.in);

    {
        actions.put(1, "Replace words concrete length in sentence.");
        actions.put(2, "Word in first sentence absent in another.");
        actions.put(3, "Replace first and last words in sentence.");
        actions.put(0, "Exit.");
    }

    public String selectAction() {
        printMenuToConsole();

        while (true) {
            int number = scanner.nextInt();

            if (!actions.containsKey(number)) {
                System.err.println("Wrong number of action: " + number);
            } else {
                return actions.get(number);
            }
        }
    }

    private void printMenuToConsole() {
        System.out.println("Select number of action:");

        for (Map.Entry<Integer, String> entry : actions.entrySet()) {
            System.out.printf("%d. %s\n", entry.getKey(), entry.getValue());
        }
    }
}
