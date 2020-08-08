package by.epamtc.degtyarovea.entity;

import java.util.Objects;

/**
 * Pattern 'Composite'
 */
public abstract class TextComponent {

    private TextComponentType type;

    public TextComponent(TextComponentType type) {
        this.type = type;
    }

    public abstract String text();

    public final TextComponentType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextComponent that = (TextComponent) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "TextComponent{" +
                "type=" + type +
                '}';
    }
}
