package board;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import player.IPlayer;

import java.util.ArrayList;
import java.util.HashMap;

public class Board implements IBoard {
    private static String[] layerNames = {"Board","Hole","Flag","Player"};
    private static String[] robotTextureNames = {"RobotRed", "RobotBlue", "RobotGreen", "RobotYellow", "RobotCyan", "RobotOrange", "RobotPink", "RobotPurple"};
    private int BOARD_WIDTH = 5;
    private int BOARD_HEIGHT = 5;

    private int tileTextureSize = 300; //Size of tile-textures in pixels (From Tiled-editor)
    private TiledMap tiledMap;
    private HashMap<String, TiledMapTileLayer> layers;
    private ArrayList<Cell> playerCells;
    private ArrayList<IPlayer> players;

    public Board(ArrayList<IPlayer> players){
        // Set up TiledMap with layers
        tiledMap = new TmxMapLoader().load("example.tmx");
        layers = new HashMap<>();
        for (String layerName : layerNames)
            layers.put(layerName, (TiledMapTileLayer) tiledMap.getLayers().get(layerName));

        this.players = players;
        playerCells = new ArrayList<>();

        // Set up Playercells
        for(int i=0; i<players.size(); i++){
            Cell playerCell = new Cell();
            playerCell.setTile(new StaticTiledMapTile(new TextureRegion(new Texture("robots/"+robotTextureNames[i]+".png"))));
            playerCells.add(i, playerCell);
        }
    }

    public void addPlayer(IPlayer player){

    }

    public void updatePlayerPos() {
        for(int i=0; i<players.size(); i++){
            IPlayer player = players.get(i);
            Vector2 pos = player.getPos();

            switch (player.getOrientation()){
                case NORTH:
                    playerCells.get(i).setRotation(0);
                    break;
                case WEST:
                    playerCells.get(i).setRotation(1);
                    break;
                case SOUTH:
                    playerCells.get(i).setRotation(2);
                    break;
                case EAST:
                    playerCells.get(i).setRotation(3);
                    break;
            }
            layers.get("Player").setCell((int) pos.x, (int) pos.y, playerCells.get(i));
        }
    }

    public void clearPos(Vector2 pos){
        layers.get("Player").setCell((int) pos.x, (int) pos.y, new Cell());
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public int getTileTextureSize() {
        return tileTextureSize;
    }
}
