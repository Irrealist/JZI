package jzi.model;

import static org.junit.Assert.*;
import jzi.model.entities.Zombie;

import org.junit.Before;
import org.junit.Test;

/**
 * Black-Box-Test for the zombie class class in src.jzi.model
 * 
 * @author MaiBot guest24
 */
public class ZombieTest {
    /**
     * object of the class which is to be tested
     */
    private static Zombie zombieTestObjekt;
    /**
     * Executed before each test,set up the test object to the initial state all
     * tests assume it to be in.
     */
    @Before
    public void setUp() throws Exception {
        zombieTestObjekt = new Zombie();
    }
    /**
     * Test if the number of steps is reset the default value (1)
     */
    @Test
    public void testResetSteps() {
       assertTrue("steps should be reset to 1",1.== zombieTestObjekt.getSteps());
    }

}
