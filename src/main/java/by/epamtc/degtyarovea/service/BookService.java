package by.epamtc.degtyarovea.service;

import by.epamtc.degtyarovea.dao.BookDAO;
import by.epamtc.degtyarovea.dao.DAOFactory;
import by.epamtc.degtyarovea.entity.SentencePart;
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

    public String wordInFirstSentenceAbsentInAnother() {
        TextComponent book = dao.createBook();
        List<TextComponent> paragraphs = ((TextComposite) book).getChildren();
        TextComponent firstParagraph = paragraphs.get(0);
        List<TextComponent> sentences = ((TextComposite) firstParagraph).getChildren();
        TextComponent firstSentence = sentences.get(0);

        TextComposite wordsInFirstSentence = (TextComposite) firstSentence;

        for (TextComponent child : wordsInFirstSentence.getChildren()) {
            if (!anyOtherSentencesContainWord(child)) {
                return child.text();
            }
        }

        return "There is no word unique.";
    }

    private boolean anyOtherSentencesContainWord(TextComponent child) {
        TextComponent book = dao.createBook();
        List<TextComponent> paragraphs = ((TextComposite) book).getChildren();
        for (int i = 0; i < paragraphs.size(); i++) {
            List<TextComponent> sentences = ((TextComposite) paragraphs.get(i)).getChildren();
            for (int j = 0; j < sentences.size(); j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                List<TextComponent> words = ((TextComposite) sentences.get(j)).getChildren();

                for (TextComponent word : words) {
                    if (word.equals(child)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public TextComponent replaceFirstAndLastWordsInSentence() {
        TextComponent book = getBook();
        List<TextComponent> sentences = getSentences(book);

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

    public TextComponent replaceWordsConcreteLengthInSentence(int wordLength, int sentenceNumber, String text) {
        TextComponent book = getBook();
        List<TextComponent> sentences = getSentences(book);
        TextComponent concreteSentence = sentences.get(sentenceNumber);

        List<TextComponent> words = ((TextComposite) concreteSentence).getChildren();
        for (TextComponent word : words) {
            if (word.text().length() == wordLength) {
                ((SentencePart) word).setSentencePart(text);
            }
        }

        return book;
    }

    private List<TextComponent> getSentencesWithoutCode(TextComponent book) {
        List<TextComponent> sentences = new ArrayList<>();

        for (TextComponent paragraph : getParagraphs(book)) {
            if (paragraph.getType() != TextComponentType.CODE) {
                sentences.addAll(((TextComposite) paragraph).getChildren());
            }
        }

        return sentences;
    }


    private List<TextComponent> getSentences(TextComponent book) {
        List<TextComponent> sentences = new ArrayList<>();

        for (TextComponent paragraph : getParagraphs(book)) {
            List<TextComponent> paragraphSentences = ((TextComposite) paragraph).getChildren();
            sentences.addAll(paragraphSentences);
        }

        return sentences;
    }

    private List<TextComponent> getParagraphs(TextComponent book) {
        return ((TextComposite) book).getChildren();
    }

    private TextComponent getBook() {
        return dao.createBook();
    }
}
