package card;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestDeck {

    Deck newDeck;
    @Test
    public void testDeckSize() {
        newDeck = new Deck();
        assertEquals( newDeck.cards.size(), 84);
    }

    @Test
    public void testDeckContentMove() {
        newDeck = new Deck();
        assertTrue(newDeck.cards.contains(new CardMove(1,500)));
    }

    @Test
    public void testDeckContentTurn() {
        newDeck = new Deck();
        assertTrue(newDeck.cards.contains(new CardTurn(2,20)));
    }

}