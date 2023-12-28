package cs410.uno;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void deck() {
        // Create a GameState and check if deck() initializes discardPile
        GameState gameState = new GameState(new Deck(), new Deck(), new ArrayList<>());
        gameState.Deck();
        assertNotNull(gameState.discardPile);
    }

    @Test
    void startGame() {
        // Create a GameState with specific parameters and check if it initializes correctly
        GameState gameState = GameState.startGame(3, 7, 10, 3, 4);

        // Verify that the mainDeck, discardPile, and players are not null
        assertNotNull(gameState.mainDeck);
        assertNotNull(gameState.discardPile);
        assertNotNull(gameState.players);

        // Verify that the initial card is pushed to the discardPile
        assertNotNull(gameState.discardPile.topCard());
    }

    @Test
    void isGameOver() {
        // Create a GameState with one player and an empty hand
        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player(new Deck(), 0, players);
        players.add(player);

        GameState gameState = new GameState(new Deck(), new Deck(), players);

        // Verify that isGameOver() returns true when the player's hand is empty
        assertTrue(gameState.isGameOver());

        // Add a card to the player's hand and verify that isGameOver() returns false
        player.addToHand(new Card(Card.Color.RED, Card.BasicValue.ONE));
        assertFalse(gameState.isGameOver());
    }

    @Test
    void runOneTurn() {
        // Create a GameState with a player and a card in the discardPile
        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player(new Deck(5, 5, 5), 1, players);
        players.add(player);

        Deck mainDeck = new Deck(5, 5, 5);
        mainDeck.push(new Card(Card.Color.RED, Card.BasicValue.TWO));

        Deck discardPile = new Deck();
        discardPile.push(new Card(Card.Color.RED, Card.BasicValue.ONE));

        GameState gameState = new GameState(mainDeck, discardPile, players);

        // Mock the System.out.println output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Perform runOneTurn and verify that the player's turn is executed
        gameState.runOneTurn();
        assertFalse(outputStream.toString().contains("Player played:"));

        // Verify that the currentPlayerIndex is updated
        assertEquals(0, gameState.currentPlayerIndex);
    }
}
