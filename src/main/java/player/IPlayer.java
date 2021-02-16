package player;

public interface IPlayer {
    /**
     * Adds damage tokens to the player
     * @param damageTaken - amount of damage tokens to add
     */
    void applyDamage(int damageTaken);

    /**
     * Removes damage tokens from the player
     * @param repairAmount - amount of damage tokens to remove
     */
    void repair(int repairAmount);

    /**
     * Clear non-locked registers
     */
    void clearRegisters();

}
