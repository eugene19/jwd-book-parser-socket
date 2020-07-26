package by.epamtc.degtyarovea.service;

import by.epamtc.degtyarovea.dao.BookDAO;
import by.epamtc.degtyarovea.dao.impl.FileBookDAO;
import by.epamtc.degtyarovea.entity.TextComponent;
import by.epamtc.degtyarovea.entity.TextComposite;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BookService {

    BookDAO dao = new FileBookDAO();

    public String printEachSentenceInNewLine() {
        StringBuilder text = new StringBuilder();
        TextComponent book = dao.createBook();

        List<TextComponent> paragraphs = ((TextComposite) book).getChildren();
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = ((TextComposite) paragraph).getChildren();
            for (TextComponent sentence : sentences) {
                text.append(sentence.text());
            }
        }

        return text.toString();
    }

    public String sentenceOrderByWordCount() {
        StringBuilder text = new StringBuilder();
        TextComponent book = dao.createBook();
        Map<Integer, Integer> sentenceAndCountPair = new LinkedHashMap<>();
        List<String> sents = new ArrayList();
        int sentenceCount = 0;

        List<TextComponent> paragraphs = ((TextComposite) book).getChildren();
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = ((TextComposite) paragraph).getChildren();

            for (TextComponent sentence : sentences) {
                int sentenceLength = sentence.text().length();
                sentenceAndCountPair.put(sentenceCount++, sentenceLength);
                sents.add(sentence.text());
            }

        }

        printSenteences(sents, sentenceAndCountPair);

        return text.toString();
    }

    private void printSenteences(List<String> sents, Map<Integer, Integer> sentenceAndCountPair) {
        while (sentenceAndCountPair.size() > 0) {
            int maxSen = maxWords(sentenceAndCountPair);
            String text1 = sents.get(maxSen);

            System.out.println(text1);
            sentenceAndCountPair.remove(maxSen);
        }
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
}
