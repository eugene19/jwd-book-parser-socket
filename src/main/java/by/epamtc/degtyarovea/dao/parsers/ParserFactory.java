package by.epamtc.degtyarovea.dao.parsers;

public final class ParserFactory {

    private static final ParserFactory factory = new ParserFactory();
    private AbstractParser parser;

    private ParserFactory() {
    }

    public static ParserFactory getInstance() {
        return factory;
    }

    public AbstractParser getParser() {
        if (parser == null) {
            parser = new BookParser(new ParagraphParser(new SentenceParser(new WordParser(null))));
        }
        return parser;
    }
}
