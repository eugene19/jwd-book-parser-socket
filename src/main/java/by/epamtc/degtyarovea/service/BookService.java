package by.epamtc.degtyarovea.service;

import by.epamtc.degtyarovea.entity.TextComponent;

public interface BookService {

    String orderSentencesByWordsCount();

    TextComponent wordInFirstSentenceAbsentInAnother();

    TextComponent replaceFirstAndLastWordsInSentence();

    TextComponent replaceWordsConcreteLengthInSentence(int wordLength, int sentenceNumber, String text);
}
