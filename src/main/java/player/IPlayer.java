package player;

import card.ICard;
import com.badlogic.gdx.math.Vector2;
import game.Direction;

import java.util.ArrayList;

public interface IPlayer {
    /**
     * Adds damage tokens to the player
     * @param damageTaken - amount of damage tokens to add
     */
    void applyDamage(int damageTaken);

    /**
     * Removes damage tokens from the player
     * @param repairAmount - amount of damage tokens to remove
     */
    void repair(int repairAmount);

    /**
     * Return players alive status (bool)
     */
    boolean getAlive();

    /**
     * Moves the robot
     */
    void moveRobot(int steps);

    /**
     * Kills robot (sets lifetokens to 0 and moves to pos (-1,-1))
     */
    void kill();

    /**
     * Rotates the robot
     * @param turnSteps - amount of steps to turn (-1 = 90deg left, 1 = 90deg right, 2 = 180deg)
     * @return the updated orientation of the robot
     */
    Direction rotateRobot(int turnSteps);

    /*
        SETTERS
     */
    void setPos(Vector2 pos);

    void setOrientation(Direction dir);

    void setRegisters(ArrayList<ICard> cardsToAdd);

    /*
        GETTERS
     */
    Vector2 getPos();

    String getPlayerName();

    Direction getOrientation();

    ArrayList<ICard> getRegisters();
}
