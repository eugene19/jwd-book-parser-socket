package by.epamtc.degtyarovea.service;

import by.epamtc.degtyarovea.dao.BookDAO;
import by.epamtc.degtyarovea.dao.DAOFactory;
import by.epamtc.degtyarovea.entity.Lexeme;
import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComponentType;
import by.epamtc.degtyarovea.entity.TextComposite;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BookService {

    private BookDAO dao;

    public BookService() {
        this.dao = DAOFactory.getInstance().getBookDAO();
    }

    // Task #2
    public String orderSentencesByWordsCount() {
        TextComponent book = dao.createBook();
        Map<Integer, Integer> sentenceAndCountPair = new LinkedHashMap<>();
        List<String> sentencesList = new ArrayList();
        int sentenceCount = 0;

        List<TextComponent> paragraphs = ((TextComposite) book).getChildren();
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = ((TextComposite) paragraph).getChildren();

            for (TextComponent sentence : sentences) {
                if (sentence.getType() != TextComponentType.CODE) {
                    int sentenceLength = sentence.text().length();
                    sentenceAndCountPair.put(sentenceCount++, sentenceLength);
                    sentencesList.add(sentence.text());
                }
            }
        }

        return printSentences(sentencesList, sentenceAndCountPair);
    }

    private String printSentences(List<String> sentences, Map<Integer, Integer> sentenceAndCountPair) {
        StringBuilder sortedSent = new StringBuilder();

        while (sentenceAndCountPair.size() > 0) {
            int maxSen = maxWords(sentenceAndCountPair);
            String text1 = sentences.get(maxSen);

            sortedSent.append(text1.trim()).append("\n");
            sentenceAndCountPair.remove(maxSen);
        }
        return sortedSent.toString();
    }

    private int maxWords(Map<Integer, Integer> sentenceAndCountPair) {
        int maxLength = 0;
        int maxSen = 0;

        for (Map.Entry<Integer, Integer> entry : sentenceAndCountPair.entrySet()) {
            if (maxLength < entry.getValue()) {
                maxLength = entry.getValue();
                maxSen = entry.getKey();
            }
        }

        return maxSen;
    }


    // Task #3
    public TextComponent wordInFirstSentenceAbsentInAnother() {
        TextComponent book = takeBook();
        List<TextComponent> sentences = takeSentencesWithoutCode(book);
        TextComponent firstSentence = sentences.get(0);

        List<TextComponent> wordsInFirstSentence = ((TextComposite) firstSentence).getChildren();

        for (TextComponent child : wordsInFirstSentence) {
            if (!anyOtherSentencesContainWord(child)) {
                return child;
            }
        }

        return null;
    }

    private boolean anyOtherSentencesContainWord(TextComponent child) {
        TextComponent book = takeBook();
        List<TextComponent> sentences = takeSentences(book);

        for (int i = 1; i < sentences.size(); i++) {
            List<TextComponent> words = ((TextComposite) sentences.get(i)).getChildren();

            for (TextComponent word : words) {
                if (word.equals(child)) {
                    return true;
                }
            }
        }

        return false;
    }


    // Task #5
    public TextComponent replaceFirstAndLastWordsInSentence() {
        TextComponent book = takeBook();
        List<TextComponent> sentences = takeSentences(book);

        for (TextComponent sentence : sentences) {
            if (sentence.getType() != TextComponentType.CODE) {
                replaceFirstWithLast(sentence);
            }
        }

        return book;
    }

    private void replaceFirstWithLast(TextComponent sentence) {
        List<TextComponent> lexemes = ((TextComposite) sentence).getChildren();

        for (int i = 0; i < lexemes.size(); i++) {
            if (lexemes.get(i).getType() == TextComponentType.WORD) {
                for (int j = lexemes.size() - 1; j >= 0; j--) {
                    if (lexemes.get(j).getType() == TextComponentType.WORD) {
                        TextComponent temp = lexemes.get(i);

                        lexemes.set(i, lexemes.get(j));
                        lexemes.set(j, temp);
                        break;
                    }
                }
                break;
            }
        }
    }


    // Task #16
    public TextComponent replaceWordsConcreteLengthInSentence(int wordLength, int sentenceNumber, String text) {
        TextComponent book = takeBook();
        List<TextComponent> sentences = takeSentences(book);
        TextComponent concreteSentence = sentences.get(sentenceNumber);

        List<TextComponent> words = ((TextComposite) concreteSentence).getChildren();
        for (TextComponent word : words) {
            if (word.text().length() == wordLength) {
                ((Lexeme) word).setLexeme(text);
            }
        }

        return book;
    }

    private List<TextComponent> takeSentencesWithoutCode(TextComponent book) {
        List<TextComponent> sentences = new ArrayList<>();

        for (TextComponent paragraph : takeParagraphs(book)) {
            if (paragraph.getType() != TextComponentType.CODE) {
                sentences.addAll(((TextComposite) paragraph).getChildren());
            }
        }

        return sentences;
    }

    private List<TextComponent> takeSentences(TextComponent book) {
        List<TextComponent> sentences = new ArrayList<>();

        for (TextComponent paragraph : takeParagraphs(book)) {
            List<TextComponent> paragraphSentences = ((TextComposite) paragraph).getChildren();
            sentences.addAll(paragraphSentences);
        }

        return sentences;
    }

    private List<TextComponent> takeParagraphs(TextComponent book) {
        return ((TextComposite) book).getChildren();
    }

    private TextComponent takeBook() {
        return dao.createBook();
    }
}
