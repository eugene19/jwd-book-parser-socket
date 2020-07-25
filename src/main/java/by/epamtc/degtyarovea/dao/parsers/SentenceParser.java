package by.epamtc.degtyarovea.dao.parsers;

import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {

    private static final String WORD_PATTERN = "[-\\w,:()\"]+";

    public SentenceParser() {
        super(new WordParser());
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite sentence = new TextComposite();
        Matcher matcher = Pattern.compile(WORD_PATTERN).matcher(text);

        while (matcher.find()) {
            String word = matcher.group();
            TextComponent wordComponent = parseNext(word);

            sentence.addChildren(wordComponent);
        }

        return sentence;
    }
}
