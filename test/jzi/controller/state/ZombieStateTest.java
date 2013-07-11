package jzi.controller.state;

import static org.junit.Assert.*;
import jzi.model.GameStub;
import jzi.model.game.Game;
import jzi.model.map.CoordinatesStub;
import jzi.view.GameMenu;
import jzi.view.IWindow;
import jzi.view.WindowStub;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ZombieStateTest {
    private static ZombieState state;
    private static GameStub game;
    private static IWindow window;

    /**
     * Initializes the test environment.
     */
    @BeforeClass
    public static void setup() {
        game = new GameStub();

        window = new WindowStub();
        window.setMenu(new GameMenu(window, game));

        state = new ZombieState(window);
        state.setGame(game);
    }

    /**
     * Resets the game object so hasChanged() returns false.
     */
    @Before
    public void reset() {
        // reset hasChanged()
        game.notifyObservers();
    }

    /**
     * Tests the ZombieState.rollAction() method, makes sure the ZombieMode is
     * set to Move if placement is not possible.
     */
    @Test
    public void testRoll() {
        game.setDie(1);

        state.setZombieMode(ZombieMode.Place);
        state.rollAction();

        assertEquals(state.getZombieMode(), ZombieMode.Move);

        game.setDie(Game.ZOMBIE_PLACE_COST + 1);

        state.setZombieMode(ZombieMode.Place);
        state.rollAction();

        assertEquals(state.getZombieMode(), ZombieMode.Place);
    }

    /**
     * Tests that the continue method changes the game state.
     */
    @Test
    public void testContinue() {
        state.continueAction();

        assertTrue(game.hasChanged());
    }

    /**
     * Tests that the mapAction method functions correctly.
     */
    @Test
    public void testMap() {
        state.setZombieMode(ZombieMode.Move);
        state.mapAction(new CoordinatesStub());

        assertTrue(game.hasChanged());
    }
}
