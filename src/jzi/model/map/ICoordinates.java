package jzi.model.map;

/**
 * Interface for coordinates on the map.
 * 
 * @author Buddy Jonte
 * 
 */
public interface ICoordinates extends java.io.Serializable {
    
    /**
     * Gets the coordinates to the left.
     * 
     * @return left coordinates
     */
    ICoordinates getLeft();
    
    /**
     * Gets the coordinates to the right.
     * 
     * @return right coordinates
     */
    ICoordinates getRight();
    
    /**
     * Gets the coordinates to the top.
     * 
     * @return up coordinates
     */
    ICoordinates getUp();

    /**
     * Gets the coordinates to the bottom.
     * 
     * @return down coordinates
     */
    ICoordinates getDown();
    
    /**
     * Gets coordinates in the given direction.
     * 
     * @param dir
     *            direction to get coordinates in
     * @return coordinates in direction dir
     */
    ICoordinates getDir(IDirection dir);
    
    /**
     * Translates x and y components.
     * 
     * @param dx
     *            value to translate x by
     * @param dy
     *            value to translate y by
     * @return translated coordinates
     */
    ICoordinates translate(int dx, int dy);
    
    /**
     * Adds other coordinates.
     * 
     * @param coord
     *            coordinates to add
     * @return new coordinates resulting from addition
     */
    ICoordinates add(ICoordinates coord);
    
    /**
     * Gets the x component.
     * 
     * @return x component
     */
    int getX();

    /**
     * Gets the y component.
     * 
     * @return y component
     */
    int getY();

    /**
     * Converts global field coordinates to tile coordinates.
     * 
     * @return tile coordinates
     */
    ICoordinates toTile();

    /**
     * Converts global field coordinates to relative field coordinates.
     * 
     * @return relative field coordinates
     */
    ICoordinates toRelativeField();
    
    /**
     * Converts tile coordinates to global field coordinates.
     * 
     * @return global field coordinates
     */
    ICoordinates toField();
}
