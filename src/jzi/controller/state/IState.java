package jzi.controller.state;

import java.io.Serializable;

import jzi.model.IGame;
import jzi.model.map.ICoordinates;
import jzi.view.IWindow;

/**
 * Interface for game states.
 * 
 * @author Buddy
 */
public interface IState extends Serializable {

    /**
     * Performs any setup actions of the state.
     */
    void enter();

    /**
     * Performs any teardown actions of the state.
     */
    void exit();

    /**
     * Set the game instance.
     * 
     * @param game
     *            {@link Game} instance
     */
    void setGame(IGame game);

    /**
     * Sets the state's window handle.
     * 
     * @param window
     *            new window
     */
    void setWindow(IWindow window);

    /**
     * Is called when the player rolls the die.
     */
    void rollAction();

    /**
     * Is called when the player presses the continue button.
     */
    void continueAction();

    /**
     * Is called when the player clicks on the map.
     * 
     * @param field
     *            clicked field
     */
    void mapAction(ICoordinates field);

    /**
     * Is called when the player draws a tile.
     */
    void drawAction();
}
