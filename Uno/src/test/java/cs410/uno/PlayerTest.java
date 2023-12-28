package cs410.uno;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getHand() {
        Deck mainDeck = new Deck(5, 5, 5);
        mainDeck.shuffle();

        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player(mainDeck, 5, players);

        assertEquals(5, player.getHand().size());
    }

    @Test
    void addToHand() {
        Deck mainDeck = new Deck(5, 5, 5);
        mainDeck.shuffle();

        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player(mainDeck, 5, players);

        int initialHandSize = player.getHand().size();
        Card newCard = new Card(Card.Color.RED, Card.BasicValue.ONE);
        player.addToHand(newCard);

        assertEquals(initialHandSize + 1, player.getHand().size());
        assertTrue(player.getHand().contains(newCard));
    }

/*    @Test
    void playTurn() {
        // Write test cases for playTurn method, considering valid moves and drawing scenarios.
    }*/

    @Test
    void isValidMove() {
        Deck mainDeck = new Deck(5, 5, 5);
        mainDeck.shuffle();

        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player(mainDeck, 5, players);

        Card topDiscard = new Card(Card.Color.RED, Card.BasicValue.FIVE);
        Card matchingCard = new Card(Card.Color.RED, Card.BasicValue.SEVEN);
        Card nonMatchingCard = new Card(Card.Color.BLUE, Card.BasicValue.SEVEN);

        assertTrue(player.isValidMove(matchingCard, topDiscard));
        assertFalse(player.isValidMove(nonMatchingCard, topDiscard));
    }

/*    @Test
    void playCard() {
        Deck mainDeck = new Deck();
        mainDeck.shuffle();

        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player(mainDeck, 5, players);

        Card cardToPlay = new Card(Card.Color.GREEN, Card.BasicValue.THREE);
        player.addToHand(cardToPlay);

        Deck discardPile = new Deck();
        player.playCard(cardToPlay, discardPile);

        assertEquals(0, player.getHand().size());
        assertTrue(discardPile.topCard(cardToPlay));
    }*/

    @Test
    void callUno() {
        Deck mainDeck = new Deck(5, 5, 5);
        mainDeck.shuffle();

        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player(mainDeck, 2, players);

        assertFalse(player.callUno());

        player.getHand().remove(0);

        assertTrue(player.callUno());
    }
}