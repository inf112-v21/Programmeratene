package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell; //virker redundant, men var nødvendig for å unngå å skrive 'TiledMapTileLayer.Cell' på alle implementasjoner av Cell
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class HelloWorld implements ApplicationListener  {
    private SpriteBatch batch;
    private BitmapFont font;

    private int tileTextureSize = 300; //Size of tile-textures in pixels (From Tiled-editor)

    private TiledMap tiledMap;
    private HashMap<String, TiledMapTileLayer> layers;
    private String[] layerNames = {"Board","Hole","Flag","Player"};
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private Cell playerWonCell;
    private Cell playerDiedCell;
    private Cell playerCell;
    private Vector2 playerPos;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        // Set up TiledMap with layers
        tiledMap = new TmxMapLoader().load("example.tmx");
        layers = new HashMap<>();
        for (String layerName : layerNames)
            layers.put(layerName, (TiledMapTileLayer) tiledMap.getLayers().get(layerName));

        // Set up Camera and Renderer
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 5, 5);
        camera.update();
        renderer = new OrthogonalTiledMapRenderer(tiledMap, (1f/tileTextureSize));
        renderer.setView(camera);

        // Set up Player-cell
        TextureRegion[][] playerTextures = TextureRegion.split(new Texture("player.png"), tileTextureSize, tileTextureSize);
        playerWonCell = new Cell();
        playerDiedCell = new Cell();
        playerCell = new Cell();
        playerWonCell.setTile(new StaticTiledMapTile(playerTextures[0][2]));
        playerDiedCell.setTile(new StaticTiledMapTile(playerTextures[0][1]));
        playerCell.setTile(new StaticTiledMapTile(playerTextures[0][0]));
        playerPos = new Vector2(0, 0);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        layers.get("Player").setCell((int) playerPos.x, (int) playerPos.y, playerCell);

        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
