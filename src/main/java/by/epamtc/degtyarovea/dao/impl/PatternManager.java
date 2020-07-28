package by.epamtc.degtyarovea.dao.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PatternManager {

    private static final String PATTERNS_FILEPATH = "src/main/resources/patterns.properties";

    private static String paragraph = ".+\\n*";
    private static String sentence = ".+?(([.!?]\\s?)|(\\n))";
    private static String lexeme = "(([-\\w]+)|([-.=()'%,\":;])|(\\s+))";

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PATTERNS_FILEPATH));
            paragraph = properties.getProperty("paragraph.pattern");
            sentence = properties.getProperty("sentence.pattern");
            lexeme = properties.getProperty("lexeme.pattern");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getParagraph() {
        return paragraph;
    }

    public static String getSentence() {
        return sentence;
    }

    public static String getLexeme() {
        return lexeme;
    }
}
