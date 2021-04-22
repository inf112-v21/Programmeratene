package game;

import com.badlogic.gdx.Input.TextInputListener;

public class TextInput implements TextInputListener {
    GUI g;
    String s;

    TextInput(GUI g){
        this.g = g;
    }

    @Override
    public void input(String text) {
        this.s = text;
        g.startAsClient(s);
    }

    @Override
    public void canceled() {}
}
