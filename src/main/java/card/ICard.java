package card;

public interface ICard {
    /**
     *
     * @return priority value of the card as an int
     */
    int getPriority();

    int compareTo(ICard iCard);
}
