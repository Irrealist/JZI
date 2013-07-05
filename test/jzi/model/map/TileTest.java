package jzi.model.map;

import static org.junit.Assert.*;
import jzi.model.map.IField;
import jzi.model.map.ITile;
import jzi.model.map.Tile;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;

public class TileTest {
    private static FieldStub[][] manualRotationMatrix;
    private static FieldStub topleft;
    private static FieldStub notImportantField;
    private static ITile testTile;
    
    /**
     * Methods with the annotation 'BeforeClass' are executed once before 
     * the first of the series of tests.External resources that are used 
     * by all tests should be initialized here.
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("@BeforeClass:Test starts");
     
        topleft = new FieldStub();
        //topleft.setPosition("topleft");
        notImportantField = new FieldStub();
        //notImportantField.setPosition("Null");
    }
    
    /**
     * Methods with the annotation 'AfterClass' are executed once after the
     * last test has been run.Resources used by the tests that need to be released
     * such as streams etc. should be set free here.
     * In our test there is nothing to be done.
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("@AfterClass:Test ends");
    }
    
    /**
     * Methods with the annotation 'Before' are executed before every test.
     * The test object should be brought to the initial state all tests
     * assume it to be in.
     */
    @Before
    public void setUp() throws Exception {
        System.out.println("@Before:Test object initialised");
       
        testTile = new Tile();
        testTile.setField(topleft, 0, 0);
        testTile.setField(notImportantField, 0, 1);
        testTile.setField(notImportantField, 0, 2);
        
        for(int i = 1;i <= 2;i++){
            for(int j = 0;j <= 2;j++){
                testTile.setField(notImportantField, i, j);
            }
        }
    }

    /**
     * Methods with the annotation 'After' are executed after every test.
     */
    @After
    public void tearDown() throws Exception {
        System.out.println("@After:Executed after every test");
    }
    
    /**
     * The list of fields on a tile is implemented as a 3x3 matrix.
     * We expect top left field at [0][2] after one rotation to the right.
     * The list of fields of testTile is rotated to the right one time
     * with testTile.rightRotation().After that the position of the expectedField
     * is checked against the position of the actualField with assertEquals(message,
     * expected,actual).
     * The assert method takes as an extra argument a message that is displayed
     * when the test fails.
     */
    @Test
    public void testRightRotation() {
        System.out.println("@Test:testRightRotation()");
        manualRotationMatrix = new FieldStub[][]{
                {notImportantField,notImportantField,topleft},
                {notImportantField,notImportantField,notImportantField},
                {notImportantField,notImportantField,notImportantField}
        };
        FieldStub expectedField = manualRotationMatrix[0][2];
        
        testTile.rightRotation();
        IField actualField = testTile.getField(0, 2);
        
        assertEquals("top left field should be on top right ",
                expectedField.getPosition(),actualField.getPosition());
        
    }
    
    /**
     * The list of fields on a tile is implemented as a 3x3 matrix.
     * We expect top left field at [2][0] after one rotation to the left.
     * The list of fields of testTile is rotated to the left one time
     * with testTile.leftRotation().After that the position of the expectedField
     * is checked against the position of the actualField with assertEquals(message,
     * expected,actual).
     * The assert method takes as an extra argument a message that is displayed
     * when the test fails.
     */
    @Test
    public void testLeftRotation() {
        System.out.println("@Test:testRightRotation()");
        manualRotationMatrix = new FieldStub[][]{
                {notImportantField,notImportantField,notImportantField},
                {notImportantField,notImportantField,notImportantField},
                {topleft,notImportantField,notImportantField}
        };
        FieldStub expectedField = manualRotationMatrix[2][0];
        testTile.leftRotation();
        IField actualField = testTile.getField(2, 0);
        assertEquals("top left field should be on bottom left ",
                expectedField.getPosition(),actualField.getPosition());
        
    }

}
