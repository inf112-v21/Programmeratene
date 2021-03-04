package board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import player.IPlayer;

public interface IBoard {
    public TiledMap getTiledMap();

    public int getTileTextureSize();

    void drawPlayers();

    void clearPos(Vector2 pos);

    void addPlayer(IPlayer player);
}
