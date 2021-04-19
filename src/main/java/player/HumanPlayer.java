package player;

import com.badlogic.gdx.math.Vector2;

public class HumanPlayer extends AbstractPlayer{

    public HumanPlayer(){
        super();
    }

    public HumanPlayer(String playerName) {
        super(playerName);
    }

    public HumanPlayer(String playerName, Vector2 spawnPos){
        super(playerName, spawnPos);
    }
}
