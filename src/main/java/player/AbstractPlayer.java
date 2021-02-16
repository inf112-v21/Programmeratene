package player;

import card.ICard;
import game.Direction;

import java.util.ArrayList;

public abstract class AbstractPlayer {
    String playerName;
    ArrayList<ICard> deck; //Tildelte kort
    ArrayList<ICard> registers; //Valgte kort
    ArrayList<ICard> lockedRegisters; //Kort som er låst pga damage
    int damageTokens;
    int lifeTokens;
    Direction facingDir;
}
