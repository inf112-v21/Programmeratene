package card;

import game.Direction;

import java.util.Objects;

/**
 * Changes orientation of the robot
 * -1 = left turn
 * 1 = right turn
 * 2 = U-turn
 */
public class CardTurn implements ICard{
    int turnSteps;
    int priority;

    public CardTurn(int turnSteps, int priority) {
        this.turnSteps = turnSteps;
        this.priority = priority;
    }

    public CardTurn(){
    }

    public void checkPriority() {
        
    }

    @Override
    public String toString() {
        return "CardTurn{" + "turnSteps=" + turnSteps + ", priority=" + priority + '}';
    }

    public int getTurnSteps() {
        return turnSteps;
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
        CardTurn cardTurn = (CardTurn) o;
        return turnSteps == cardTurn.turnSteps && priority == cardTurn.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(turnSteps, priority);
    }
}
