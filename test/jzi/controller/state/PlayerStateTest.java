package jzi.controller.state;

import static org.junit.Assert.*;
import jzi.model.GameStub;
import jzi.model.PlayerStub;
import jzi.model.map.CoordinatesStub;
import jzi.view.GameMenu;
import jzi.view.IWindow;
import jzi.view.WindowStub;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerStateTest {
    private static PlayerState state;
    private static PlayerStub player;
    private static GameStub game;
    private static IWindow window;

    /**
     * Initializes the test environment.
     */
    @BeforeClass
    public static void setup() {
        player = new PlayerStub();

        game = new GameStub();

        window = new WindowStub();
        window.setMenu(new GameMenu(window, game));

        state = new PlayerState(window);
        state.setGame(game);
    }

    /**
     * Makes sure the game object is unchanged.
     */
    @Before
    public void reset() {
        // reset hasChanged()
        game.notifyObservers();
    }

    /**
     * Tests the enter method, makes sure it changes the game state if the
     * player has rolled for movement.
     */
    @Test
    public void testEnter() {
        player.setRolledPlayer(true);
        game.setCurrentPlayer(player);
        state.enter();
        assertTrue(game.hasChanged());
    }

    /**
     * Tests the continue method, makes sure it changes the game state.
     */
    @Test
    public void testContinue() {
        state.continueAction();
        assertTrue(game.hasChanged());
    }

    /**
     * Tests the mapAction method, makes sure it tries to move the player.
     */
    @Test
    public void testMap() {
        player.setRolledPlayer(true);
        game.setCurrentPlayer(player);
        state.mapAction(new CoordinatesStub());
        assertTrue(game.hasChanged());
    }
}
