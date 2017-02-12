package com.taphere.playingcards;

import java.util.Random;

/**
 * Created by laubermd on 2/12/17.
 */
public class Deck {
    private Card[] cards;
    private int topCard;

    /**
     * Constructs a new Deck object with 52 cards, 13 of each suit
     */
    public Deck() throws Exception{
        cards = new Card[52];
        topCard = 0;
        int position = 0;

        for (int value = 1; value < 14; value++) {
            cards[position++] = new Card(Suit.Club, value);
            cards[position++] = new Card(Suit.Spade, value);
            cards[position++] = new Card(Suit.Diamond, value);
            cards[position++] = new Card(Suit.Heart, value);
        }
    }

    /**
     * Shuffles the deck by swapping each card with another random card, and sets the position of the top card to 0
     * NOTE: It is improbable, but possible, that the shuffled order is identical to the pre-shuffled order
     */
    public void shuffle() {
        Random generator = new Random();
        topCard = 0;

        for (int position = 0; position < cards.length; position++) {
            int newPosition = generator.nextInt(cards.length);
            Card card = cards[position];
            cards[position] = cards[newPosition];
            cards[newPosition] = card;
        }
    }

    /**
     * Draws one card, and increments the location of the top card
     * @return the top card in the deck
     * @throws Exception - When all cards are drawn, no more cards can be drawn from the deck
     */
    public Card drawOneCard() throws Exception {
        if (topCard <= cards.length) {
            Card card = cards[topCard];
            topCard++;

            return card;
        } else {
            throw new Exception("All cards have been drawn.  The deck must be shuffled to continue drawing.");
        }
    }

    /**
     * retrieves the array containing all cards from the deck in their current order
     * @return all cards in the deck, including any drawn cards
     */
    public Card[] getCards() {
        return cards;
    }
}
