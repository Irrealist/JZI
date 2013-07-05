package jzi.controller.state;

import static org.junit.Assert.*;
import jzi.model.GameStub;
import jzi.view.GameMenu;
import jzi.view.IWindow;
import jzi.view.WindowStub;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FightStateTest {
    private static FightState state;
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

        state = new FightState(window);
        state.setGame(game);
    }

    /**
     * Resets the game object.
     */
    @Before
    public void reset() {
        // reset hasChanged()
        game.notifyObservers();
    }

    /**
     * Tests the continueAction method, makes sure it changes the state.
     */
    @Test
    public void testContinue() {
        state.continueAction();
        assertTrue(game.hasChanged());
    }

    /**
     * Tests the rollAction method, makes sure it honors the additionalPoint
     * flag.
     */
    @Test
    public void testRoll() {
        state.rollAction();

        assertTrue(game.hasChanged());

        reset();

        game.setAdditionalPoint(true);

        state.rollAction();

        assertTrue(game.hasChanged());
    }
}
