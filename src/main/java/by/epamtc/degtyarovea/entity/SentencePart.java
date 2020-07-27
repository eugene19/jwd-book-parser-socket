package by.epamtc.degtyarovea.entity;

import java.util.Objects;

public class SentencePart extends TextComponent {

    private String sentencePart;

    public SentencePart(String word) {
        super(TextComponentType.PARAGRAPH);
        this.sentencePart = word;
    }

    public SentencePart(String word, TextComponentType type) {
        super(type);
        this.sentencePart = word;
    }

    public String text() {
        return String.valueOf(sentencePart);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SentencePart sentencePart1 = (SentencePart) o;
        return sentencePart.equals(sentencePart1.sentencePart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentencePart);
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "symbol=" + sentencePart +
                '}';
    }
}
