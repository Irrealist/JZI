package jzi.controller;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.File;
import java.util.LinkedList;

import jzi.controller.state.FightStateStub;
import jzi.controller.state.StateStub;
import jzi.model.GameStub;
import jzi.model.game.Game;
import jzi.model.game.GameCoop;
import jzi.model.game.IGame;
import jzi.model.map.ITileType;
import jzi.view.SetupMenu;
import jzi.view.GameMenu;
import jzi.view.IWindow;
import jzi.view.MainMenu;
import jzi.view.WindowStub;

import org.junit.BeforeClass;
import org.junit.Test;

public class ControllerTest {
    private static IWindow window;
    private static IGame game;
    private static Controller controller;

    /**
     * Initializes the test environment.
     */
    @BeforeClass
    public static void createController() {
        game = new Game(new LinkedList<ITileType>());
        window = new WindowStub();
        controller = new Controller(window);
    }

    /**
     * Tests loading and saving the game. Saves a game object with specific
     * attributes, loads it and makes sure they are equal.
     */
    @Test
    public void testLoadSave() {
        IGame loaded;
        // somewhat unique settings
        game.setZombification(true);
        game.setStartAmmo(666);
        game.setRevives(200);
        game.setState(new StateStub());

        controller.setGame(game);
        controller.saveGame(new File("test.jzi"));

        // Make sure the game is destroyed
        controller.setGame(null);

        controller.loadGame(new File("test.jzi"));

        loaded = controller.getGame();

        assertNotNull(loaded);
        assertTrue(loaded.isZombification());
        assertEquals(loaded.getStartAmmo(), 666);
        assertEquals(loaded.getRevives(), 200);
    }

    /**
     * Tests the new game action, makes sure the game object is set.
     */
    @Test
    public void testNewGame() {
        controller.setGame(null);
        controller.new NewGameAction().actionPerformed(null);

        assertTrue(controller.getGame() instanceof Game);
    }

    /**
     * Tests the surrender action, makes sure the game object is accessed.
     */
    @Test
    public void testSurrender() {
        GameStub game = new GameStub();

        controller.setGame(game);
        controller.new SurrenderAction().actionPerformed(null);

        assertTrue(game.hasChanged());
    }

    /**
     * Tests the coop game start, makes sure a new coop game is created.
     */
    @Test
    public void testCoop() {
        controller.setGame(null);
        controller.new CoopAction().actionPerformed(null);

        assertTrue(controller.getGame() instanceof GameCoop);
    }

    /**
     * Tests the back button action, makes sure it changes the menu.
     */
    @Test
    public void testAddBack() {
        controller.new AddBackAction().actionPerformed(null);
        assertTrue(window.getMenu() instanceof MainMenu);
    }

    /**
     * Tests the start game action, makes sure it correctly switches to the game
     * menu.
     */
    @Test
    public void testStart() {
        window.setMenu(new SetupMenu(window, controller.getGame()));
        controller.setGame(new GameStub());
        controller.new StartAction().actionPerformed(null);

        assertTrue(window.getMenu() instanceof GameMenu);

        // Test with coop game, too
        window.setMenu(new SetupMenu(window, controller.getGame()));
        controller.setGame(new GameStub(true));
        controller.new StartAction().actionPerformed(null);

        assertTrue(window.getMenu() instanceof GameMenu);
    }

    /**
     * Tests the add player action, makes sure the game object is modified.
     */
    @Test
    public void testAddPlayer() {
        GameStub game = new GameStub();

        window.setMenu(new SetupMenu(window, controller.getGame()));

        controller.setGame(game);
        controller.new AddPlayerAction().actionPerformed(null);

        assertTrue(game.hasChanged());
    }

    /**
     * Tests the roll action, makes sure the game object is modified.
     */
    @Test
    public void testRoll() {
        GameStub game = new GameStub();

        controller.setGame(game);
        controller.new RollAction().actionPerformed(null);

        assertTrue(game.hasChanged());
    }

    /**
     * Tests the draw action, makes sure the game object is modified.
     */
    @Test
    public void testDraw() {
        StateStub stub = new StateStub();

        game.setState(stub);

        controller.setGame(game);
        controller.new DrawAction().actionPerformed(null);

        assertTrue(stub.hasChanged());
    }

    /**
     * Tests the continue action, makes sure the game object is modified.
     */
    @Test
    public void testContinue() {
        StateStub stub = new StateStub();

        game.setState(stub);

        controller.setGame(game);
        controller.new ContinueAction().actionPerformed(null);

        assertTrue(stub.hasChanged());
    }

    /**
     * Tests the rotate actions, makes sure the game object is modified.
     */
    @Test
    public void testRotate() {
        GameStub stub = new GameStub();

        controller.setGame(stub);
        controller.new RotateLeftAction().actionPerformed(null);
        controller.new RotateRightAction().actionPerformed(null);

        assertTrue(stub.hasChanged());
    }

    /**
     * Tests the map action, makes sure the game object is modified.
     */
    @Test
    public void testMap() {
        StateStub stub = new StateStub();
        GameMenu menu = new GameMenu(window, game);

        menu.getMapPane().setLastPoint(new Point(0, 0));

        window.setMenu(menu);

        game.setState(stub);

        controller.setGame(game);
        controller.new MapAction().mouseClicked(null);

        assertTrue(stub.hasChanged());
    }

    /**
     * Tests the fight actions, makes sure the game object is modified.
     */
    @Test
    public void testFight() {
        window.setMenu(new GameMenu(window, game));

        FightStateStub stub = new FightStateStub(window);
        GameStub gameStub = new GameStub();

        gameStub.setState(stub);

        controller.setGame(gameStub);
        controller.new UseAmmoAction().actionPerformed(null);

        assertTrue(stub.hasChanged());

        controller.new UseLifeAction().actionPerformed(null);

        assertTrue(gameStub.hasChanged());
    }
}
