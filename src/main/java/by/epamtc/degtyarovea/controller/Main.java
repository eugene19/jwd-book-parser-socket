package by.epamtc.degtyarovea.controller;

import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.service.BookService;

public class Main {

    public static void main(String[] args) {
        BookService service = new BookService();
        TextComponent textComponent;

        //textComponent = service.replaceWordsConcreteLengthInSentence(4, 2, "***");
        //textComponent = service.replaceFirstAndLastWordsInSentence();
        textComponent = service.wordInFirstSentenceAbsentInAnother();

        TextComponentPrinter.printToConsole(textComponent);
    }
}
