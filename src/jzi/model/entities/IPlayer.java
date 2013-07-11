package jzi.model.entities;

import java.awt.Color;

import jzi.model.map.ICoordinates;

/**
 * Interface for players.
 * 
 * @author Buddy Jonte
 * 
 */
public interface IPlayer extends java.io.Serializable {
    
    /**
     * Returns the player's name.
     * 
     * @return player's name
     */
    String getName();

    /**
     * Sets the player's name.
     * 
     * @param name
     *            new name
     */
    void setName(String name);

    /**
     * Returns the player's color.
     * 
     * @return player's color
     */
    Color getColor();

    /**
     * Sets the player's color.
     * 
     * @param color
     *            new color
     */
    void setColor(Color color);

    /**
     * Returns the player's points.
     * 
     * @return player's points
     */
    int getPoints();

    /**
     * Sets the player's points.
     * 
     * @param points
     *            new number of points
     */
    void setPoints(int points);

    /**
     * Returns the player's number of lives.
     * 
     * @return player's lives
     */
    int getLives();

    /**
     * Set the player's number of lives.
     * 
     * @param lives
     *            new number of lives
     */
    void setLives(int lives);

    /**
     * Returns the amount of ammunition the player has.
     * 
     * @return ammunition number
     */
    int getAmmo();

    /**
     * Set how much ammunition the player has left.
     * 
     * @param ammo
     *            new amount of ammunition
     */
    void setAmmo(int ammo);
    
    /**
     * Gets the number of steps the player has left.
     * 
     * @return number of steps
     */
    int getSteps();
    
    /**
     * Sets the number of steps the player has left.
     * 
     * @param steps
     *            new number of steps
     */
    void setSteps(int steps);
    
    /**
     * Gets the number of zombies the player may move.
     * 
     * @return number of zombies
     */
    int getZombies();
    
    /**
     * Sets the number of zombies the player may move.
     * 
     * @param zombies
     *            new number of zombies
     */
    void setZombies(int zombies);

    /**
     * Get whether the player has rolled for movement.
     * 
     * @return playerRoll
     */
    boolean hasRolledPlayer();

    /**
     * Set whether or not the player has rolled for movement.
     * 
     * @param roll
     *            new playerRoll
     */
    void setRolledPlayer(boolean roll);
    
    /**
     * Get whether the player has rolled for zombie movement.
     * 
     * @return rollZombie.
     */
    boolean hasRolledZombie();

    /**
     * Set whether or not the player has rolled to move the zombies.
     * 
     * @param roll
     *            new value
     */
    void setRolledZombie(boolean roll);
    
    /**
     * revives the player and resets attributes.
     */
    void revive();
    
    /**
     * Returns the player's coordinates.
     * 
     * @return {@link ICoordinates} of this player
     */
    ICoordinates getCoordinates();

    /**
     * Sets the player's coordinates.
     * 
     * @param coord
     *            new coordinates
     */
    void setCoordinates(ICoordinates coord);

    /**
     * Gets the number of times the player has been revived.
     * 
     * @return revives.
     */
    int getRevives();
}
