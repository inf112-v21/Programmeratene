package game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.esotericsoftware.kryonet.Connection;
import netcode.GameClient;
import netcode.Host;
import player.IPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GUI implements ApplicationListener {
    private enum GUI_STATE {MAIN_MENU, HOST_LOBBY, CLIENT_LOBBY, IN_GAME}
    private HashMap<String, Texture> textures;
    private SpriteBatch batch;
    private BitmapFont font;

    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TextInput textlistener;

    private GameClient gameClient;
    private Host host;
    private GUI_STATE currentState = GUI_STATE.MAIN_MENU;


    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);

        initTextures();
        textlistener = new TextInput(this);
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

        batch.begin();
        if(currentState.equals(GUI_STATE.MAIN_MENU)){
            batch.draw(textures.get("MainMenuBG"), 0, 0, 700, 800);
            if(Gdx.input.getX() > 100 && Gdx.input.getX() < 600 && Gdx.input.getY() > 600 && Gdx.input.getY() < 800) {
                batch.draw(textures.get("JoinGameHover"), 100, 0, 500, 200);
                if(Gdx.input.isTouched()){
                    Gdx.input.getTextInput(textlistener,"Enter Host IP","","72.199.1.167");
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Input IP to connect to: ");
                    startAsClient(sc.nextLine());
                }
            } else {
                batch.draw(textures.get("JoinGame"), 100, 0, 500, 200);
            }
            if(Gdx.input.getX() > 100 && Gdx.input.getX() < 600 && Gdx.input.getY() > 375 && Gdx.input.getY() < 575) {
                batch.draw(textures.get("HostGameHover"), 100, 225, 500, 200);
                if(Gdx.input.isTouched())
                    startAsHost();
            } else {
                batch.draw(textures.get("HostGame"), 100, 225, 500, 200);
            }
        }
        else if(currentState.equals(GUI_STATE.HOST_LOBBY)){
            batch.draw(textures.get("MainMenuBG"), 0, 0, 700, 800);
            GlyphLayout playerHeader = new GlyphLayout(font,"Connected players:");
            font.draw(batch, playerHeader, 500, 500);
            for(Map.Entry<Connection, IPlayer> entry : host.playerMap.entrySet()){
                GlyphLayout playerEntry = new GlyphLayout(font, "Name: " + entry.getValue().getPlayerName() +"\nIP:"+entry.getKey().getRemoteAddressTCP().getHostString());
                font.draw(batch, playerEntry, 500, 500 - host.playerMap.size()*40);
            }
            if(Gdx.input.getX() > 100 && Gdx.input.getX() < 600 && Gdx.input.getY() > 600 && Gdx.input.getY() < 800) {
                batch.draw(textures.get("StartGameHover"), 100, 0, 500, 200);
                if(Gdx.input.isTouched()){
                    currentState = GUI_STATE.IN_GAME;
                    host.dealCards();
                }
            } else {
                batch.draw(textures.get("StartGame"), 100, 0, 500, 200);
            }
        }
        else if(currentState.equals(GUI_STATE.CLIENT_LOBBY)){
            batch.draw(textures.get("MainMenuBG"), 0, 0, 700, 800);
            GlyphLayout playerHeader = new GlyphLayout(font, "Connected players:");
            font.draw(batch, playerHeader, 500, 500);
            for(Map.Entry<Connection, IPlayer> entry : host.playerMap.entrySet()){
                GlyphLayout playerEntry = new GlyphLayout(font, "Name: " + entry.getValue().getPlayerName() +"\nIP:"+entry.getKey().getRemoteAddressTCP().getHostString());
                font.draw(batch, playerEntry, 500, 500 - host.playerMap.size()*40);
            }
            if(!gameClient.inLobby)
                currentState = GUI_STATE.IN_GAME;
        }
        else if(currentState.equals(GUI_STATE.IN_GAME)){
            renderer.render();
        }
        batch.end();
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

    public void startAsHost(){
        try {
            host = new Host();
            gameClient = host.getGameClient();

            camera = new OrthographicCamera();
            camera.setToOrtho(false, gameClient.board.getBOARD_WIDTH(),gameClient.board.getBOARD_HEIGHT());
            camera.update();
            renderer = new OrthogonalTiledMapRenderer(gameClient.board.getTiledMap(), (1f/gameClient.board.getTileTextureSize()));
            renderer.setView(camera);

            currentState = GUI_STATE.HOST_LOBBY;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kryonet server couldn't bind to ports");
        }
    }

    public void startAsClient(String ip){
        gameClient = new GameClient();
        if(gameClient.connectTo(ip)){
            camera = new OrthographicCamera();
            camera.setToOrtho(false, gameClient.board.getBOARD_WIDTH(),gameClient.board.getBOARD_HEIGHT());
            camera.update();
            renderer = new OrthogonalTiledMapRenderer(gameClient.board.getTiledMap(), (1f/gameClient.board.getTileTextureSize()));
            renderer.setView(camera);

            currentState = GUI_STATE.CLIENT_LOBBY;
        }
    }

    public void initTextures(){
        textures = new HashMap<>();
        textures.put("MainMenuBG", new Texture("assets/menu/main_menu_background.png"));
        textures.put("JoinGame", new Texture("assets/menu/joingame.png"));
        textures.put("JoinGameHover", new Texture("assets/menu/joingame_hover.png"));
        textures.put("HostGame", new Texture("assets/menu/hostgame.png"));
        textures.put("HostGameHover", new Texture("assets/menu/hostgame_hover.png"));
        textures.put("StartGame", new Texture("assets/menu/startgame.png"));
        textures.put("StartGameHover", new Texture("assets/menu/startgame_hover.png"));
    }
}
