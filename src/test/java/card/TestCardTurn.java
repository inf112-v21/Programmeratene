package card;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestCardTurn {

    CardTurn cardTurn;
    @Test
    public void testHasToString() {
        cardTurn = new CardTurn();
        assertNotNull(cardTurn.toString());
    }

    @Test
    public void testHasPriority() {
        cardTurn = new CardTurn();
        assertNotNull(cardTurn.getPriority());
    }

    @Test
    public void testHasHash() {
        cardTurn = new CardTurn();
        assertNotNull(cardTurn.hashCode());
    }

}
