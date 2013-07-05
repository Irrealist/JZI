package jzi.model.map;

/**
 * Interface for tiles in the game.
 * 
 * @author Maibot guest24, Buddy Jonte
 * 
 */
public interface ITile extends java.io.Serializable {
    
    /**
     * Getter for field at specified index position.
     * 
     * @param rowIndex
     *            - row of fieldsMatrix
     * @param columnIndex
     *            - column of fieldsMatrix
     * @return field object at specified position
     */
    IField getField(int rowIndex, int columnIndex);
    
    /**
     * Gets the field at the given field position.
     * 
     * @param pos
     *            position of the field on the tile
     * @return field at given position
     */
    IField getField(IFieldPosition pos);
    
    /**
     * Gets the field at the given relative coordinates.
     * 
     * @param coord
     *            coordinates relative to the tile
     * @return field at given coordinates
     */
    IField getField(ICoordinates coord);
    
    /**
     * Setter for field at specified position.
     * 
     * @param field
     *            - this field is to be set
     * @param rowIndex
     *            - set the field at this row
     * @param columnIndex
     *            - set the field at this column
     */
    void setField(IField field, int rowIndex, int columnIndex);
    
    /**
     * Rotates the tile to the right.
     */
    void rightRotation();

    /**
     * Rotates the tile to the left.
     */
    void leftRotation();

    /**
     * Makes the tile ready for placing.
     */
    void setUp();

    /**
     * Gets the tile's coordinates.
     * 
     * @return tile coordinates
     */
    ICoordinates getCoordinates();

    /**
     * Sets the tile's coordinates.
     * 
     * @param coords
     *            new coordinates
     */
    void setCoordinates(ICoordinates coords);

    /**
     * Checks whether the tile can be left in the given direction.
     * 
     * @param dir
     *            direction to check
     * @return true if the tile can be left in direction dir
     */
    boolean hasDir(IDirection dir);

    /**
     * Gets the tile's tile type.
     * 
     * @return tile type
     */
    ITileType getTileType();

    /**
     * Sets the tile's tile type.
     * 
     * @param tileType
     *            tile type
     */
    void setTileType(ITileType tileType);

    /**
     * Gets the tile's current rotation.
     * 
     * @return rotation expressed as a direction
     */
    IDirection getRotation();

    boolean isFinalized();

    void setFinalized();
}
