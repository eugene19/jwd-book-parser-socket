package by.epamtc.degtyarovea.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private Map<Integer, String> points = new LinkedHashMap<>();
    private Scanner scanner = new Scanner(System.in);

    {
        points.put(1, "Replace words concrete length in sentence.");
        points.put(2, "Word In First Sentence Absent In Another.");
        points.put(3, "Replace First And Last Words In Sentence.");
        points.put(0, "Exit.");
    }

    public String selectAction() throws NoSuchPointException {
        int number = scanner.nextInt();

        if (!points.containsKey(number)) {
            throw new NoSuchPointException("No such point: " + number);
        }

        return points.get(number);
    }

    public Map<Integer, String> getPoints() {
        return points;
    }
}
