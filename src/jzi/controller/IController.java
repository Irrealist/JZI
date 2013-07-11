package jzi.controller;

import java.io.File;

import jzi.model.game.IGame;
import jzi.view.IWindow;

/**
 * Interface for the game controller. The controller controls the game flow.
 * 
 * @author Buddy Jonte
 * 
 */
public interface IController {
    /**
     * Gets the controller's game instance.
     * 
     * @return current game instance
     */
    IGame getGame();

    /**
     * Sets the controller's game instance.
     * 
     * @param game
     *            new game instance
     */
    void setGame(IGame game);

    IWindow getWindow();

    void saveGame(File file);

    void loadGame(File file);
}
