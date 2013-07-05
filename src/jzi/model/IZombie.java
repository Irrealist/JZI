package jzi.model;

import jzi.model.map.ICoordinates;

/**
 * Interface for classes that represent zombies in the game.
 * 
 * @author Buddy Jonte
 * 
 */
public interface IZombie extends java.io.Serializable {

    /**
     * Gets the zombie's coordinates.
     * 
     * @return zombie's {@link ICoordinates}
     */
    ICoordinates getCoordinates();

    /**
     * Sets the zombie's coordinates.
     * 
     * @param coord
     *            new zombie {@link ICoordinates}.
     */
    void setCoordinates(ICoordinates coord);

    /**
     * Gets the steps the zombie has left in the current round.
     * 
     * @return steps left
     */
    int getSteps();

    /**
     * Sets how many steps the zombie has left in the current round.
     * 
     * @param steps
     *            new number of steps
     */
    void setSteps(int steps);

    /**
     * Resets the number of steps the zombie may take to a default value.
     */
    void resetSteps();

    /**
     * Gets the minimum number a player must roll to defeat the zombie.
     * 
     * @return win threshold for the zombie
     */
    int getWinThreshold();
}
