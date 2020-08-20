package by.epamtc.server.dao.impl.parsers;

import by.epamtc.server.dao.impl.AbstractParser;
import by.epamtc.server.dao.impl.PatternManager;
import by.epamtc.server.entity.TextComponent;
import by.epamtc.server.entity.TextComponentType;
import by.epamtc.server.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {

    private final Pattern pattern;

    public SentenceParser(AbstractParser nextParser) {
        super(nextParser);
        this.pattern = Pattern.compile(PatternManager.getInstance().getSentence());
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
