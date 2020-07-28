package by.epamtc.degtyarovea.dao.impl.parsers;

import by.epamtc.degtyarovea.dao.impl.AbstractParser;
import by.epamtc.degtyarovea.dao.impl.PatternManager;
import by.epamtc.degtyarovea.entity.Lexeme;
import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComponentType;
import by.epamtc.degtyarovea.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractParser {

    private final Pattern pattern;

    public LexemeParser(AbstractParser nextParser) {
        super(nextParser);
        this.pattern = Pattern.compile(PatternManager.getInstance().getLexeme());
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite sentence = new TextComposite(TextComponentType.SENTENCE);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String word = matcher.group();

            if (word.matches("[-.=()'%,\":;]")) {
                sentence.addChildren(new Lexeme(word, TextComponentType.PUNCTUATION));
            } else if (word.matches("(\\s+)")) {
                sentence.addChildren(new Lexeme(word, TextComponentType.SPACE));
            } else {
                sentence.addChildren(new Lexeme(word, TextComponentType.WORD));
            }
        }

        return sentence;
    }
}
