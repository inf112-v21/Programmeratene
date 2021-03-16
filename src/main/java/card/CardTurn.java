package card;

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

    @Override
    public String toString() {
        if(turnSteps == -1){
            return "<Snu venstre " + "(" + priority + ")>";
        }
        else if(turnSteps == 1){
            return "<Snu høyre " + "(" + priority + ")>";
        }
        else if(turnSteps == 2){
            return "<Snu 180 " + "(" + priority + ")>";
        }
        else{
            return super.toString();
        }
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

    @Override
    public int compareTo(ICard iCard) {
        return iCard.getPriority() - getPriority();
    }
}
