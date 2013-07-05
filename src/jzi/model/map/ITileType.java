package jzi.model.map;

/**
 * Interface for a type of tile.
 * 
 * @author Buddy Jonte
 * 
 */
public interface ITileType extends java.io.Serializable {
    /**
     * Gets the tile type's name.
     * 
     * @return tile type name
     */
    String getName();

    /**
     * Sets the tile type's name.
     * 
     * @param name
     *            new name
     */
    void setName(String name);

    /**
     * Gets the number of times the tile type should be in the game.
     * 
     * @return tile type count
     */
    int getCount();

    /**
     * Sets the number of times the tile type should be in the game.
     * 
     * @param count
     *            new tile type count
     */
    void setCount(int count);

    /**
     * Gets the number of zombies that should be on a tile of this type.
     * 
     * @return zombie count
     */
    int getZombieCount();

    /**
     * Sets the number of zombies that should be on a tile of this type.
     * 
     * @param zombieCount
     *            new zombie count
     */
    void setZombieCount(int zombieCount);

    /**
     * Sets the number of life points that should be on a tile of this type.
     * 
     * @return life count
     */
    int getLifeCount();

    /**
     * Sets the number of life points that should be on a tile of this type.
     * 
     * @param lifeCount
     *            new life count
     */
    void setLifeCount(int lifeCount);

    /**
     * Gets the amount of ammunition that should be on a tile of this type.
     * 
     * @return ammunition count
     */
    int getAmmoCount();

    /**
     * Sets the amount of ammunition that should be on a tile of this type.
     * 
     * @param ammoCount
     *            new ammunition count
     */
    void setAmmoCount(int ammoCount);

    /**
     * Gets a field on this tile type.
     * 
     * @param rowIndex
     *            row index in the field matrix
     * @param columnIndex
     *            column index in the field matrix
     * @return field at given matrix position
     */
    IField getField(int rowIndex, int columnIndex);

    /**
     * Sets a field on this tile type.
     * 
     * @param rowIndex
     *            row index in the field matrix
     * @param columnIndex
     *            column index in the field matrix
     * @param field
     *            new field for given matrix position
     */
    void setField(int rowIndex, int columnIndex, IField field);

    /**
     * Creates a tile out of the tile type.
     * 
     * @return new tile
     */
    ITile createTile();

    /**
     * Gets the image file name of the tile type.
     * 
     * @return image file name
     */
    String getFileName();

    /**
     * Sets the image file name for the tile type.
     * 
     * @param fileName
     *            new image file name
     */
    void setFileName(String fileName);
}
