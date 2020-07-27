package by.epamtc.degtyarovea.dao.parsers;

import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractParser {

    private static final String SENTENCE_PATTERN = ".+?(([.!?]\\s?)|(\\n))";

    public ParagraphParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite paragraph = new TextComposite();
        Matcher matcher = Pattern.compile(SENTENCE_PATTERN).matcher(text);

        while (matcher.find()) {
            String sentence = matcher.group();
            TextComponent sentenceComponent = parseNext(sentence);

            paragraph.addChildren(sentenceComponent);
        }

        return paragraph;
    }
}
