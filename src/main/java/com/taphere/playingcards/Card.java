package com.taphere.playingcards;

/**
 * Created by laubermd on 2/12/17.
 */
public class Card {

    private Suit suit;
    private String face;
    private int value;

    /**
     * Constructs a new card with a given suit and value
     * The card's face is assigned based on the provided value
     * @param suit the suit of the card (Heart, Spade, Diamond, Club)
     * @param value 1-13, representing the rank of each card
     */
    public Card(Suit suit, int value) throws Exception {
        this.suit = suit;
        this.value = value;

        switch (value) {
            case 1:
                face = "Ace";
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                face = "" + value;
                break;
            case 11:
                face = "Jack";
                break;
            case 12:
                face = "Queen";
                break;
            case 13:
                face = "King";
                break;
            default:
                throw new Exception ("Invalid value (" + value + ") provided for card.");
        }
    }

    /**
     * String representation of the Card, noting the card's face and suit
     * @return
     */
    public String toString() {
        return face + " of " + suit + "s";
    }

    /**
     * Equality comparator, based on the suit, face, and value of the card
     * @param card - the card to be compared with the current card
     * @return true if the cards match, false if they do not
     */
    public boolean equals(Card card) {
        return suit != null && suit.equals(card.suit) && face != null && face.equals(card.face) && value == card.value;
    }
}
