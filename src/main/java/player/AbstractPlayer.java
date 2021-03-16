package player;

import game.Direction;
import card.*;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer implements IPlayer {
    String playerName;
    ArrayList<ICard> deck; //Tildelte kort
    ArrayList<ICard> registers; //Valgte kort
    ArrayList<ICard> lockedRegisters; //Kort som er låst pga damage
    int damageTokens;
    int lifeTokens;
    Direction orientation;
    Vector2 playerPos;

    AbstractPlayer(){

    }

    AbstractPlayer(String playerName){
        this.playerName = playerName;
        this.deck = new ArrayList<>();
        this.registers = new ArrayList<>();
        this.lockedRegisters = new ArrayList<>();
        this.damageTokens = 0;
        this.lifeTokens = 3;
        this.orientation = Direction.NORTH;
        this.playerPos = new Vector2(0,0);
    }

    @Override
    public void applyDamage(int damageTaken) {
        //TODO
    }

    @Override
    public void repair(int repairAmount) {
        //TODO
    }

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

    public Direction rotateRobot(int turnSteps) {
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

    public void setRegisters(ArrayList<ICard> cardsToAdd){
        this.registers = cardsToAdd;
    }

    @Override
    public ArrayList<ICard> getRegisters() {
        return registers;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void clearRegisters() {
        //TODO
    }

    @Override
    public Vector2 getPos() {
        return playerPos;
    }

    @Override
    public Direction getOrientation() {
        return orientation;
    }
}
