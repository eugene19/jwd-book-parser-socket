package by.epamtc.degtyarovea.controller;

import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.service.BookService;
import by.epamtc.degtyarovea.service.BookServiceFactory;

public class Main {

    public static void main(String[] args) {
        BookService service = BookServiceFactory.getInstance().getBookService();
        TextComponent book;

        book = service.replaceWordsConcreteLengthInSentence(4, 2, "***");
//        book = service.wordInFirstSentenceAbsentInAnother();
//        book = service.replaceFirstAndLastWordsInSentence();

        TextComponentPrinter.printToConsole(book);
    }
}
