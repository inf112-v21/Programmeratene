package card;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Moves robot
 * -1 = 1 step back
 * 1 = 1 step forward
 * 2 = 2 step forward
 * 3 = 3 step forward
 */
public class CardMove implements ICard{
    int steps;
    int priority;

    public CardMove(int steps, int priority){
        this.steps = steps;
        this.priority = priority;
    }


    /**
     * @return priority value of the card as an int
     */
    @Override
    public int getPriority() {
        return priority;
    }
}


