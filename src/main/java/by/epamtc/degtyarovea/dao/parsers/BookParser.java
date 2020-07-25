package by.epamtc.degtyarovea.dao.parsers;

import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookParser extends AbstractParser {

    private static final String PARAGRAPH_PATTERN = ".+\\n+";

    public BookParser() {
        super(new ParagraphParser());
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite book = new TextComposite();
        Matcher matcher = Pattern.compile(PARAGRAPH_PATTERN).matcher(text);

        while (matcher.find()) {
            String paragraph = matcher.group();
            TextComponent paragraphComponent = parseNext(paragraph);

            book.addChildren(paragraphComponent);
        }

        return book;
    }
}
