package by.epamtc.degtyarovea.service;

import by.epamtc.degtyarovea.dao.BookDAO;
import by.epamtc.degtyarovea.dao.DAOFactory;
import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComposite;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BookService {

    private final static int PAR = 0;
    private final static int SENT = 0;

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
                int sentenceLength = sentence.text().length();
                sentenceAndCountPair.put(sentenceCount++, sentenceLength);
                sentencesList.add(sentence.text());
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
        TextComponent firstParagraph = paragraphs.get(PAR);
        List<TextComponent> sentences = ((TextComposite) firstParagraph).getChildren();
        TextComponent firstSentence = sentences.get(SENT);

        TextComposite wordsInFirstSentence = (TextComposite) firstSentence;

        for (TextComponent child : wordsInFirstSentence.getChildren()) {
            if (!anyOtherSentencesContainWord(child)) {
                return child.text();
            }
        }

        return "Such word is absent.";
    }

    private boolean anyOtherSentencesContainWord(TextComponent child) {
        TextComponent book = dao.createBook();
        List<TextComponent> paragraphs = ((TextComposite) book).getChildren();
        for (int i = 0; i < paragraphs.size(); i++) {
            List<TextComponent> sentences = ((TextComposite) paragraphs.get(i)).getChildren();
            for (int j = 0; j < sentences.size(); j++) {
                if (i == PAR && j == SENT) {
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
}
