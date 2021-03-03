package card;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestCardMove {

    CardMove cardMove;
    @Test
    public void testHasToString() {
        cardMove = new CardMove();
        assertNotNull(cardMove.toString());
    }

    @Test
    public void testHasPriority() {
        cardMove = new CardMove();
        assertNotNull(cardMove.getPriority());
    }

    @Test
    public void testHasHash() {
        cardMove = new CardMove();
        assertNotNull(cardMove.hashCode());
    }

}
