package jzi.model;

import static org.junit.Assert.*;

import java.util.*;

import jzi.model.map.CoordinatesStub;
import jzi.model.map.FieldEmptyStub;
import jzi.model.map.FieldStub;
import jzi.model.map.ITileType;
import jzi.model.map.MapStub;
import jzi.model.map.MapStub1;
import jzi.model.map.TileStub;
import jzi.model.map.TileTypeStub;
import jzi.view.IWindow;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Black-Box-Test for the game class in src.jzi.model
 * 
 * @author MaiBot guest24
 */
public class GameTest {
    /**
     * object of the class which is to be tested
     */
    private static Game gameTestObj;
    /**
     * Stubs needed for tests
     */
    private static TileTypeStub tileTypeStub;
    private static MapStub mapStub;
    private static MapStub1 mapStub1;
    private static DieStub dieStub;
    private static PlayerStub playerStub;
    private static PlayerStub1 playerStub1;
    private static ZombieStub zombieStub;
    private static TileStub tileStub;
    private static FieldStub fieldStub;
    /**
     * LinkedList filled with TileTypeStubs
     */
    private static List<ITileType> shuffledListforTest;
    /**
     * Window object for the constructor of the test object
     */
    private static IWindow window;

    /**
     * Executed once before the first of the series of tests,external resources
     * that are use by tests can be initialized here.
     */
    @BeforeClass
    public static void setUpBeforeClasse() throws Exception {
        shuffledListforTest = new LinkedList<>();
        tileTypeStub = new TileTypeStub();
        mapStub = new MapStub();
        mapStub1 = new MapStub1();
        dieStub = new DieStub();
        playerStub = new PlayerStub();
        playerStub1 = new PlayerStub1();
        zombieStub = new ZombieStub();
        tileStub = new TileStub();
        fieldStub = new FieldStub();
    }
    /**
     * Executed before each test,set up the test object to the initial state all
     * tests assume it to be in.
     */
    @Before
    public void setUp() throws Exception {
        int i = 0;
        while (i < 30) {
            shuffledListforTest.add(tileTypeStub);
            i++;
        }
        gameTestObj = new Game(shuffledListforTest);
        gameTestObj.setWindow(window);
        gameTestObj.setMap(mapStub);
        gameTestObj.addPlayer(playerStub);
        gameTestObj.setCurrentZombie(zombieStub);
        gameTestObj.setDieObject(dieStub);
        gameTestObj.setUp();
    }

    /**
     * Test will return normally if gameTestObj.drawTile() return false
     */
    @Test
    public void testDrawTileIfTrue() {
        assertFalse(gameTestObj.drawTile());
    }

    /**
     * Test will return normally if gameTestObj.drawTile() return true
     */
    @Test
    public void testDrawTileIfFalse() {
        shuffledListforTest.clear();
        gameTestObj.setMap(mapStub1);
        assertTrue(gameTestObj.drawTile());
    }

    /**
     * Test will return normally if gameTestObj.movePlayer() return false
     */
    @Test
    public void testMovePlayerIfTrue() {
        assertFalse(gameTestObj.movePlayer(fieldStub));
    }

    /**
     * Test will return normally if gameTestObj.movePlayer() return false
     */
    @Test
    public void testMovePlayerIfTrue1() {
        gameTestObj.setMap(mapStub1);
        assertFalse(gameTestObj.movePlayer(fieldStub));
    }

    /**
     * Test will return normally if gameTestObj.movePlayer() return true
     */
    @Test
    public void testMovePlayerIfFalse() {
        gameTestObj.setMap(mapStub1);
        gameTestObj.addPlayer(playerStub1);
        gameTestObj.nextPlayer();
        assertTrue(gameTestObj.movePlayer(fieldStub));
    }

    /**
     * Test will return normally if gameTestObj.moveZombie() return false
     */
    @Test
    public void testMoveZombieIfTrue() {
        mapStub.setField(new FieldEmptyStub());
        gameTestObj.setMap(mapStub);
        assertFalse(gameTestObj.moveZombie(fieldStub));
    }

    /**
     * Test will return normally if gameTestObj.moveZombie() return false
     */
    @Test
    public void testMoveZombieIfTrue1() {
        gameTestObj.setMap(mapStub1);
        assertFalse(gameTestObj.moveZombie(fieldStub));
    }

    /**
     * Test will return normally if gameTestObj.moveZombie() return true
     */
    @Test
    public void testMoveZombieIfFalse() {
        mapStub1.setField(new FieldEmptyStub());
        gameTestObj.setMap(mapStub1);
        gameTestObj.addPlayer(playerStub1);
        gameTestObj.nextPlayer();
        assertTrue(gameTestObj.moveZombie(new FieldEmptyStub()));
    }

    /**
     * Test will return normally if gameTestObj.placeZombie() return false
     */
    @Test
    public void testPlaceZombieIfTrue() {
        assertFalse(gameTestObj.placeZombie(fieldStub));
    }

    /**
     * Test will return normally if gameTestObj.placeZombie() return true
     */
    @Test
    public void testPlaceZombieIfFalse() {
        gameTestObj.addPlayer(playerStub1);
        gameTestObj.nextPlayer();
        assertTrue(gameTestObj.placeZombie(new FieldEmptyStub()));
    }

    /**
     * Test will return normally if gameTestObj.placeTile() return true
     */
    @Test
    public void testPlaceTileIfFalse() {
        gameTestObj.setCurrentTile(tileStub);
        assertTrue(gameTestObj.placeTile(new CoordinatesStub()));
    }

    /**
     * Test will return normally if gameTestObj.placeTile() return false
     */
    @Test
    public void testPlaceTileIfTrue() {
        gameTestObj.setMap(mapStub1);
        gameTestObj.setCurrentTile(tileStub);
        assertFalse(gameTestObj.placeTile(new CoordinatesStub()));
    }

    /**
     * Test will return normally if gameTestObj.checkWin() return true
     */
    @Test
    public void testCheckWinIfTrue() {
        gameTestObj.setWinThreshold(0);
        assertTrue(gameTestObj.checkWin());
    }

    /**
     * Test will return normally if gameTestObj.checkWin() return false
     */
    @Test
    public void testCheckWinIfTrue1() {
        gameTestObj.setWinThreshold(1);
        gameTestObj.setMap(mapStub1);
        assertFalse(gameTestObj.checkWin());
    }

    /**
     * Test will return normally if gameTestObj.checkWin() return false
     */
    @Test
    public void testCheckWinIfFalse() {
        gameTestObj.setWinThreshold(1);
        assertFalse(gameTestObj.checkWin());
    }

    /**
     * Test will return normally if gameTestObj.noTiles() return true
     */
    @Test
    public void testNoTilesTrue() {
        shuffledListforTest.clear();
        assertTrue(gameTestObj.noTiles());
    }

    /**
     * Test will return normally if gameTestObj.noTiles() return false
     */
    @Test
    public void testNoTilesFalse() {
        assertFalse(gameTestObj.noTiles());
    }

    /**
     * Test will return normally if gameTestObj.canZombieMove() return false
     */
    @Test
    public void testCanZombieMoveIfTrue() {
        assertFalse(gameTestObj.canZombieMove(new ZombieStub1()));
    }

    /**
     * Test will return normally if gameTestObj.canZombieMove() return false
     */
    @Test
    public void testCanZombieMoveIfFalse() {
        mapStub.setField(new FieldStub());
        gameTestObj.setMap(mapStub);
        assertFalse(gameTestObj.canZombieMove(zombieStub));
    }
}
