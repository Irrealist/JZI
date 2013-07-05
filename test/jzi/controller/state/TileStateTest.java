package jzi.controller.state;

import static org.junit.Assert.*;
import jzi.model.GameStub;
import jzi.model.map.CoordinatesStub;
import jzi.model.map.FieldEmptyStub;
import jzi.model.map.MapStub;
import jzi.view.GameMenu;
import jzi.view.IWindow;
import jzi.view.WindowStub;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TileStateTest {
    private static TileState state;
    private static GameStub game;
    private static MapStub map;
    private static IWindow window;

    /**
     * Initializes the test environment.
     */
    @BeforeClass
    public static void setup() {
        map = new MapStub();

        game = new GameStub();
        game.setMap(map);

        window = new WindowStub();
        window.setMenu(new GameMenu(window, game));

        state = new TileState(window);
        state.setGame(game);
    }

    /**
     * Resets the game object so that hasChanged() returns false.
     */
    @Before
    public void reset() {
        // reset hasChanged()
        game.notifyObservers();
    }

    /**
     * Tests the enter method, makes sure the game object is changed when there
     * are no tiles left in the game.
     */
    @Test
    public void testEnter() {
        state.enter();
        // Game should have changed, because noTiles == true && setState leads
        // to setChanged
        assertTrue(game.hasChanged());
    }

    /**
     * Tests the continue method, makes sure the state is changed.
     */
    @Test
    public void testContinue() {
        state.continueAction();
        assertTrue(game.hasChanged());
    }

    /**
     * Tests the draw method, makes sure a tile is drawn.
     */
    @Test
    public void testDraw() {
        state.drawAction();
        assertTrue(game.hasChanged());
    }

    /**
     * Tests the mapAction method, makes sure a tile and objects on it can be
     * placed.
     */
    @Test
    public void testMap() {
        // mehhh
        state.mapAction(new CoordinatesStub());
        assertTrue(game.hasChanged());

        reset();

        state.mapAction(new CoordinatesStub());
        assertTrue(game.hasChanged());

        reset();

        map.setField(new FieldEmptyStub());

        state.mapAction(new CoordinatesStub());
        assertTrue(game.hasChanged());
    }
}
