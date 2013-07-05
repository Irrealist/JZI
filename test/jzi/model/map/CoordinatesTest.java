package jzi.model.map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Black-Box-Test for the coordinates class in src.jzi.model.map
 * 
 * @author MaiBot guest24
 */
public class CoordinatesTest {
    /**
     * object of the class which is to be tested
     */
    private static Coordinates coordTestObject;
    /**
     * Executed before each test,set up the test object to the initial state all
     * tests assume it to be in.
     */
    @Before
    public void setUp() throws Exception {
        coordTestObject = new Coordinates(0, 0);
    }
    /**
     * Test if the x and y components of the coordinates has been translated.
     */
    @Test
    public void testTranslate() {
        int expected_X_Value = coordTestObject.getX()+3;//should be 3
        int expected_Y_Value = coordTestObject.getY()+3;//should be 3
        ICoordinates afterTranslate = coordTestObject.translate(3, 3);
        int actual_X_Value = afterTranslate.getX();
        int actual_Y_Value = afterTranslate.getY();
        assertTrue("x value should be 3 after translate", expected_X_Value == actual_X_Value);
        assertTrue("y value should be 3 after translate", expected_Y_Value == actual_Y_Value);
    }
    /**
     * Test if new coordinates are created by adding given coordinates
     */
    @Test
    public void testAdd() {
        ICoordinates givenCoord = new Coordinates(3, 3);
        int expected_X_Value = coordTestObject.getX()+givenCoord.getX();//0+3
        int expected_Y_Value = coordTestObject.getY()+givenCoord.getY();//0+3
        ICoordinates afterAdding = coordTestObject.add(givenCoord);
        int actual_X_Value = afterAdding.getX();
        int actual_Y_Value = afterAdding.getY();
        assertTrue("x value should be 3 after adding given coordinates", 
                expected_X_Value == actual_X_Value);
        assertTrue("y value should be 3 after adding given coordinates", 
                expected_Y_Value == actual_Y_Value);
    }
    /**
     * Test if global field coordinates is converted to tile coordinates
     * For x,y >= 0
     */
    @Test
    public void testToTileIfFalse() {
        int expected_X_Value = 3/3;
        int expected_Y_Value = 3/3;
        Coordinates coordTestObject1 = new Coordinates(2, 2);
        ICoordinates afterConvertion = coordTestObject1.toTile();
        int actual_X_Value = afterConvertion.getX();
        
        int actual_Y_Value = afterConvertion.getY();
      
        assertTrue("x=3/3 after converting field coordinatees to tile coordinates", 
                expected_X_Value == actual_X_Value);
        assertTrue("y=3/3 after converting field coordinatees to tile coordinates", 
                expected_Y_Value == actual_Y_Value);
        
    }
    /**
     * Test if global field coordinates is converted to tile coordinates
     * For x,y < 0
     */
    @Test
    public void testToTileIfTrue() {
        int expected_X_Value = -3/3;
        int expected_Y_Value = -3/3;
        Coordinates coordTestObject1 = new Coordinates(-2, -2);
        ICoordinates afterConvertion = coordTestObject1.toTile();
        int actual_X_Value = afterConvertion.getX();
        int actual_Y_Value = afterConvertion.getY();
      
        assertTrue("x=-1 after converting field coordinatees to tile coordinates", 
                expected_X_Value == actual_X_Value);
        assertTrue("y=-1 after converting field coordinatees to tile coordinates", 
                expected_Y_Value == actual_Y_Value);
        
    }
    /**
     * Test if global field coordinates is converted to coordinates relative to the field's
     * For x,y >= 0
     */
    @Test
    public void testToRelativeFieldPositiveNum() {
        int expected_X_Value = ((0 + 1) % 3 + 3) % 3;//equals 1
        int expected_Y_Value = ((0 + 1) % 3 + 3) % 3;//equals 1
        ICoordinates afterRFieldConversion = coordTestObject.toRelativeField();
        int actual_X_Value = afterRFieldConversion.getX();
        int actual_Y_Value = afterRFieldConversion.getY();
        
        assertTrue("x=1 after converting field coordinatees to relative field coordinates", 
                expected_X_Value == actual_X_Value);
        assertTrue("y=1 after converting field coordinatees to relative field coordinates", 
                expected_Y_Value == actual_Y_Value);
    }
    /**
     * Test if global field coordinates is converted to coordinates relative to the field's
     * For x,y < 0 
     */
    @Test
    public void testToRelativeFieldNegativeNum() {
        int expected_X_Value = ((-2 + 1) % 3 + 3) % 3;//equals 2
        int expected_Y_Value = ((-2 + 1) % 3 + 3) % 3;//equals 2
        ICoordinates coordTestObject2 = new Coordinates(-2, -2);
        ICoordinates afterRFieldConversion = coordTestObject2.toRelativeField();
        int actual_X_Value = afterRFieldConversion.getX();
        int actual_Y_Value = afterRFieldConversion.getY();
        
        assertTrue("x=2 after converting field coordinatees to relative field coordinates", 
                expected_X_Value == actual_X_Value);
        assertTrue("y=2 after converting field coordinatees to relative field coordinates", 
                expected_Y_Value == actual_Y_Value);
    }
    /**
     * Test if tile coordinates is converted to the global field coordinates 
     * of the tile's top left field.
     */
    @Test
    public void testToField() {
        ICoordinates afterToFieldConversion = coordTestObject.toField();
        assertTrue(-1.==afterToFieldConversion.getX());
        assertTrue(-1.==afterToFieldConversion.getY());
    }
    /**
     * Test the calculation of the distance between two Coordinates objects.
     */
    @Test
    public void testDistance() {
        ICoordinates compareObject = new Coordinates(2, 2);
        int dx = 0 - 2;
        int dy = 0 - 2;
        double expectedDistance = Math.sqrt(dx * dx + dy * dy);
        double actualDistance = Coordinates.distance(coordTestObject, compareObject);
        
        assertTrue(expectedDistance == actualDistance);
    }
}
