package by.epamtc.degtyarovea.dao.parsers;

import by.epamtc.degtyarovea.entity.Lexeme;
import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComponentType;
import by.epamtc.degtyarovea.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractParser {

    private static final String LEXEME_PATTERN = "(([-\\w]+)|([-\\.=()'%,\\\":;])|(\\s+))";

    public LexemeParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite sentence = new TextComposite(TextComponentType.SENTENCE);
        Matcher matcher = Pattern.compile(LEXEME_PATTERN).matcher(text);

        while (matcher.find()) {
            String word = matcher.group();

            if (word.matches("[-\\.=()'%,\\\":;]")) {
                sentence.addChildren(new Lexeme(word, TextComponentType.PUNCTUATION));
            } else if (word.matches("(\\s+)")) {
                sentence.addChildren(new Lexeme(word, TextComponentType.SPACE));
            } else if (word.contains("{")) {
                sentence.addChildren(new Lexeme(word, TextComponentType.CODE));
            } else {
                sentence.addChildren(new Lexeme(word, TextComponentType.WORD));
            }
        }

        return sentence;
    }
}
