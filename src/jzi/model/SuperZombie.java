package jzi.model;

import jzi.model.map.ICoordinates;

/**
 * Defines a super zombie. Super zombies need an additional point to defeat and
 * may move two steps per round.
 * 
 * @author Buddy Jonte
 * 
 */
public class SuperZombie extends Zombie {
    /**
     * Number the player must at least roll to defeat a super zombie.
     */
    private static final int SUPER_ZOMBIE_WIN_THRESHOLD = 5;
    /**
     * UID for serialization.
     */
    private static final long serialVersionUID = 2900814329262921516L;

    /**
     * Default constructor, calls super class default constructor.
     */
    public SuperZombie() {
        super();
    }

    /**
     * Constructor with coordinates, calls super class constructor.
     * 
     * @param coord
     *            new zombie {@link ICoordinates}
     */
    public SuperZombie(final ICoordinates coord) {
        super(coord);
    }

    /**
     * Resets the number of steps this zombie has left in the current round.
     * Default value is 2.
     */
    @Override
    public final void resetSteps() {
        setSteps(2);
    }

    /**
     * Gets the number the player must roll at least to defeat this zombie.
     * 
     * @return win threshold
     */
    @Override
    public final int getWinThreshold() {
        return SUPER_ZOMBIE_WIN_THRESHOLD;
    }
}
