package game;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestDirection {

    Direction orientation;
    @Test
    public void testReverse() {
        orientation = Direction.NORTH;
        assertEquals(orientation.getReverse(), Direction.SOUTH);
    }

    @Test
    public void testLeft() {
        orientation = Direction.NORTH;
        assertEquals(orientation.getLeft(), Direction.WEST);
    }

    @Test
    public void testRight() {
        orientation = Direction.NORTH;
        assertEquals(orientation.getRight(), Direction.EAST);
    }
}
