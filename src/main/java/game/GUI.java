package game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import netcode.GameClient;
import netcode.Host;

import java.util.HashMap;

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
        font.setColor(Color.RED);

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
        switch(currentState){
            case MAIN_MENU:
                batch.draw(textures.get("MainMenuBG"), 0, 0, 700, 800);
                if(Gdx.input.getX() > 100 && Gdx.input.getX() < 600 && Gdx.input.getY() > 600 && Gdx.input.getY() < 800) {
                    batch.draw(textures.get("JoinGameHover"), 100, 0, 500, 200);
                    if(Gdx.input.isTouched()){
                        Gdx.input.getTextInput(textlistener,"Enter Host IP","","72.199.1.167");
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

                break;
            case HOST_LOBBY:
                batch.draw(textures.get("MainMenuBG"), 0, 0, 700, 800);
                break;
            case CLIENT_LOBBY:
                batch.draw(textures.get("MainMenuBG"), 0, 0, 700, 800);
                break;
            case IN_GAME:
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

            currentState = GUI_STATE.IN_GAME;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kryonet server couldn't bind to ports");
        }
    }

    public void startAsClient(String ip){
        gameClient = new GameClient();
        if(!gameClient.connectTo(ip)){
            System.out.print("Connection failed, try again");
        } else {
            camera = new OrthographicCamera();
            camera.setToOrtho(false, gameClient.board.getBOARD_WIDTH(),gameClient.board.getBOARD_HEIGHT());
            camera.update();
            renderer = new OrthogonalTiledMapRenderer(gameClient.board.getTiledMap(), (1f/gameClient.board.getTileTextureSize()));
            renderer.setView(camera);

            currentState = GUI_STATE.IN_GAME;
        }
    }

    public void initTextures(){
        textures = new HashMap<>();
        textures.put("MainMenuBG", new Texture("assets/menu/main_menu_background.png"));
        textures.put("JoinGame", new Texture("assets/menu/joingame.png"));
        textures.put("JoinGameHover", new Texture("assets/menu/joingame_hover.png"));
        textures.put("HostGame", new Texture("assets/menu/hostgame.png"));
        textures.put("HostGameHover", new Texture("assets/menu/hostgame_hover.png"));
    }
}
