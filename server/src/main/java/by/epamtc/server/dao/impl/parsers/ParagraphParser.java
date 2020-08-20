package by.epamtc.server.dao.impl.parsers;

import by.epamtc.server.dao.impl.AbstractParser;
import by.epamtc.server.dao.impl.PatternManager;
import by.epamtc.server.entity.Lexeme;
import by.epamtc.server.entity.TextComponent;
import by.epamtc.server.entity.TextComponentType;
import by.epamtc.server.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractParser {

    private final Pattern pattern;

    public ParagraphParser(AbstractParser nextParser) {
        super(nextParser);
        this.pattern = Pattern.compile(PatternManager.getInstance().getParagraph());
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite book = new TextComposite(TextComponentType.BOOK);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String paragraphGroup = matcher.group();

            if (paragraphGroup.contains("{")) {
                int bracketsCount = 1;
                StringBuilder codeLines = new StringBuilder(paragraphGroup);

                while (matcher.find()) {
                    String line = matcher.group();
                    if (line.contains("{")) {
                        bracketsCount++;
                    }
                    if (line.contains("}")) {
                        bracketsCount--;
                    }

                    codeLines.append(line);

                    if (bracketsCount == 0) {
                        break;
                    }
                }

                TextComposite paragraph = new TextComposite(TextComponentType.CODE);
                TextComposite sentence = new TextComposite(TextComponentType.CODE);
                sentence.addChildren(new Lexeme(codeLines.toString(), TextComponentType.CODE));
                paragraph.addChildren(sentence);
                book.addChildren(paragraph);
            } else {
                TextComponent paragraphComponent = parseNext(paragraphGroup);
                book.addChildren(paragraphComponent);
            }
        }

        return book;
    }
}
