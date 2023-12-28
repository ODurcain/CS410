package cs410.uno;

import java.util.ArrayList;
import java.util.Collections;

public class Player {

    private ArrayList<Card> hand;

    public Player(Deck deck, int countInitialCards, ArrayList<Player> players) {
        this.hand = new ArrayList<>();
        initializeHand(deck, countInitialCards);
    }

    private void initializeHand(Deck deck, int countInitialCards) {
        for (int i = 0; i < countInitialCards; i++) {
            addToHand(deck.drawCard());
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addToHand(Card drawnCard) {
        hand.add(drawnCard);
    }

    public void playTurn(Card topDiscard, Deck discardPile, Deck mainDeck) {
        System.out.println("Player's Hand: " + hand);

        // Iterate through the player's hand to find a valid card to play
        for (Card card : hand) {
            if (isValidMove(card, topDiscard)) {
                // Play the card
                playCard(card, discardPile);
                System.out.println("Player played: " + card);

                return; // Exit the method after playing a card
            }
        }

        // If no valid card is found, draw a card from the deck
        System.out.println("Player couldn't play a card, drawing one from the deck.");
        addToHand(mainDeck.drawCard());
        if (mainDeck.isEmpty()) {
            System.out.println("deck empty");
            mainDeck.addAll(discardPile);
        }
    }

    public boolean isValidMove(Card card, Card topDiscard) {
        return card.getColor() == topDiscard.getColor() || card.getValue() == topDiscard.getValue();
    }

    public void playCard(Card card, Deck discardPile) {
        // Remove the played card from the player's hand and add it to the discard pile
        hand.remove(card);
        discardPile.push(card);
    }

    public boolean callUno() {
        // return hand.size() == 1; // change this. it's not allowing a new card to be picked up
        if (hand.size() == 1){
            return true;
        }
        return false;
    }

    public void drawCard(Deck mainDeck) {
        hand.add(mainDeck.drawCard());
    }
}