package jzi.model;

/**
 * Interface for a class that represents a game die.
 * 
 * @author Buddy Jonte
 * 
 */
public interface IDie extends java.io.Serializable {
    /**
     * Getter to get the value of the die.
     * 
     * @return the value of dice.
     */

    int getValue();

    /**
     * Set the new value for die.
     * 
     * @param val
     *            new value.
     */
    void setValue(int val);
}
