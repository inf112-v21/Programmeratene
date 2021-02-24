package board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

public interface IBoard {
    public TiledMap getTiledMap();

    public int getTileTextureSize();

    void updatePlayerPos();

    void clearPos(Vector2 pos);
}
