package by.epamtc.degtyarovea.controller;

import by.epamtc.degtyarovea.entity.TextComponent;

public class TextComponentPrinter {

    public static void printToConsole(TextComponent composite) {
        System.out.println(composite.text());
    }
}
