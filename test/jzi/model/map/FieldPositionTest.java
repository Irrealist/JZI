package jzi.model.map;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Black-Box-Test for the field position enumeration class in src.jzi.model.map.
 * 
 * @author MaiBot guest24
 */
public class FieldPositionTest {
    /**
     * Test if the new position of the top left field is top right 
     * after being rotated to the right.
     */
    @Test
    public void testRotateRightPositionTL() {
        IFieldPosition topRight = 
                FieldPosition.TOPLEFT.rotateRightPosition();
        assertNotSame(topRight, FieldPosition.TOPLEFT);
        assertEquals(topRight, FieldPosition.TOPRIGHT);
    }
    /**
     * Test if the new position of the top left field is bottom left 
     * after being rotated to the left.
     */
    @Test
    public void testRotateLeftPositionTL() {
        IFieldPosition bottomLeft = 
                FieldPosition.TOPLEFT.rotateLeftPosition();
        assertNotSame(bottomLeft, FieldPosition.TOPLEFT);
        assertEquals(bottomLeft, FieldPosition.BOTTOMLEFT);
    }
    
    /**
     * Test if the new position of the top field is right 
     * after being rotated to the right.
     */
    @Test
    public void testRotateRightPositionT() {
        IFieldPosition right = 
                FieldPosition.TOP.rotateRightPosition();
        assertNotSame(right, FieldPosition.TOP);
        assertEquals(right, FieldPosition.RIGHT);
    }
    /**
     * Test if the new position of the top field is left 
     * after being rotated to the left.
     */
    @Test
    public void testRotateLeftPositionT() {
        IFieldPosition left = 
                FieldPosition.TOP.rotateLeftPosition();
        assertNotSame(left, FieldPosition.TOP);
        assertEquals(left, FieldPosition.LEFT);
    }
    
    /**
     * Test if the new position of the top right field is bottom right 
     * after being rotated to the right.
     */
    @Test
    public void testRotateRightPositionTR() {
        IFieldPosition bottomRight = 
                FieldPosition.TOPRIGHT.rotateRightPosition();
        assertNotSame(bottomRight, FieldPosition.TOPRIGHT);
        assertEquals(bottomRight, FieldPosition.BOTTOMRIGHT);
    }
    /**
     * Test if the new position of the top right field is top left 
     * after being rotated to the left.
     */
    @Test
    public void testRotateLeftPositionTR() {
        IFieldPosition topLeft = 
                FieldPosition.TOPRIGHT.rotateLeftPosition();
        assertNotSame(topLeft, FieldPosition.TOPRIGHT);
        assertEquals(topLeft, FieldPosition.TOPLEFT);
    }
    
    /**
     * Test if the new position of the left field is top
     * after being rotated to the right.
     */
    @Test
    public void testRotateRightPositionL() {
        IFieldPosition top = 
                FieldPosition.LEFT.rotateRightPosition();
        assertNotSame(top, FieldPosition.LEFT);
        assertEquals(top, FieldPosition.TOP);
    }
    /**
     * Test if the new position of the left field is bottom 
     * after being rotated to the left.
     */
    @Test
    public void testRotateLeftPositionL() {
        IFieldPosition bottom = 
                FieldPosition.LEFT.rotateLeftPosition();
        assertNotSame(bottom, FieldPosition.LEFT);
        assertEquals(bottom, FieldPosition.BOTTOM);
    }
    
    /**
     * Test if the new position of the center field is center 
     * after being rotated to the right.
     */
    @Test
    public void testRotateRightPositionC() {
        IFieldPosition center = 
                FieldPosition.CENTER.rotateRightPosition();
        assertNotSame(center, FieldPosition.TOPLEFT);
        assertEquals(center, FieldPosition.CENTER);
    }
    /**
     * Test if the new position of the center field is center 
     * after being rotated to the left.
     */
    @Test
    public void testRotateLeftPositionC() {
        IFieldPosition center = 
                FieldPosition.CENTER.rotateLeftPosition();
        assertNotSame(center, FieldPosition.TOPLEFT);
        assertEquals(center, FieldPosition.CENTER);
    }
    
    /**
     * Test if the new position of the right field is bottom 
     * after being rotated to the right.
     */
    @Test
    public void testRotateRightPositionR() {
        IFieldPosition bottom = 
                FieldPosition.RIGHT.rotateRightPosition();
        assertNotSame(bottom, FieldPosition.RIGHT);
        assertEquals(bottom, FieldPosition.BOTTOM);
    }
    /**
     * Test if the new position of the right field is top 
     * after being rotated to the left.
     */
    @Test
    public void testRotateLeftPositionR() {
        IFieldPosition top = 
                FieldPosition.RIGHT.rotateLeftPosition();
        assertNotSame(top, FieldPosition.RIGHT);
        assertEquals(top, FieldPosition.TOP);
    }
    
    /**
     * Test if the new position of the bottom left field is top left
     * after being rotated to the right.
     */
    @Test
    public void testRotateRightPositionBL() {
        IFieldPosition topLeft = 
                FieldPosition.BOTTOMLEFT.rotateRightPosition();
        assertNotSame(topLeft, FieldPosition.BOTTOMLEFT);
        assertEquals(topLeft, FieldPosition.TOPLEFT);
    }
    /**
     * Test if the new position of the bottom left field is bottom right 
     * after being rotated to the left.
     */
    @Test
    public void testRotateLeftPositionBL() {
        IFieldPosition bottomRight = 
                FieldPosition.BOTTOMLEFT.rotateLeftPosition();
        assertNotSame(bottomRight, FieldPosition.BOTTOMLEFT);
        assertEquals(bottomRight, FieldPosition.BOTTOMRIGHT);
    }
    
    /**
     * Test if the new position of the bottom field is left
     * after being rotated to the right.
     */
    @Test
    public void testRotateRightPositionB() {
        IFieldPosition left = 
                FieldPosition.BOTTOM.rotateRightPosition();
        assertNotSame(left, FieldPosition.BOTTOM);
        assertEquals(left, FieldPosition.LEFT);
    }
    /**
     * Test if the new position of the bottom field is right 
     * after being rotated to the left.
     */
    @Test
    public void testRotateLeftPositionB() {
        IFieldPosition right = 
                FieldPosition.BOTTOM.rotateLeftPosition();
        assertNotSame(right, FieldPosition.BOTTOM);
        assertEquals(right, FieldPosition.RIGHT);
    }
    
    /**
     * Test if the new position of the bottom right field is bottom left 
     * after being rotated to the right.
     */
    @Test
    public void testRotateRightPositionBR() {
        IFieldPosition bottomLeft = 
                FieldPosition.BOTTOMRIGHT.rotateRightPosition();
        assertNotSame(bottomLeft, FieldPosition.BOTTOMRIGHT);
        assertEquals(bottomLeft, FieldPosition.BOTTOMLEFT);
    }
    /**
     * Test if the new position of the bottom right field is top right 
     * after being rotated to the left.
     */
    @Test
    public void testRotateLeftPositionBR() {
        IFieldPosition topRight = 
                FieldPosition.BOTTOMRIGHT.rotateLeftPosition();
        assertNotSame(topRight, FieldPosition.BOTTOMRIGHT);
        assertEquals(topRight, FieldPosition.TOPRIGHT);
    }
}
