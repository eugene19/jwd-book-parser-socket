package by.epamtc.degtyarovea.dao.parsers;

import by.epamtc.degtyarovea.entity.Lexeme;
import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComponentType;
import by.epamtc.degtyarovea.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractParser {

    private static final String PARAGRAPH_PATTERN = ".+\\n*";

    public ParagraphParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite book = new TextComposite(TextComponentType.BOOK);
        Matcher matcher = Pattern.compile(PARAGRAPH_PATTERN).matcher(text);

        while (matcher.find()) {
            String paragraph = matcher.group();

            if (paragraph.contains("{")) {
                int bracketsCount = 1;
                StringBuilder codeLines = new StringBuilder(paragraph);

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

                TextComposite paragr = new TextComposite(TextComponentType.CODE);
                TextComposite sentence = new TextComposite(TextComponentType.CODE);
                sentence.addChildren(new Lexeme(codeLines.toString(), TextComponentType.CODE));
                paragr.addChildren(sentence);
                book.addChildren(paragr);
            } else {
                TextComponent paragraphComponent = parseNext(paragraph);
                book.addChildren(paragraphComponent);
            }
        }

        return book;
    }
}
