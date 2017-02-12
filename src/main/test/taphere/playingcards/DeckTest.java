package test.taphere.playingcards;

import com.taphere.playingcards.Card;
import com.taphere.playingcards.Deck;
import com.taphere.playingcards.Suit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by laubermd on 2/12/17.
 */
public class DeckTest {
    private Deck deck;
    private Card[] cards;

    @Before
    public void setUp() {
        try {
            deck = new Deck();
        } catch (Exception e) {
            fail("unable to setup Deck: " + e.getMessage());
        }

        cards = deck.getCards();
    }

    /**
     * Asserts that each card from the original deck only exists exactly once in the new deck
     * @param newCards
     */
    public void validateCards(Card[] newCards) {
        for (int ii = 0; ii < newCards.length; ii++) {
            int matchedCards = 0;
            for (int jj = 0; jj < cards.length; jj++) {
                if (newCards[ii].equals(cards[jj])) {
                    matchedCards++;
                }
            }

            assertTrue("Deck contains " + matchedCards + " " + newCards[ii].toString(), matchedCards == 1);
        }
    }

    /**
     * Asserts that a shuffled deck contains the same cards as the original deck, but in a different order
     * Note: it is improbable but possible for this test to produce a false failure if the shuffled deck
     *       is identical to the original deck
     */
    public void testShuffle() {
        Deck shuffledDeck = deck;
        shuffledDeck.shuffle();
        Card[] shuffledCards = shuffledDeck.getCards();
        validateCards(shuffledCards);

        for (int ii = 0; ii < shuffledCards.length; ii++) {
            int matchedCards = 0;

            if (shuffledCards[ii].equals(cards[ii])) {
                matchedCards++;
            }

            assertTrue("The order of cards in the shuffled deck did not change." , matchedCards < cards.length);
        }
    }

    /**
     * Asserts that each card in a deck can be drawn, and no duplicate cards are drawn from a single deck
     */
    @Test
    public void testDrawAllCards() {
        Card[] drawnCards = new Card[cards.length];

        try {
            for (int ii = 0; ii < cards.length; ii++) {
                drawnCards[ii] = deck.drawOneCard();
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }

        validateCards(drawnCards);
    }

    /**
     * Asserts that an error is thrown if more cards are drawn than exist in the deck
     */
    @Test
    public void testDrawTooManyCards() {
        try {
            for (int ii = 0; ii < cards.length + 1; ii++) {
                deck.drawOneCard();
            }

            fail("More cards were drawn than exist in the deck.");
        } catch (Exception e) {
            // negative test passed
        }
    }

    /**
     * Asserts that cards cannot be created with invalid values
     */
    @Test
    public void testInvalidCards() {
        Card badCard;

        try {
            badCard = new Card(Suit.Spade, 0);
            fail("invalid card with value of 0 was created");
        } catch (Exception e) {
            // negative test passed
        }

        try {
            badCard = new Card(Suit.Spade, 14);
            fail("invalid card with value of 14 was created");
        } catch (Exception e) {
            // negative test passed
        }
    }
}