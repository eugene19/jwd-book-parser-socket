package by.epamtc.degtyarovea.dao.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PatternManager {

    private static final String PATTERNS_FILENAME = "patterns.properties";
    private static final PatternManager instance = new PatternManager();

    private String paragraph = ".+\\n*";
    private String sentence = ".+?(([.!?]\\s?)|(\\n))";
    private String lexeme = "(([-\\w]+)|([-.=()'%,\":;])|(\\s+))";

    private PatternManager() {
        ClassLoader loader = getClass().getClassLoader();
        String filePath = Objects.requireNonNull(loader.getResource(PATTERNS_FILENAME)).getFile();
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(filePath));
            paragraph = properties.getProperty("paragraph.pattern");
            sentence = properties.getProperty("sentence.pattern");
            lexeme = properties.getProperty("lexeme.pattern");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PatternManager getInstance() {
        return instance;
    }

    public String getParagraph() {
        return paragraph;
    }

    public String getSentence() {
        return sentence;
    }

    public String getLexeme() {
        return lexeme;
    }
}
