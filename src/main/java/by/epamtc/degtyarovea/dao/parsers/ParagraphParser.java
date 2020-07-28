package by.epamtc.degtyarovea.dao.parsers;

import by.epamtc.degtyarovea.entity.Lexeme;
import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComponentType;
import by.epamtc.degtyarovea.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractParser {

    private final Pattern pattern = Pattern.compile(".+\\n*");

    public ParagraphParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
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
