package by.epamtc.degtyarovea.dao.impl;

import by.epamtc.degtyarovea.entity.TextComponent;

public abstract class AbstractParser {

    protected AbstractParser nextParser;

    public AbstractParser() {
    }

    public TextComponent parseNext(String text) {
        if (nextParser == null) {
            return null;
        }

        return nextParser.parse(text);
    }

    public abstract TextComponent parse(String text);
}
