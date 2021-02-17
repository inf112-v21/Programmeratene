package player;

import card.ICard;
import game.Direction;

import java.util.ArrayList;

public abstract class AbstractPlayer implements IPlayer {
    String playerName;
    ArrayList<ICard> deck; //Tildelte kort
    ArrayList<ICard> registers; //Valgte kort
    ArrayList<ICard> lockedRegisters; //Kort som er låst pga damage
    int damageTokens;
    int lifeTokens;
    Direction orientation;

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
     * Clear non-locked registers
     */
    @Override
    public void clearRegisters() {
        //TODO
    }
}
