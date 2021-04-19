package board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import player.IPlayer;

import java.util.ArrayList;

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


     int getBOARD_WIDTH();
     int getBOARD_HEIGHT();

}
