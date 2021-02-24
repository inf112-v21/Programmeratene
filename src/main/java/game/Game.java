package game;

import board.Board;
import board.IBoard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import player.HumanPlayer;
import player.IPlayer;

import java.util.ArrayList;
import java.util.HashMap;

public class Game extends InputAdapter {
    ArrayList<IPlayer> players;
    IBoard board;

    public Game(){
        players = new ArrayList<>();
        players.add(new HumanPlayer("HumanPlayer1"));
        players.add(new HumanPlayer("HumanPlayer2"));

        board = new Board(players);

        // Set up input processor
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyUp(int keycode){
        board.clearPos(players.get(0).getPos());

        if (keycode == Input.Keys.UP || keycode == Input.Keys.W)
            players.get(0).moveRobot(1);
        else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S)
            players.get(0).rotateRobot(2);
        else if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A)
            players.get(0).rotateRobot(-1);
        else if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)
            players.get(0).rotateRobot(1);

        board.updatePlayerPos();

        return true;
    }

    public IBoard getBoard() {
        return board;
    }
}
