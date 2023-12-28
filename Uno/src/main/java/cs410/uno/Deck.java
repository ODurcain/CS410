package cs410.uno;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private final Stack<Card> deck;

    public Deck(int countDigitCardsPerColor, int countSpecialCardsPerColor, int countWildCards) {
        deck = new Stack<>();

        populateDeck(countDigitCardsPerColor, countSpecialCardsPerColor, countWildCards);
        shuffle();
    }

    public Deck(){
        deck = new Stack<>();
    }

    public void populateDeck(int countDigitCardsPerColor, int countSpecialCardsPerColor, int countWildCards) {
        for (Card.Color color : Card.Color.values()) {
            // Adding digit cards to the stack that have the same amount as countDigitCardsPerColor
            for (Card.BasicValue value : Card.BasicValue.values()) {
                deck.push(new Card(color, value));
            }

            // adding special cards (e.g. Skip, Reverse, Draw Two) to the stack
            for (int i = 0; i < countSpecialCardsPerColor; i++) {
                for (Card.ActionValue value : Card.ActionValue.values()) {
                    deck.push(new Card(color, value));
                }
            }

            for (int i = 0; i < countWildCards; i++) {
                for (Card.WildValue value : Card.WildValue.values()) {
                    deck.push(new Card(color, value));
                }
            }
        }
    }

    public Card drawCard(){
        if (!deck.isEmpty()) {
            return deck.pop();
        } else {
            System.out.println("Deck is empty");
            // Handle the case where the deck is empty, maybe reshuffle discard pile
            return null;
        }
    }
    public void shuffle() {
        Collections.shuffle(deck);
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public void addAll(Deck discardPile) {
        shuffle();
        deck.addAll(discardPile.getDeck());
        discardPile.clear();
    }

    public void clear() {
        deck.removeAllElements();
    }

    public void push(Card topDiscard) {
        deck.add(topDiscard);
    }

    public Card topCard() {
        return deck.peek();
    }

    public Stack<Card> getDeck() {
        return deck;
    }
}
