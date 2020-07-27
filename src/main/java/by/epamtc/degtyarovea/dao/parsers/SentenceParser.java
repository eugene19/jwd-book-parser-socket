package by.epamtc.degtyarovea.dao.parsers;

import by.epamtc.degtyarovea.entity.SentencePart;
import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComponentType;
import by.epamtc.degtyarovea.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {

    private static final String WORD_PATTERN = "(([-\\w]+)|([-\\.=()'%,\\\":;])|(\\s+))";

    public SentenceParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite sentence = new TextComposite(TextComponentType.SENTENCE);
        Matcher matcher = Pattern.compile(WORD_PATTERN).matcher(text);

        while (matcher.find()) {
            String word = matcher.group();

            if (word.matches("[-\\.=()'%,\\\":;]")) {
                sentence.addChildren(new SentencePart(word, TextComponentType.PUNCTUATION));
            } else if (word.matches("(\\s+)")) {
                sentence.addChildren(new SentencePart(word, TextComponentType.SPACE));
            } else if (word.contains("{")) {
                sentence.addChildren(new SentencePart(word, TextComponentType.CODE));
            } else {
                sentence.addChildren(new SentencePart(word, TextComponentType.WORD));
            }
        }

        return sentence;
    }
}
