package by.epamtc.degtyarovea.dao.impl.parsers;

import by.epamtc.degtyarovea.dao.impl.AbstractParser;

public final class ParserFactory {

    private static final ParserFactory instance = new ParserFactory();
    private AbstractParser parser = new ParagraphParser(new SentenceParser(new LexemeParser(null)));

    private ParserFactory() {
    }

    public static ParserFactory getInstance() {
        return instance;
    }

    public AbstractParser getParser() {
        return parser;
    }
}
