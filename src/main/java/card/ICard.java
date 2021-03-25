package card;

public interface ICard {

    /**
     * Override Object.compareTo so that cards are compared with respects to priority
     * @param iCard
     * @return
     */
    int compareTo(ICard iCard);

    /*
        GETTERS
     */
    int getPriority();
}
