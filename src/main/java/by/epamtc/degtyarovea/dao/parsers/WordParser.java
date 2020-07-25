package by.epamtc.degtyarovea.dao.parsers;

import by.epamtc.degtyarovea.entity.Symbol;
import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComposite;

public class WordParser extends AbstractParser {

    public WordParser() {
        super(null);
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite word = new TextComposite();

        char[] characters = text.toCharArray();

        for (char character : characters) {
            word.addChildren(new Symbol(character));
        }

        return word;
    }
}
