package jzi.controller.state;

/**
 * Determines whether clicking moves or places a zombie.
 * 
 * @author Buddy Jonte
 * 
 */
public enum ZombieMode implements java.io.Serializable {
    /**
     * Clicking moves a zombie.
     */
    Move,
    /**
     * Clicking places a zombie.
     */
    Place,
}
