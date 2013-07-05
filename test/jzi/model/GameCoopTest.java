package jzi.model;

import static org.junit.Assert.*;

import java.util.LinkedList;

import jzi.controller.state.ZombieState;
import jzi.model.map.FieldEmptyStub;
import jzi.model.map.ITileType;
import jzi.model.map.MapStub;
import jzi.view.GameMenu;
import jzi.view.WindowStub;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameCoopTest {
    private static GameCoop game;
    private static WindowStub window;
    private static MapStub map;

    /**
     * Initializes variables for this test.
     */
    @BeforeClass
    public static void setUp() {
        LinkedList<ITileType> list = new LinkedList<>();

        map = new MapStub();

        game = new GameCoop(list);
        game.addPlayer(new PlayerStub());
        game.setWinThreshold(50);
        game.setMap(map);
        game.addZombie(new ZombieStub());

        window = new WindowStub();
        window.setMenu(new GameMenu(window, game));

        game.setWindow(window);

        map.setField(new FieldEmptyStub());
    }

    /**
     * Tests the GameCoop.setState() method, makes sure that it switches to
     * another state if a ZombieState is specified.
     */
    @Test
    public void testState() {
        window.setMenu(new GameMenu(window, game));
        game.setState(new ZombieState(window));
        assertFalse(game.getCurrentState() instanceof ZombieState);
    }

    /**
     * Tests the GameCoop.checkWin() method, makes sure that it returns false
     * normally and true if winning conditions are met.
     */
    @Test
    public void testWin() {
        assertFalse(game.checkWin());

        game.addPlayer(new PlayerWinStub());

        assertTrue(game.checkWin());
    }
}
