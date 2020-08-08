package by.epamtc.degtyarov.main;

import java.util.Scanner;

public class Menu {

    public String selectAction() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input number of action:\n" +
                "1. Replace words concrete length in sentence\n" +
                "2. Word In First Sentence Absent In Another\n" +
                "3. Replace First And Last Words In Sentence\n");

        int number = scanner.nextInt();

        switch (number) {
            case 1:
                return "REPLACE WORD WITH LENGTH";
            case 2:
                return "GET WORD IN FIRST SENTENCE";
            case 3:
                return "REPLACE FIRST AND LAST WORDS";
            default:
                throw new IllegalArgumentException();
        }
    }
}
