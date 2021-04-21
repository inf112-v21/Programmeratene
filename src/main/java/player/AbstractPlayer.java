package player;

import game.Direction;
import card.*;
import com.badlogic.gdx.math.Vector2;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class AbstractPlayer implements IPlayer {
    String playerName;
    ArrayList<ICard> deck; //Tildelte kort
    ArrayList<ICard> registers; //Valgte kort
    ArrayList<ICard> lockedRegisters; //Kort som er låst pga damage
    ArrayList<Integer> visitedFlags; // liste med besøkte flagg
    int damageTokens;
    int lifeTokens;
    Direction orientation;
    Vector2 playerPos;
    Vector2 spawnPos;

    AbstractPlayer(){

    }

    AbstractPlayer(String playerName){
        this(playerName, new Vector2(0,0));
    }

    AbstractPlayer(String playerName, Vector2 spawnPos){
        this.playerName = playerName;
        this.deck = new ArrayList<>();
        this.registers = new ArrayList<>();
        this.lockedRegisters = new ArrayList<>();
        this.damageTokens = 0;
        this.lifeTokens = 3;
        this.orientation = Direction.NORTH;
        this.playerPos = new Vector2(spawnPos);
        this.spawnPos = new Vector2(spawnPos);
    }


    @Override
    public void applyDamage(int damageTaken) {
        damageTokens = damageTokens + damageTaken;
        if(damageTokens >= 9) {
            lifeTokens--;
            damageTokens = 0;
            setPos(new Vector2(spawnPos));
            if(lifeTokens <= 0) {
                kill();
            }
        }
    }

    @Override
    public void repair(int repairAmount) {
        damageTokens = damageTokens - repairAmount;
        if(damageTokens < 0) {
            damageTokens = 0;
        }
    }

    @Override
    public boolean getAlive() {
        return lifeTokens>0;
    }

    public void kill() {
        lifeTokens = 0;
        setPos(new Vector2(-1,-1));
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

    public ArrayList<Integer> getVisited() {
        return visitedFlags;
    }

    public void addVisitedFlag(int x) {
        visitedFlags.add(x);
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
    public void setPos(Vector2 pos) {
        this.playerPos = new Vector2(pos);
    }

    @Override
    public Vector2 getPos() {
        return playerPos;
    }

    @Override
    public void setOrientation(Direction dir) {
        this.orientation = dir;
    }

    @Override
    public Direction getOrientation() {
        return orientation;
    }
}
