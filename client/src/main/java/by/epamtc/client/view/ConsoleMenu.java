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
        int number = scanner.nextInt();

        if (!actions.containsKey(number)) {
            throw new IllegalArgumentException("Wrong number of action: " + number);
        } else {
            return actions.get(number);
        }
    }

    public Map<Integer, String> getActions() {
        return actions;
    }
}
