package jzi.model.map;

/**
 * Defines the position of a field on a tile.
 * 
 * @author Maibot guest024
 * 
 */
public interface IFieldPosition extends java.io.Serializable {
    /**
     * Gets the new position of a field if its tile is rotated to the right.
     * 
     * @return new field position
     */
    IFieldPosition rotateRightPosition();

    /**
     * Gets the new position of a field if its tile is rotated to the left.
     * 
     * @return new field position
     */
    IFieldPosition rotateLeftPosition();

    /**
     * Gets the coordinates of a field relative to its tile.
     * 
     * @return relative field coordinates
     */
    ICoordinates getCoordinates();
}
