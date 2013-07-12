package jzi.model.map;


/**
 * Defines a Type of Tile.
 * 
 * @author Buddy Jonte
 */
public class TileType implements ITileType {
    /**
     * UID for serialization.
     */
    private static final long serialVersionUID = 3140028176247744136L;
    /**
     * The name of this tile type.
     */
    private String name;
    /**
     * Array of the {@link IField}s on this tile.
     */
    private IField[][] fieldsMatrix = new IField[Tile.HEIGHT_FIELDS][Tile.WIDTH_FIELDS];
    /**
     * Number of times this tile type should be in the tile list.
     */
    private int count;
    /**
     * Number of zombies on this type of tile.
     */
    private int zombieCount;
    /**
     * Number of lives of this type of tile.
     */
    private int lifeCount;
    /**
     * Amount of ammunition on this type of tile.
     */
    private int ammoCount;
    /**
     * File name for this tile type's image.
     */
    private String fileName;

    /**
     * Default constructor.
     */
    public TileType() {
    }

    /**
     * Copy constructor. Copies another {@link ITileType} object.
     * 
     * @param tileType
     *            {@link ITileType} object to copy.
     */
    public TileType(final ITileType tileType) {
        name = tileType.getName();
        count = tileType.getCount();
        zombieCount = tileType.getZombieCount();
        lifeCount = tileType.getLifeCount();
        ammoCount = tileType.getAmmoCount();
        fileName = tileType.getFileName();

        for (int row = 0; row < Tile.HEIGHT_FIELDS; row++) {
            for (int col = 0; col < Tile.WIDTH_FIELDS; col++) {
                fieldsMatrix[row][col] = tileType.getField(row, col);
            }
        }
    }

    /**
     * Gets this tile type's name.
     * 
     * @return tile type name
     */
    @Override
    public final String getName() {
        return name;
    }

    /**
     * Sets this tile type's name.
     * 
     * @param name
     *            new tile type name
     */
    @Override
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the number of times this tile type should be in the game.
     * 
     * @return tile type count
     */
    @Override
    public final int getCount() {
        return count;
    }

    /**
     * Sets the number of times this tile type should be in the game.
     * 
     * @param count
     *            new tile type count
     */
    @Override
    public final void setCount(final int count) {
        this.count = count;
    }

    /**
     * Gets the number of zombies this tile type should have.
     * 
     * @return zombie count
     */
    @Override
    public final int getZombieCount() {
        return zombieCount;
    }

    /**
     * Sets the number of zombies this tile type should have.
     * 
     * @param zombieCount
     *            new zombie count
     */
    @Override
    public final void setZombieCount(final int zombieCount) {
        this.zombieCount = zombieCount;
    }

    /**
     * Gets the number of life points this tile type should have.
     * 
     * @return life point count
     */
    @Override
    public final int getLifeCount() {
        return lifeCount;
    }

    /**
     * Sets the number of life points this tile type should have.
     * 
     * @param lifeCount
     *            new life point count
     */
    @Override
    public final void setLifeCount(final int lifeCount) {
        this.lifeCount = lifeCount;
    }

    /**
     * Gets the amount of ammunition this tile type should have.
     * 
     * @return ammunition count
     */
    @Override
    public final int getAmmoCount() {
        return ammoCount;
    }

    /**
     * Sets the amount of ammunition this tile type should have.
     * 
     * @param ammoCount
     *            new ammunition count
     */
    @Override
    public final void setAmmoCount(final int ammoCount) {
        this.ammoCount = ammoCount;
    }

    /**
     * Gets a field on this tile type.
     * 
     * @param rowIndex
     *            row index of the field
     * @param columnIndex
     *            column index of the field
     * @return {@IField} at given position
     */
    @Override
    public final IField getField(final int rowIndex, final int columnIndex) {
        return fieldsMatrix[rowIndex][columnIndex];
    }

    /**
     * Sets a field on this tile type.
     * 
     * @param rowIndex
     *            row index of the field to be set
     * @param columnIndex
     *            column index of the field to be set
     * @param field
     *            new {@link IField} to be set
     */
    @Override
    public final void setField(final int rowIndex, final int columnIndex,
            IField field) {
        fieldsMatrix[rowIndex][columnIndex] = field;
    }

    /**
     * Creates a new tile with this tile type.
     * 
     * @return new {@link ITile} with this tile type
     */
    public final ITile createTile() {
        ITile tile = new Tile(this);

        for (int row = 0; row < Tile.HEIGHT_FIELDS; row++) {
            for (int col = 0; col < Tile.WIDTH_FIELDS; col++) {
                tile.setField(new Field(getField(row, col)), row, col);
            }
        }

        return tile;
    }

    /**
     * Gets the image file name for this tile type.
     * 
     * @return image file name
     */
    @Override
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the image file name for this tile type.
     * 
     * @param fileName
     *            new file name
     */
    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
