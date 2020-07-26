package by.epamtc.degtyarovea.controller;

import by.epamtc.degtyarovea.service.BookService;

public class Main {

    public static void main(String[] args) {
        BookService service = new BookService();
        String firstSentence;

//        firstSentence = service.printEachSentenceInNewLine();
        firstSentence = service.sentenceOrderByWordCount();

        System.out.println(firstSentence);
    }
}
