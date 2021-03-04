package game;

import board.Board;
import board.IBoard;
import player.IPlayer;

import java.util.ArrayList;

public class Game  {
    ArrayList<IPlayer> players;
    IBoard board;

    public Game(){
        players = new ArrayList<>();
        board = new Board();
    }

    public IBoard getBoard() {
        return board;
    }
}
