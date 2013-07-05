package jzi.model;

import java.awt.Color;

import jzi.model.map.Coordinates;
import jzi.model.map.ICoordinates;

/**
 * Contains all relevant data for one player.
 * 
 * @author Buddy Jonte
 * 
 */
public class Player implements IPlayer {
    /**
     * Number of kills that are deducted if the player is revived.
     */
    private static final int REVIVE_PENALTY = 5;
    /**
     * Amount of ammunition a player has at the start or after being revived.
     */
    private static final int START_AMMUNITION = 3;
    /**
     * Number of life points a player has at the start or after being revived.
     */
    private static final int START_LIVES = 3;
    /**
     * UID for serialization.
     */
    private static final long serialVersionUID = 2194616172649082850L;
    /**
     * Whether or not the player has rolled for movement.
     */
    private boolean playerRoll;
    /**
     * Whether or not the player has rolled for zombie movement.
     */
    private boolean zombieRoll;
    /**
     * Name of the player.
     */
    private String name;
    /**
     * Color the player has picked.
     */
    private Color color;
    /**
     * Number of kills the player has made.
     */
    private int points;
    /**
     * Number of life points the player has left.
     */
    private int lives;
    /**
     * Amount of ammunition the player has left.
     */
    private int ammo;
    /**
     * Number of times the player has been revived.
     */
    private int revives;
    /**
     * Coordinates of the player on the map.
     */
    private ICoordinates coords;
    /**
     * Number of steps the player has left in the current round.
     */
    private int steps;
    /**
     * Number of zombie movements the player has left in the current round.
     */
    private int zombies;

    /**
     * Default constructor; sets number of lives, amount of ammunition and
     * coordinates.
     */
    public Player() {
        setLives(START_LIVES);
        setAmmo(START_AMMUNITION);
        setCoordinates(new Coordinates(0, 0));
    }

    /**
     * Constructor with name and color.
     * 
     * @param name
     *            name for the player
     * @param color
     *            color of the player
     */
    public Player(final String name, final Color color) {
        this();
        setName(name);
        setColor(color);
    }

    /**
     * Gets the player's name.
     * 
     * @return name of the player
     */
    public final String getName() {
        return name;

    }

    /**
     * Setter for the name of the player.
     * 
     * @param name
     *            name of the player.
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Getter for the color of the player.
     * 
     * @return the color of the player
     */
    public final Color getColor() {
        return color;
    }

    /**
     * Setter for the color of the player.
     * 
     * @param color
     *            color of the player
     */
    public final void setColor(final Color color) {
        this.color = color;
    }

    /**
     * Gets the number of kills the player has made.
     * 
     * @return number of kills
     */
    public final int getPoints() {
        return points;
    }

    /**
     * Sets the number of kills the player has made.
     * 
     * @param points
     *            new number of kills
     */
    public final void setPoints(final int points) {
        this.points = points;
    }

    /**
     * Getter for the player's lives.
     * 
     * @return the player's lives
     */
    public final int getLives() {
        return lives;

    }

    /**
     * Setter for the player's lives.
     * 
     * @param lives
     *            player's lives
     */
    public final void setLives(final int lives) {
        this.lives = lives;

    }

    /**
     * Getter for the player's ammo.
     * 
     * @return the player's ammo
     */
    public final int getAmmo() {
        return ammo;

    }

    /**
     * Setter for the player's ammo.
     * 
     * @param ammo
     *            player's ammo
     */
    public final void setAmmo(final int ammo) {
        this.ammo = ammo;

    }

    /**
     * Gets the number of steps the player has remaining.
     * 
     * @return remaining number of steps
     */
    public final int getSteps() {
        return steps;
    }

    /**
     * Sets the number of steps the player has remaining.
     * 
     * @param steps
     *            number of steps to set.
     */
    public final void setSteps(final int steps) {
        this.steps = steps;
    }
    
    /**
     * Gets the number of zombie movements the player has left.
     * 
     * @return number of zombie movements
     */
    @Override
    public final int getZombies() {
        return zombies;
    }

    /**
     * Sets the number of zombie movements the player has left.
     * 
     * @param zombies
     *            new number of zombie movements
     */
    @Override
    public final void setZombies(final int zombies) {
        this.zombies = zombies;
    }

    
    /**
     * Checks if player has rolled for movement.
     * 
     * @return true if player has rollde for movement
     */
    @Override
    public final boolean hasRolledPlayer() {
        return playerRoll;
    }

    /**
     * Sets whether the player has rolled for movement.
     * 
     * @param roll
     *            whether or not the player has rolled for movement
     */
    @Override
    public final void setRolledPlayer(final boolean roll) {
        playerRoll = roll;
    }

    /**
     * Checks if the player has rolled for zombie movement.
     * 
     * @return true if the player has rolled for zombie movement
     */
    @Override
    public final boolean hasRolledZombie() {
        return zombieRoll;
    }

    /**
     * Sets whether the player has rolled for zombie movement.
     * 
     * @param roll
     *            whether or not the player has rolled for zombie movement
     */
    @Override
    public final void setRolledZombie(final boolean roll) {
        zombieRoll = roll;
    }

    /**
     * Revives the player, resets attributes, sets coordinates to (0, 0) and
     * deducts kills as a penalty.
     */
    @Override
    public final void revive() {
        revives++;

        setLives(START_LIVES);
        setAmmo(START_AMMUNITION);
        setCoordinates(new Coordinates(0, 0));

        if (points > REVIVE_PENALTY) {
            points -= REVIVE_PENALTY;
        } else {
            points = 0;
        } 
    }

    /**
     * Gets the player's coordinates.
     * 
     * @return player {@link ICoordinates}
     */
    @Override
    public final  ICoordinates getCoordinates() {
        return coords;
    }

    /**
     * Sets the player's coordinates.
     * 
     * @param coord
     *            new {@link ICoordinates}
     */
    @Override
    public final void setCoordinates(final ICoordinates coord) {
        coords = coord;
    }

    /**
     * Gets the number of times the player was revived.
     * 
     * @return number of revives
     */
    @Override
    public final  int getRevives() {
        return revives;
    }
}
