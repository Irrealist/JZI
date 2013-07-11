package jzi.model.entities;

import jzi.model.map.ICoordinates;

/**
 * Manages one in-game zombie.
 * 
 * @author Buddy Jonte
 */
public class Zombie implements IZombie {
    /**
     * Number the player must at least roll to defeat this type of zombie.
     */
    private static final int ZOMBIE_WIN_THRESHOLD = 4;
    /**
     * UID for serialization.
     */
    private static final long serialVersionUID = -7850570582798249451L;
    /**
     * Number of steps this zombie has left in the current round.
     */
    private int steps;
    /**
     * Position of the zombie on the map.
     */
    private ICoordinates coord;

    /**
     * Default constructor, resets steps.
     */
    public Zombie() {
        resetSteps();
    }

    /**
     * Constructor with {@link ICoordinates}. Calls default constructor and sets
     * coordinates.
     * 
     * @param coord
     *            new zombie {@link ICoordinates}
     */
    public Zombie(ICoordinates coord) {
        this();
        this.coord = coord;
    }

    /**
     * Gets this zombie's coordinates.
     * 
     * @return zombie's {@link ICoordinates}
     */
    @Override
    public ICoordinates getCoordinates() {
        return coord;
    }

    /**
     * Sets this zombie's coordinates.
     * 
     * @param coord
     *            new {@link ICoordinates}
     */
    @Override
    public void setCoordinates(ICoordinates coord) {
        this.coord = coord;
    }

    /**
     * Returns how many steps this zombie has left in the current round.
     * 
     * @return number of steps
     */
    @Override
    public int getSteps() {
        return steps;
    }

    /**
     * Sets the number of steps this zombie has left in the current round.
     * 
     * @param steps
     *            new number of steps
     */
    @Override
    public void setSteps(int steps) {
        this.steps = steps;
    }

    /**
     * Resets the number of steps,this zombie has left in the current round to
     * the default value (1).
     */
    @Override
    public void resetSteps() {
        setSteps(1);
    }

    /**
     * Gets the number the player must at least roll to defeat this zombie. The
     * default value is 4.
     * 
     * @return win threshold
     */
    @Override
    public int getWinThreshold() {
        return ZOMBIE_WIN_THRESHOLD;
    }
}
