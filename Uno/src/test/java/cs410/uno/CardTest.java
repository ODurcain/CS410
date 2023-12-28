import cs410.uno.Card;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void getColor() {
        // Create a card with a specific color
        Card card = new Card(Card.Color.RED, Card.BasicValue.ONE);

        // Verify that the getColor() method returns the correct color
        assertEquals(Card.Color.RED, card.getColor());
    }

    @Test
    void getValue() {
        // Create a card with a specific value
        Card card = new Card(Card.Color.BLUE, Card.BasicValue.FIVE);

        // Verify that the getValue() method returns the correct value
        assertEquals(Card.BasicValue.FIVE, card.getValue());
    }

    @Test
    void hasActionValue() {
        // Create a card with an action value
        Card cardWithActionValue = new Card(Card.Color.GREEN, Card.ActionValue.SKIP);

        // Create a card with a basic value
        Card cardWithBasicValue = new Card(Card.Color.YELLOW, Card.BasicValue.THREE);

        // Verify that hasActionValue() returns true for a card with an action value
        assertTrue(cardWithActionValue.hasActionValue());

        // Verify that hasActionValue() returns false for a card with a basic value
        assertFalse(cardWithBasicValue.hasActionValue());
    }

    @Test
    void hasWildValue() {
        // Create a card with a wild value
        Card wildCard = new Card(Card.Color.RED, Card.WildValue.WILD);

        // Create a card with a basic value
        Card cardWithBasicValue = new Card(Card.Color.RED, Card.BasicValue.ONE);

        // Verify that hasWildValue() returns true for a card with a wild value
        assertTrue(wildCard.hasWildValue());

        // Verify that hasWildValue() returns false for a card with a basic value
        assertFalse(cardWithBasicValue.hasWildValue());
    }
}
