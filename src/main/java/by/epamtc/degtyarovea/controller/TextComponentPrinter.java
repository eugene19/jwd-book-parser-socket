package by.epamtc.degtyarovea.controller;

import by.epamtc.degtyarovea.entity.TextComponent;

public class TextComponentPrinter {

    public static void printToConsole(TextComponent composite) {
        if (composite == null) {
            System.out.println("There is no text.");
        } else {
            System.out.println(composite.text());
        }
    }
}
