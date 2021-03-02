package player;

import game.Direction;
import card.*;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

public abstract class AbstractPlayer implements IPlayer {
    String playerName;
    ArrayList<ICard> deck; //Tildelte kort
    ArrayList<ICard> registers; //Valgte kort
    ArrayList<ICard> lockedRegisters; //Kort som er låst pga damage
    int damageTokens;
    int lifeTokens;
    Direction orientation;
    Vector2 playerPos;

    /**
     * Adds damage tokens to the player
     *
     * @param damageTaken - amount of damage tokens to add
     */
    @Override
    public void applyDamage(int damageTaken) {
        //TODO
    }

    /**
     * Removes damage tokens from the player
     *
     * @param repairAmount - amount of damage tokens to remove
     */
    @Override
    public void repair(int repairAmount) {
        //TODO
    }

    /**
     *
     * @param steps - number of steps the robot moves -> -1, 1, 2, 3
     */
    public void moveRobot(int steps) {
        if(orientation == Direction.NORTH) {
            playerPos.add(0,steps);
        }
        else if(orientation == Direction.SOUTH) {
            playerPos.add(0,-steps);
        }
        else if(orientation == Direction.EAST) {
            playerPos.add(steps,0);
        }
        else if(orientation == Direction.WEST) {
            playerPos.add(-steps,0);
        }
    }

    /**
     *
     * @param turnSteps - amount of steps the robot should rotate
     * @return the new direction of the robot
     */
    public Direction rotation(int turnSteps) {
        if(turnSteps == -1) {
            orientation = orientation.getLeft();
        }
        else if(turnSteps == 1) {
            orientation = orientation.getRight();
        }
        if(turnSteps == 2) {
            orientation = orientation.getReverse();
        }
        return orientation;
    }

    /**
     * if damage == 0 then deal 9 cards else deal 9 - damage tokens
     *
     */
    void deliverCardsToPlayer() {
        for(int i = 0; i == 9 - damageTokens; i++) {
            deck.add(card.Deck.cards.get(0));
            card.Deck.cards.remove(0);
        }
    }

    /**
     * Clear non-locked registers
     */
    @Override
    public void clearRegisters() {
        //TODO
    }
}
