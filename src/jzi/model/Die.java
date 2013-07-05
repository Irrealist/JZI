package jzi.model;

/**
 * Model for a game Die. Stores the value that was rolled.
 * 
 * @author Bud
 */
public class Die implements IDie {
    /**
     * UID serialization.
     */
    private static final long serialVersionUID = 8369238601510714439L;
    /**
     * Current value of the die.
     */
    private int value;

    /**
     * Retrieve the current die value.
     * 
     * @return die value
     */
    public final int getValue() {
        return value;
    }

    /**
     * Set the die value.
     * 
     * @param val
     *            value to be set.
     */
    public final void setValue(int val) {
        value = val;
    }
}
