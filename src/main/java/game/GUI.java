package game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import netcode.GameClient;
import netcode.Host;

import java.util.Scanner;

public class GUI implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;

    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private GameClient gameClient;
    private Host host;


    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);


        Scanner sc = new Scanner(System.in);
        System.out.print("Start game as host? (y/n): ");

        if (sc.nextLine().equals("y")){
            startAsHost();
        }
        else {
            System.out.print("Input IP to connect to: ");
            startAsClient(sc.nextLine());
        }

        // Set up Camera and Renderer
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 5, 5);
        camera.update();
        renderer = new OrthogonalTiledMapRenderer(gameClient.getGame().getBoard().getTiledMap(), (1f/gameClient.getGame().getBoard().getTileTextureSize()));
        renderer.setView(camera);

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

    public void startAsHost(){
        gameClient = new GameClient();
        host = new Host();
    }

    public void startAsClient(String ip){
        gameClient = new GameClient();
        if(gameClient.connectTo(ip)){
            //connection success
        }
        else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Connection failed, Ctrl+C to exit or re-enter IP: ");
            startAsClient(sc.nextLine());
        }
    }
}
