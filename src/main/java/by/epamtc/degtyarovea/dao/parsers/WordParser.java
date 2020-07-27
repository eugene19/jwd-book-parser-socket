package by.epamtc.degtyarovea.dao.parsers;

import by.epamtc.degtyarovea.entity.SentencePart;
import by.epamtc.degtyarovea.entity.TextComponent;

public class WordParser extends AbstractParser {

    public WordParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public TextComponent parse(String text) {
        return new SentencePart(text);
    }
}
