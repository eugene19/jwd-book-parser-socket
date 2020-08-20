package by.epamtc.server.service;

import by.epamtc.server.entity.TextComponent;

public interface BookService {

    String orderSentencesByWordsCount();

    TextComponent wordInFirstSentenceAbsentInAnother();

    TextComponent replaceFirstAndLastWordsInSentence();

    TextComponent replaceWordsConcreteLengthInSentence(int wordLength, int sentenceNumber, String text);
}
