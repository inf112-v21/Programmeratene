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

    public CardMove(){
    }

    @Override
    public String toString() {
        return "CardMove{" +
                "steps=" + steps +
                ", priority=" + priority +
                '}';
    }

    /**
     * @return priority value of the card as an int
     */
    @Override
    public int getPriority() {
        return priority;
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

