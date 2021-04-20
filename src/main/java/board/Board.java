package board;

import board.boardelements.IBoardElement;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import game.Direction;
import player.IPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Board implements IBoard {
    private static final String[] robotTextureNames = {"RobotRed", "RobotBlue", "RobotGreen", "RobotYellow", "RobotCyan", "RobotOrange", "RobotPink", "RobotPurple"};
    public final int BOARD_WIDTH;
    public final int BOARD_HEIGHT;

    private final int tileTextureSize; //Size of tile-textures in pixels (From Tiled-editor)
    private final TiledMap tiledMap;
    private final HashMap<String, TiledMapTileLayer> layers;
    private final ArrayList<Cell> playerCells;

    private ArrayList<IPlayer> players;

    public Board(){
        // Set up TiledMap with layers
        tiledMap = new TmxMapLoader().load("Corners.tmx");
        MapProperties prop = tiledMap.getProperties();
        BOARD_WIDTH = prop.get("width", Integer.class);
        BOARD_HEIGHT = prop.get("height", Integer.class);
        tileTextureSize = prop.get("tilewidth", Integer.class); //note: tilewidth and tileheight should be the same

        layers = new HashMap<>();
        for (MapLayer layer : tiledMap.getLayers())
            layers.put(layer.getName(), (TiledMapTileLayer) layer);

        this.players = new ArrayList<>();
        playerCells = new ArrayList<>();

        for(int i=0; i<8; i++){
            Cell playerCell = new Cell();
            playerCell.setTile(new StaticTiledMapTile(new TextureRegion(new Texture("robots/"+robotTextureNames[i]+".png"))));
            playerCells.add(playerCell);
        }
    }


    public boolean canMove(Vector2 pos, Direction orientation) {
        boolean check = true;
        Vector2 pos2 = new Vector2(pos);
        pos2.add(orientation.getVector());
        //Wall-tiles id in their respective list. Walls facing north will be in int[] north, etc.
        int[] north = new int[]{24, 31, 16};
        int[] south = new int[]{29, 32, 8};
        int[] east = new int[]{8, 16, 23};
        int[] west = new int[]{24, 30, 32};

            switch (orientation) {
                case NORTH:
                    try {
                        if (Arrays.stream(north).anyMatch(x -> x == (layers.get("Walls").getCell((int) pos.x, (int) pos.y).getTile().getId()))) {
                            check = false;
                        }
                    }
                    catch (Exception e) {
                        //If we have nullpointerexception here, it means we have no walls here, and we can continue with checking the location the robot wants to move to.
                    }
                    try {
                        if (Arrays.stream(south).anyMatch(x -> x == (layers.get("Walls").getCell((int) pos2.x, (int) pos2.y).getTile().getId()))) {
                            check = false;
                            //If there is a south wall from this direction, or north wall from the robot`s direction before moving, the robot can`t move here.
                        }
                    }
                    catch (Exception e) {
                    }

                    break;
                    case SOUTH:
                        try {
                            if (Arrays.stream(south).anyMatch(x -> x == (layers.get("Walls").getCell((int) pos.x, (int) pos.y).getTile().getId()))) {
                                check = false;
                            }
                        }
                        catch (Exception e) {
                        }
                        try {
                            if (Arrays.stream(north).anyMatch(x -> x == (layers.get("Walls").getCell((int) pos2.x, (int) pos2.y).getTile().getId()))) {
                                check = false;
                            }
                        }
                        catch (Exception e) {
                        }
                        break;
                    case EAST:
                        try {
                            if (Arrays.stream(east).anyMatch(x -> x == (layers.get("Walls").getCell((int) pos.x, (int) pos.y).getTile().getId()))) {
                                check = false;
                            }
                        }
                        catch (Exception e) {
                        }
                        try {
                            if (Arrays.stream(west).anyMatch(x -> x == (layers.get("Walls").getCell((int) pos2.x, (int) pos2.y).getTile().getId()))) {
                                check = false;
                            }
                        }
                        catch (Exception e) {
                        }

                        break;
                    case WEST:
                        try {
                            if (Arrays.stream(west).anyMatch(x -> x == (layers.get("Walls").getCell((int) pos.x, (int) pos.y).getTile().getId()))) {
                                check = false;
                            }
                        }
                        catch (Exception e) {
                        }
                        try {
                            if (Arrays.stream(east).anyMatch(x -> x == (layers.get("Walls").getCell((int) pos2.x, (int) pos2.y).getTile().getId()))) {
                                check = false;
                            }
                        }
                        catch (Exception e) {
                        }

                        break;
                }
        return check;
    }

    public void addPlayer(IPlayer player){
        players.add(player);
        drawPlayers();
    }

    public void drawPlayers() {
        clearAllPlayerPos();
        for(int i=0; i<players.size(); i++){
            IPlayer player = players.get(i);
            Vector2 pos = player.getPos();
            //System.out.println("Drawing position of player "+player.getPlayerName()+" in position ("+pos.x+", "+pos.y+") facing "+player.getOrientation());

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

    public void clearAllPlayerPos() {
        Cell emptyCell = new Cell();
        for(int y=0; y<BOARD_HEIGHT; y++)
            for(int x=0; x<BOARD_WIDTH; x++)
                layers.get("Player").setCell(x, y, emptyCell);
    }

    public String getElementInPos(Vector2 pos){
        if(layers.get("Flag").getCell((int) pos.x, (int) pos.y) != null)
            return "flag";
        else if(layers.get("Hole").getCell((int) pos.x, (int) pos.y) != null)
            return "hole";

        return "floortile";
    }

    public ArrayList<Vector2> getSpawns() {
        ArrayList<Vector2> spawns = new ArrayList<>();
        for(int y=0; y<BOARD_HEIGHT; y++)
            for(int x=0; x<BOARD_WIDTH; x++)
                if(layers.get("Spawn").getCell(x, y) != null)
                    spawns.add(new Vector2(x, y));
        return spawns;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public int getTileTextureSize() {
        return tileTextureSize;
    }

    public void setPlayers(ArrayList<IPlayer> players) {
        this.players = players;
    }

    public int getBOARD_WIDTH() {
        return BOARD_WIDTH;
    }

    public int getBOARD_HEIGHT() {
        return BOARD_HEIGHT;
    }
}
