package card;

import java.util.Objects;

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

    public CardMove(){}

    @Override
    public String toString() {
        if(steps==-1){
            return "<Beveg " + steps + " bakover " + "(" + priority + ")>";
        }
        else{
            return "<Beveg " + steps + " frem " + "(" + priority + ")>";
        }
    }

    @Override
    public int compareTo(ICard iCard) {
        return iCard.getPriority() - getPriority();
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public int getSteps() {
        return steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardMove cardMove = (CardMove) o;
        return steps == cardMove.steps && priority == cardMove.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(steps, priority);
    }
}

