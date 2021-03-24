package board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import player.IPlayer;

import java.util.ArrayList;

public interface IBoard {
    TiledMap getTiledMap();

    int getTileTextureSize();

    void drawPlayers();

    void clearPos(Vector2 pos);

    void clearAllPlayerPos();

    void addPlayer(IPlayer player);

    void setPlayers(ArrayList<IPlayer> players);

     String getElementInPos(Vector2 pos);
}
