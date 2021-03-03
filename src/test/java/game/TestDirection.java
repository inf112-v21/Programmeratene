package game;

import org.junit.Test;
import static org.junit.Assert.*;

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
