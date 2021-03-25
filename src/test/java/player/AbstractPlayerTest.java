package player;

import static org.junit.Assert.*;

import game.Direction;
import org.junit.Test;


public class AbstractPlayerTest {

    @Test
    public void testApplyDamage() {
        ComputerPlayer p = new ComputerPlayer("p1");
        p.applyDamage(1);

        assertEquals(1, p.damageTokens);
    }

    @Test
    public void testApplyDamageOverflow() {
        ComputerPlayer p = new ComputerPlayer("p1");
        p.applyDamage(9);

        assertEquals(0, p.damageTokens);
        assertEquals(2, p.lifeTokens);
    }

    @Test
    public void testRepair() {
        ComputerPlayer p = new ComputerPlayer("p1");
        p.applyDamage(2);
        p.repair(1);

        assertEquals(1, p.damageTokens);
    }

    @Test
    public void testRepairOverflow() {
        ComputerPlayer p = new ComputerPlayer("p1");
        p.applyDamage(2);
        p.repair(3);

        assertEquals(0, p.damageTokens);
    }

    @Test
    public void testMovementForward() {
        ComputerPlayer p = new ComputerPlayer("p1");
        p.moveRobot(1);

        //Should be facing NORTH at pos (0,1)
        assertEquals(Direction.NORTH, p.getOrientation());
        assertEquals(0, (int) p.getPos().x);
        assertEquals(1, (int) p.getPos().y);
    }

    @Test
    public void testMovementTurnRight() {
        ComputerPlayer p = new ComputerPlayer("p1");
        p.moveRobot(1);
        p.rotateRobot(1);
        p.moveRobot(1);

        //Should be facing EAST at pos (1,1)
        assertEquals(Direction.EAST, p.getOrientation());
        assertEquals(1, (int) p.getPos().x);
        assertEquals(1, (int) p.getPos().y);
    }

    @Test
    public void testMovementTurnLeft() {
        ComputerPlayer p = new ComputerPlayer("p1");
        p.moveRobot(1);
        p.rotateRobot(-1);
        p.moveRobot(1);

        //Should be facing WEST at pos (-1,1)
        assertEquals(Direction.WEST, p.getOrientation());
        assertEquals(-1, (int) p.getPos().x);
        assertEquals(1, (int) p.getPos().y);
    }

    @Test
    public void testMovementAll() {
        ComputerPlayer p = new ComputerPlayer("p1");
        p.moveRobot(1);
        p.rotateRobot(1);
        p.moveRobot(3);
        p.rotateRobot(-1);
        p.moveRobot(2);
        p.rotateRobot(2);
        p.moveRobot(-1);

        //Should be facing SOUTH at pos (3,4)
        assertEquals(Direction.SOUTH, p.getOrientation());
        assertEquals(3, (int) p.getPos().x);
        assertEquals(4, (int) p.getPos().y);
    }
}