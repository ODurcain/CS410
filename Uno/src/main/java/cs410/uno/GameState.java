package cs410.uno;

import java.util.ArrayList;

/* A card is one of red, yellow, green, blue,
   0-9, skip, reverse, draw 2, wild card.
   A deck is 56 total cards.
   A player is someone in the game.
   A hand is initialized as 7 random cards given to each player.
   There is a discard pile initialized as an empty deck

   Example: in a 2 player game there is 2 hands totaling 14 cards
             the deck is then at 42 cards randomized

   Account for wild card on top of the discard pile. shuffle discard pile
   at main deck empty.
   isGameOver checks if a players hand is empty.
   runOneTurn goes through every player and checks current deck status.
 */

public class GameState {

    public Deck mainDeck;
    public Deck discardPile;
    public ArrayList<Player> players;
    public int currentPlayerIndex;

    private boolean isReversed;

    // Constructor for starting the game
    public GameState(Deck mainDeck, Deck discardPile, ArrayList<Player> players) {
        this.mainDeck = mainDeck;
        this.discardPile = discardPile;
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    public void Deck(){
        discardPile = new Deck();
    }

    /* After the startGame method ends, the game state should represent the
     * situation immediately before the first player takes their first turn.
     * That is, the players should be arranged, their initial hands have been dealt,
     * and the discard pile and draw pile have been created.
     */
    public static GameState startGame(int countPlayers,
                                      int countInitialCardsPerPlayer,
                                      int countDigitCardsPerColor,
                                      int countSpecialCardsPerColor,
                                      int countWildCards) {

        // Initialize deck and discard pile
        Deck mainDeck = new Deck(countDigitCardsPerColor, countSpecialCardsPerColor, countWildCards);
        Deck discardPile = new Deck();

        // Initialize players and deal initial cards
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < countPlayers; i++) {
            Player player = new Player(mainDeck, countInitialCardsPerPlayer, players);
            players.add(player);
        }

        // Place the first card on the discard pile
        Card initialCard = mainDeck.drawCard();
        do {
            initialCard = mainDeck.drawCard();
        } while (initialCard.hasActionValue() || initialCard.hasWildValue());
        discardPile.push(initialCard);

        // Return the initialized game state
        return new GameState(mainDeck, discardPile, players);
    }

    /* Indicates whether the game is over.
     */
    boolean isGameOver() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /* The current player takes their turn, and if they play a special card
     * the corresponding effects are performed. When the method returns,
     * the next player is ready to take their turn.
     * If the game is already over, this method has no effect.
     */
    public void runOneTurn() {
        if (!isGameOver()){
            Player currentPlayer = players.get(currentPlayerIndex);
            Card topDiscard = discardPile.topCard();

            // Display current game state or any necessary information to the player
            System.out.println("Current Player: " + (currentPlayerIndex + 1));
            System.out.println("Top Discard: " + topDiscard);

            // Player takes their turn
            currentPlayer.playTurn(topDiscard, discardPile, mainDeck);

            // Check if the player won after their turn
            if (currentPlayer.getHand().isEmpty()) {
                // Game over, player won
                System.out.println("Player " + (currentPlayerIndex + 1) + " wins!");
            }

            // Check if the player has Uno after their turn
            if (currentPlayer.callUno()) {
                // Current player called Uno
                System.out.println("Player: " + (currentPlayerIndex + 1) + " Uno!");
            }

            // Check if the player played a special card and handle it
            if (topDiscard.hasActionValue() && topDiscard.getValue() == Card.ActionValue.SKIP) {
                handleSkip();
            } else if (topDiscard.hasActionValue() && topDiscard.getValue() == Card.ActionValue.REVERSE) {
                handleReverse();
            } else if (topDiscard.hasActionValue() && topDiscard.getValue() == Card.ActionValue.DRAW_TWO) {
                handleDrawTwo();
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }

    private int calculateNextPlayerIndex() {
        if (isReversed) {
            return (currentPlayerIndex - 1 + players.size()) % players.size();
        } else {
            return (currentPlayerIndex + 1) % players.size();
        }
    }

    public void handleSkip() {
        // Calculate the index of the player to be skipped
        int skippedPlayerIndex = calculateNextPlayerIndex();

        // Skip the next player in rotation
        currentPlayerIndex = calculateNextPlayerIndex();
        System.out.println("Game direction skipped. Player " + (skippedPlayerIndex + 1) + " skipped. Next player: " + (currentPlayerIndex + 2));
    }


    public void handleReverse() {
        isReversed = !isReversed;
        System.out.println("Game direction reversed.");
        currentPlayerIndex = calculateNextPlayerIndex();
    }

    public void handleDrawTwo() {
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.drawCard(mainDeck);
        currentPlayer.drawCard(mainDeck);
        System.out.println("Player: " + (currentPlayerIndex + 1) + " drew 2 cards");
    }

    public static void main(String[] args) {
        // Example usage
        GameState gameState = startGame(4, 7, 10, 3, 4);

        // Run a few turns as an example
        for (int i = 0; i < 30; i++) {
            gameState.runOneTurn();
        }
    }
}

interface Value {
    // now both BasicValues and SpecialValues can be treated as a Value
}

