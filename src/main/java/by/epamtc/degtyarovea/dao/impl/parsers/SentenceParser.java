package by.epamtc.degtyarovea.dao.impl.parsers;

import by.epamtc.degtyarovea.dao.impl.AbstractParser;
import by.epamtc.degtyarovea.dao.impl.PatternManager;
import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComponentType;
import by.epamtc.degtyarovea.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {

    private final Pattern pattern;

    public SentenceParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
        this.pattern = Pattern.compile(PatternManager.getSentence());
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite paragraph = new TextComposite(TextComponentType.PARAGRAPH);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String sentence = matcher.group();
            TextComponent sentenceComponent = parseNext(sentence);

            paragraph.addChildren(sentenceComponent);
        }

        return paragraph;
    }
}
