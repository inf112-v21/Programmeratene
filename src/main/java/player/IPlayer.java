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
     * Clear non-locked registers
     */
    void clearRegisters();

    /**
     * Moves the robot
     */
    void moveRobot(int steps);

    /**
     * Rotates the robot
     * @param turnSteps - amount of steps to turn (-1 = 90deg left, 1 = 90deg right, 2 = 180deg)
     * @return the updated orientation of the robot
     */
    Direction rotateRobot(int turnSteps);

    /**
     *
     * @return Vector2 position of the player
     */
    Vector2 getPos();

    String getPlayerName();

    public Direction getOrientation();

    public ArrayList<ICard> getRegisters();

    public void setRegisters(ArrayList<ICard> cardsToAdd);
}
