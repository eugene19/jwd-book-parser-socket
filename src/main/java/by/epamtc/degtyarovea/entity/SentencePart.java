package by.epamtc.degtyarovea.entity;

import java.util.Objects;

public class SentencePart implements TextComponent {

    private String sentencePart;
    private TextComponentType type;

    public SentencePart(String word) {
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
