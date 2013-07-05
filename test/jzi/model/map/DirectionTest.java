package jzi.model.map;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Black-Box-Test for the direction enumeration class in src.jzi.model.map.
 * 
 * @author MaiBot guest24
 */
public class DirectionTest {
    
    /**
     * Test if the result equals LEFT when rotating UP to the left. 
     */
    @Test
    public final void testRotateLeftUP() {
        IDirection left = Direction.UP.rotateLeft();
        assertNotSame(left, Direction.RIGHT);
        assertEquals(left, Direction.LEFT);
    }
    
    /**
     * Test if the result equals RIGHT when rotating UP to the right. 
     */
    @Test
    public final void testRotateRightUP() {
        IDirection right = Direction.UP.rotateRight();
        assertNotSame(right, Direction.LEFT );
        assertEquals(right, Direction.RIGHT);
    }
    
    /**
     * Test if the result equals UP when rotating RIGHT to the left. 
     */
    @Test
    public final void testRotateLeftRIGHT() {
        IDirection up = Direction.RIGHT.rotateLeft();
        assertNotSame(up, Direction.DOWN);
        assertEquals(up, Direction.UP);
    }
    
    /**
     * Test if the result equals DOWN when rotating RIGHT to the right. 
     */
    @Test
    public final void testRotateRightRIGHT() {
        IDirection down = Direction.RIGHT.rotateRight();
        assertNotSame(down, Direction.UP );
        assertEquals(down, Direction.DOWN);
    }
    
    /**
     * Test if the result equals RIGHT when rotating DOWN to the left. 
     */
    @Test
    public final void testRotateLeftDOWN() {
        IDirection right = Direction.DOWN.rotateLeft();
        assertNotSame(right, Direction.DOWN);
        assertEquals(right, Direction.RIGHT);
    }
    
    /**
     * Test if the result equals LEFT when rotating DOWN to the right. 
     */
    @Test
    public final void testRotateRightDOWN() {
        IDirection left = Direction.DOWN.rotateRight();
        assertNotSame(left, Direction.UP );
        assertEquals(left, Direction.LEFT);
    }
    
    /**
     * Test if the result equals DOWN when rotating LEFT to the left.
     */
    @Test
    public final void testRotateLeftLEFT() {
        IDirection down = Direction.LEFT.rotateLeft();
        assertNotSame(down, Direction.LEFT);
        assertEquals(down, Direction.DOWN);
    }
    
    /**
     * Test if the result equals UP when rotating LEFT to the right. 
     */
    @Test
    public final void testRotateRightLEFT() {
        IDirection up = Direction.LEFT.rotateRight();
        assertNotSame(up, Direction.LEFT);
        assertEquals(up, Direction.UP);
    }

}
