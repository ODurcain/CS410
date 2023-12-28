package cs410.uno;

public class Card {

    public Card(Color color, Value value) {
        this.color = color; // represents the color of a card (e.g. RED, BLUE, ect.)
        this.value = value; // represents the value of a card (e.g. ONE, TWO, ect.)
    }

    private final Color color;
    private final Value value;

    // Ensures that a card can ONLY be one of these colors/type.
    public enum Color {
        RED,
        BLUE,
        GREEN,
        YELLOW;
    }

    // Ensures that a card can ONLY be one of these values.
    public enum BasicValue implements Value {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE;
    }

    public enum ActionValue implements Value {
        SKIP, REVERSE, DRAW_TWO;
    }

    public enum WildValue implements Value {
        WILD;
    }

    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }

    public boolean hasActionValue() {
        return value instanceof ActionValue;
    }

    public boolean hasWildValue() {
        return value instanceof WildValue;
    }

    // Displays the color and value of a card when called.
    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", value=" + value +
                '}';
    }

}
