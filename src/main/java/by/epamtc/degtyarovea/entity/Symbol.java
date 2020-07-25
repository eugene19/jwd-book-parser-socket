package by.epamtc.degtyarovea.entity;

import java.util.Objects;

public class Symbol implements TextComponent {

    private char symbol;

    public Symbol(char symbol) {
        this.symbol = symbol;
    }

    public String text() {
        return String.valueOf(symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol1 = (Symbol) o;
        return symbol == symbol1.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "symbol=" + symbol +
                '}';
    }
}
