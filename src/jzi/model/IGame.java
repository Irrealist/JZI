package jzi.model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Observer;

import jzi.controller.state.IState;
import jzi.model.map.ICoordinates;
import jzi.model.map.IField;
import jzi.model.map.IMap;
import jzi.model.map.ITile;
import jzi.view.IWindow;
import jzi.view.Update;

/**
 * Holds all game relevant data.
 * 
 * @author Buddy Jonte.
 * 
 */
public interface IGame extends java.io.Serializable {
    /**
     * Sets the game's current window.
     * 
     * @param window
     *            new game window
     */
    void setWindow(IWindow window);

    /**
     * Initializes the game.
     */
    void setUp();

    /**
     * Gets the current die value.
     * 
     * @return current die value.
     */
    int getDie();

    /**
     * Sets the die value.
     * 
     * @param val
     *            new die value
     */
    void setDie(int val);

    /**
     * Setter for an die object.
     * 
     * @param die
     *            new game die
     */
    void setDieObject(IDie die);

    /**
     * Draws a tile from the tile list.
     * 
     * @return true if tile is placeable.
     */
    boolean drawTile();

    /**
     * Gets the current tile.
     * 
     * @return current {@link ITile}, null if no tile was drawn or tile
     *         placement is finished
     */
    ITile getCurrentTile();

    /**
     * Sets the current tile (needed for JUnit test only).
     * 
     * @param currentTile
     *            new current tile
     */
    void setCurrentTile(ITile currentTile);

    /**
     * Adds a player to the game.
     * 
     * @param player
     *            player to be added
     */
    void addPlayer(IPlayer player);

    /**
     * Gets a list of players.
     * 
     * @return list of {@link IPlayer}s
     */
    LinkedList<IPlayer> getPlayers();

    /**
     * Advances the round to the next player and return this player.
     * 
     * @return next Player
     */
    IPlayer nextPlayer();

    /**
     * Gets the current player.
     * 
     * @return current player
     */
    IPlayer getCurrentPlayer();

    /**
     * Gets the map handle.
     * 
     * @return game {@link IMap}
     */
    IMap getMap();

    /**
     * Setter for a map object.
     * 
     * @param map
     *            new game map
     */
    void setMap(IMap map);

    /**
     * Sets the game state.
     * 
     * @param state
     *            new {@link IState}
     */
    void setState(IState state);

    /**
     * Gets the current state.
     * 
     * @return current {@link IState}
     */
    IState getCurrentState();

    /**
     * Moves the current player.
     * 
     * @param to
     *            {@link IField} to move player to.
     * @return true if successful.
     */
    boolean movePlayer(IField to);

    /**
     * Moves the current zombie to a field.
     * 
     * @param to
     *            {@link IField} to move current zombie to
     * @return true if successful
     */
    boolean moveZombie(IField to);

    /**
     * Tries to place a zombie on the given field.
     * 
     * @param field
     *            {@link IField} to place zombie on.
     * @return true if successful.
     */
    boolean placeZombie(IField field);

    /**
     * Places the current Tile.
     * 
     * @param currentCoordinates
     *            {@link ICoordinates} to place Tile at.
     * @return true if successful
     */
    boolean placeTile(ICoordinates currentCoordinates);

    /**
     * Checks if the game has been won.
     * 
     * @return true if game has been won
     */
    boolean checkWin();

    /**
     * Gets the player that has won.
     * 
     * @return {@link IPlayer} who has won
     */
    IPlayer getWinner();

    /**
     * Checks if the game is ready to be started. A game can only started if
     * there are at least two players.
     * 
     * @return true if game is ready
     */
    boolean isReady();

    /**
     * Gets the currently selected zombie.
     * 
     * @return current {@link IZombie}, null if no zombie is selected
     */
    IZombie getCurrentZombie();

    /**
     * Sets the current zombie.
     * 
     * @param currentZombie
     *            new current zombie
     */
    void setCurrentZombie(IZombie currentZombie);

    /**
     * Checks if list of tiles is empty.
     * 
     * @return true if tile list is empty
     */
    boolean noTiles();

    /**
     * Gets the win threshold.
     * 
     * @return winning threshold
     */
    int getWinThreshold();

    /**
     * Sets the win threshold.
     * 
     * @param winThreshold
     *            new winning threshold
     */
    void setWinThreshold(int winThreshold);

    /**
     * Gets a list of zombies in the game.
     * 
     * @return list of {@link IZombie}s
     */
    LinkedList<IZombie> getZombies();

    /**
     * Adds a zombie o the game.
     * 
     * @param zombie
     *            {@link IZombie} to be added
     */
    void addZombie(IZombie zombie);

    /**
     * Removes the current zombie from the game.
     */
    void removeZombie();

    /**
     * Checks whether the game is in zombification mode.
     * 
     * @return true if zombification is on
     */
    boolean isZombification();

    /**
     * Sets the zombification mode.
     * 
     * @param zombification
     *            whether or not zombification should be on
     */
    void setZombification(boolean zombification);

    /**
     * Revives the current player.
     */
    void revive();

    /**
     * Checks whether the current game gives an additional rolling point when
     * rolling for fights. This happens if the helipad is discarded because it
     * couldn't be placed.
     * 
     * @return true if game gives an additional rolling point in fights
     */
    boolean hasAdditionalPoint();

    /**
     * Makes the current player surrender.
     */
    void surrender();

    /**
     * Checks if the current game is in hardcore mode.
     * 
     * @return true if game is hardcore
     */
    boolean isHardcore();

    /**
     * Set the hardcore mode of the game.
     * 
     * @param hardcore
     *            whether or not the game should be hardcore
     */
    void setHardcore(boolean hardcore);

    /**
     * Gets the maximum number of times a player may be revived.
     * 
     * @return maximum number of revives
     */
    int getRevives();

    /**
     * Sets the maximum number of times a player may be revived.
     * 
     * @param revives
     *            new maximum number of revives
     */
    void setRevives(int revives);

    /**
     * Updates the game and notififes its observers.
     * 
     * @param update
     *            {@link Update} to send
     */
    void update(Update update);

    /**
     * Checks if a player with given name and color is valid.
     * 
     * @param name
     *            new player's name
     * @param color
     *            new player's color
     * @return true if player is valid
     */
    boolean isPlayerValid(String name, Color color);

    /**
     * Gets the game's window handle.
     * 
     * @return game's window
     */
    IWindow getWindow();

    /**
     * Determines whether the given zombie can move.
     * 
     * @param zombie
     *            zombie to check
     * @return true if zombie can move
     */
    boolean canZombieMove(IZombie zombie);

    void addObserver(Observer o);

    public int getDieDifference();

    public boolean isCoop();

    public void setStartAmmo(int startAmmo);

    public int getStartAmmo();
    
    /**
     * Rolls the die.
     */
    public void rollDie();
}
