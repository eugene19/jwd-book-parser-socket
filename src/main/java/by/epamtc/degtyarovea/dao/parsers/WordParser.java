package by.epamtc.degtyarovea.dao.parsers;

import by.epamtc.degtyarovea.entity.SentencePart;
import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComponentType;

public class WordParser extends AbstractParser {

    public WordParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public TextComponent parse(String text) {
//        if (text.matches("([-=()'%,\\\":;])")) {
//            return new SentencePart(text, TextComponentType.PUNCTUATION);
//        }
//        if (text.matches("(\\s+)")) {
//            return new SentencePart(text, TextComponentType.SPACE);
//        }
//        if (text.contains("{")) {
//            return new SentencePart(text, TextComponentType.CODE);
//        }
        return new SentencePart(text, TextComponentType.WORD);
    }
}
