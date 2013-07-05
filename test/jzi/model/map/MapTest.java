package jzi.model.map;

import static org.junit.Assert.*;

import java.util.HashMap;

import jzi.model.map.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Black-Box-Test for the map class in src.jzi.model.map
 * 
 * @author MaiBot guest24
 */
public class MapTest {
    /**
     * object of the class which is to be tested
     */
    private static Map mapTestObject;
    /**
     * Stubs needed by tests
     */
    private static TileStub tileStub;
    private static CoordinatesStub coordStub;
    /**
     * Hash map filled with TileStub and CoordinatesStub
     */
    private HashMap<ICoordinates, ITile> tilesHaMa = new HashMap<>();
    /**
     * Executed once before the first of the series of tests,external resources
     * that are use by tests can be initialized here.
     */
    @BeforeClass
    public static void setUpBeforeClasse() throws Exception {
        tileStub = new TileStub();
        coordStub = new CoordinatesStub();
    }
    
    /**
     * Executed before each test,set up the test object to the initial state all
     * tests assume it to be in.
     */
    @Before
    public void setUp() throws Exception {
        mapTestObject = new Map();
        mapTestObject.setHashMap(tilesHaMa);
        tilesHaMa.put(coordStub, tileStub);
    }
    /**
     * Test returns successfully if addTile returns false because the hash map isn't null
     */
    @Test
    public void testAddTileIfTrue() {
        assertFalse( mapTestObject.addTile(coordStub, tileStub));
    }
    /**
     * Test returns successfully if addTile returns false because checkTile() returns false
     */
    @Test
    public void testAddTileIfTrue1() {
        assertFalse( mapTestObject.addTile(new Coordinates(6, 6), tileStub));
    }
    /**
     * Test returns successfully if addTile returns true 
     */
    @Test
    public void testAddTileIfFalse() {
        tilesHaMa.clear();
        assertTrue( mapTestObject.addTile(coordStub, tileStub));
    }
    /**
     * Test returns successfully if checkTile() returns false
     */
    @Test
    public void testCheckTile() {
        assertFalse(mapTestObject.checkTile(tileStub));
    }
    
    /**
     * Test returns successfully if checkTile() returns false
     */
    @Test
    public void testCheckTile1() {
        tilesHaMa.put(new Coordinates(0, 0), tileStub);
        assertFalse(mapTestObject.checkTile(tileStub));
    }
    
    /**
     * Test returns successfully if checkTile() returns true
     */
    @Test
    public void testCheckTile2() {
        tilesHaMa.clear();
        assertTrue(mapTestObject.checkTile(tileStub));
    }
    
    /**
     * Test returns successfully if checkTile(para1) returns true
     */
    @Test
    public void testCheckTileTwo() {
        tilesHaMa.clear();
        assertTrue(mapTestObject.checkTile(coordStub, tileStub));
    }
    
    /**
     * Test returns successfully if checkTile(para1) returns false
     */
    @Test
    public void testCheckTileTwo1() {
        assertFalse(mapTestObject.checkTile(new  Coordinates(6, 6), tileStub));
    }
    
    /**
     * Test returns successfully if checkNeighbor(para1) returns false
     */
    @Test
    public void testCheckNeighbor() {
        assertFalse(mapTestObject.checkNeighbor(new FieldStub(),new FieldStub1()));
    }
    
}
