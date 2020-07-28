package by.epamtc.degtyarovea.entity;

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
}
