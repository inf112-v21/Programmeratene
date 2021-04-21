package board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import game.Direction;
import player.IPlayer;

import java.util.ArrayList;
import java.util.HashMap;

public interface IBoard {

    /**
     * Put player-cells into the Player-layer using their positions in the player-list
     */
    void drawPlayers();

    /**
     * Clears the TiledMapLayer containing Player-cells.
     * Usually in preparation for drawing them in new positions.
     */
    void clearAllPlayerPos();

    /**
     * Add a player to the local playerlist-variable
     * @param player - The player to be added
     */
    void addPlayer(IPlayer player);

    /**
     * Get which type of element is in a given position
     * @param pos - The position to check
     * @return A string describing the type of element
     */
    String getElementInPos(Vector2 pos);

    ArrayList<Vector2> getSpawns();

    /*
        SETTERS
     */
    void setPlayers(ArrayList<IPlayer> players);



    /*
        GETTERS
     */
    TiledMap getTiledMap();

    int getTileTextureSize();

    HashMap<String, TiledMapTileLayer> getLayers();

    int getBOARD_WIDTH();

    int getBOARD_HEIGHT();

    boolean canMove(Vector2 pos, Direction orientation);

    ArrayList<Integer> getFlags();
}
