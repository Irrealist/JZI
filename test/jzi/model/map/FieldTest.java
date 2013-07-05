package jzi.model.map;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Black-Box-Test for the field class in src.jzi.model.map
 * 
 * @author MaiBot guest24
 */
public class FieldTest {
    /**
     * object of the class which is to be tested
     */
    private static Field fieldTestObject;
    /**
     * List filled with IDirection
     */
    private static List<IDirection> dirListOld;
    private static List<IDirection> dirListNew;
    /**
     * Executed once before the first of the series of tests,external resources
     * that are use by tests can be initialized here.
     */
    @BeforeClass
    public static void setUpBeforeClasse() throws Exception {
        dirListOld = new ArrayList<>();
        dirListNew = new ArrayList<>();
    }
    /**
     * Executed before each test,set up the test object to the initial state all
     * tests assume it to be in.
     */
    @Before
    public void setUp() throws Exception {
        fieldTestObject = new Field();
    }
    /**
     * Test if field's up direction is correctly rotate to the right.
     */
    @Test
    public void testRotateRight() {
        fieldTestObject.setUp(); 
        fieldTestObject.setPosition(jzi.model.map.FieldPosition.TOPLEFT);
        dirListOld = fieldTestObject.returnDirList();
        fieldTestObject.rotateRight();
        dirListNew = fieldTestObject.returnDirList();
        IDirection beforeRRight = dirListOld.get(0);
        IDirection afterRRight = dirListNew.get(0);
        assertNotSame(beforeRRight, afterRRight);
    }
    /**
     * Test if field's up direction is correctly rotate to the left.
     */
    @Test
    public void testRotateLeft() {
        fieldTestObject.setUp(); 
        fieldTestObject.setPosition(jzi.model.map.FieldPosition.TOPLEFT);
        dirListOld = fieldTestObject.returnDirList();
        fieldTestObject.rotateLeft();
        dirListNew = fieldTestObject.returnDirList();
        IDirection beforeRLeft = dirListOld.get(0);
        IDirection afterRLeft = dirListNew.get(0);
        assertNotSame(beforeRLeft, afterRLeft);
    }
}
