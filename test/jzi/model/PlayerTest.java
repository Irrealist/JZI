package jzi.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    /**
     * object of the class which is to be tested
     */
    private static Player playerTestObject;
    /**
     * Executed before each test,set up the test object to the initial state all
     * tests assume it to be in.
     */
    @Before
    public void setUp() throws Exception {
        playerTestObject = new Player();
    }
    /**
     * Test if the number of times the player was revived has been increased.
     */
    @Test
    public void testRevivedNumber() {
        int beforeIncrease = playerTestObject.getRevives();
        playerTestObject.revive();
        int afterIncrease = playerTestObject.getRevives();
        assertTrue("number of times the player was revived has not been changed ",
                beforeIncrease != afterIncrease);
    }
    /**
     * Test if kill points have been deducted after player was revived,when points is
     * greater than 5
     */
    @Test
    public void testRevivedPenalty() {
        playerTestObject.setPoints(6);
        int expected = playerTestObject.getPoints()-5;//should be 1 now
        playerTestObject.revive();
        int actual = playerTestObject.getPoints();
        assertTrue("points should equal 1", expected == actual);
    }
    
    

}
