package jzi.model.map;

/**
 * Interface for all directions.
 * 
 * @author Buddy Jonte
 * 
 */
public interface IDirection extends java.io.Serializable {
    /**
     * Gets the resulting direction after rotating to the left.
     * 
     * @return rotated direction
     */
    IDirection rotateLeft();

    /**
     * Gets the resulting direction after rotating to the right.
     * 
     * @return rotated direction
     */
    IDirection rotateRight();

    /**
     * Gets the opposite direction.
     * 
     * @return opposite direction
     */
    IDirection getOpposite();

    /**
     * Gets the field position at the edge of a tile in this direction.
     * 
     * @return edge field position in given direction
     */
    IFieldPosition getField();
}
