package jzi.model.map;

import java.util.Random;

import jzi.model.entities.Zombie;

/**
 * Tile class stores tile information, after the XML document is parsed.
 * 
 * @author Buddy Jonte
 */
public class Tile implements ITile {
	/**
	 * Size of a Tile.
	 */
	public static final int TILE_SIZE = 800;
	/**
	 * Width of a tile in fields.
	 */
	public static final int WIDTH_FIELDS = 3;
	/**
	 * Height of a tile in fields.
	 */
	public static final int HEIGHT_FIELDS = 3;
	/**
	 * UID for serialization.
	 */
	private static final long serialVersionUID = -6357921233674806086L;

	/**
	 * Type of this Tile.
	 */
	private ITileType tileType;

	/**
	 * Fields on a tile as a 3x3 Matrix.
	 */
	private IField[][] fieldsMatrix = new IField[HEIGHT_FIELDS][WIDTH_FIELDS];

	/**
	 * Coordinates of the tile on the game map.
	 */
	private ICoordinates coords;

	/**
	 * Current rotation of the tile. Default direction is UP.
	 */
	private IDirection rotation;

	/**
	 * Default constructor; sets default rotation.
	 */
	public Tile() {
		rotation = Direction.UP;
	}

	/**
	 * Constructor with {@link ITileType}.
	 * 
	 * @param tileType
	 *            tile type for the tile
	 */
	public Tile(final ITileType tileType) {
		this();
		this.setTileType(tileType);
	}

	/**
	 * Getter for field at specified index position.
	 * 
	 * @param rowIndex
	 *            row of fieldsMatrix
	 * @param columnIndex
	 *            column of fieldsMatrix
	 * @return field object at specified position
	 */
	@Override
	public final IField getField(final int rowIndex, final int columnIndex) {
		return fieldsMatrix[rowIndex][columnIndex];
	}

	/**
	 * Gets the tile's field at the specified field position.
	 * 
	 * @param pos
	 *            field position to get field at
	 * @return field at given position
	 */
	@Override
	public final IField getField(final IFieldPosition pos) {
		return getField(pos.getCoordinates());
	}

	/**
	 * Get this tile's Field at a relative coordinate.
	 * 
	 * @param coord
	 *            relative Field coordinate
	 * @return Field at specified coordinate
	 */
	@Override
	public final IField getField(final ICoordinates coord) {
		// row = x, column = y
		return fieldsMatrix[coord.getY()][coord.getX()];
	}

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
	@Override
	public final void setField(final IField field, final int rowIndex,
			final int columnIndex) {
		fieldsMatrix[rowIndex][columnIndex] = field;
	}

	/**
	 * Rotates the field matrix and each field to the right.
	 */
	@Override
	public final void rightRotation() {
		int rowLength = WIDTH_FIELDS;
		int colLength = HEIGHT_FIELDS;
		IField[][] tmpFieldMatrix = new IField[rowLength][colLength];

		for (int row = 0; row <= rowLength - 1; row++) {
			for (int column = 0; column <= colLength - 1; column++) {
				tmpFieldMatrix[row][column] = this.fieldsMatrix[2 - column][row];
			}
		}

		for (int row = 0; row <= rowLength - 1; row++) {
			for (int column = 0; column <= colLength - 1; column++) {
				this.fieldsMatrix[row][column] = tmpFieldMatrix[row][column];
			}
		}

		for (int i = 0; i < (rowLength * colLength); i++) {
			IField field = fieldsMatrix[i / WIDTH_FIELDS][i % WIDTH_FIELDS];
			field.rotateRight();
			fieldsMatrix[i / WIDTH_FIELDS][i % WIDTH_FIELDS] = field;
		}

		rotation = rotation.rotateRight();
	}

	/**
	 * Rotates the field matrix and each field to the left.
	 */
	@Override
	public final void leftRotation() {
		int rowLength = WIDTH_FIELDS;
		int colLength = HEIGHT_FIELDS;
		IField[][] tmpFieldMatrix = new IField[rowLength][colLength];

		for (int row = 0; row <= rowLength - 1; row++) {
			for (int column = 0; column <= colLength - 1; column++) {
				tmpFieldMatrix[row][column] = this.fieldsMatrix[column][2 - row];
			}
		}

		for (int row = 0; row <= rowLength - 1; row++) {
			for (int column = 0; column <= colLength - 1; column++) {
				this.fieldsMatrix[row][column] = tmpFieldMatrix[row][column];
			}
		}

		for (int i = 0; i < (rowLength * colLength); i++) {
			IField field = fieldsMatrix[i / WIDTH_FIELDS][i % WIDTH_FIELDS];
			field.rotateLeft();
			fieldsMatrix[i / WIDTH_FIELDS][i % WIDTH_FIELDS] = field;
		}

		rotation = rotation.rotateLeft();
	}

	/**
	 * If the tile is a 'no-name' tile, then place zombies at each road link.
	 */
	private void placeZombieAtRoadLink() {
		for (int i = 1; i < 8; i += 2) {
			IField roadLink = fieldsMatrix[i / WIDTH_FIELDS][i % WIDTH_FIELDS];

			if (roadLink.getType().equals("street")) {
				roadLink.setZombie(new Zombie());
			}
		}
	}

	/**
	 * Randomly places the given number of zombies on the tile.
	 * 
	 * @param count
	 *            number of zombies to place
	 */
	private void placeZombies(int count) {
		Random rand = new Random();

		while (count > 0) {
			int pos = rand.nextInt(WIDTH_FIELDS * HEIGHT_FIELDS);
			IField field = fieldsMatrix[pos / WIDTH_FIELDS][pos % WIDTH_FIELDS];

			if (field.getType().equals("building") && field.getZombie() == null) {
				field.setZombie(new Zombie());
				count--;
			}
		}
	}

	/**
	 * Randomly places the given number of lives on the tile.
	 * 
	 * @param count
	 *            number of lives to place
	 */
	private void placeLives(int count) {
		Random rand = new Random();

		while (count > 0) {
			int pos = rand.nextInt(WIDTH_FIELDS * HEIGHT_FIELDS);
			IField field = fieldsMatrix[pos / WIDTH_FIELDS][pos % WIDTH_FIELDS];

			if (field.getType().equals("building") && !field.hasLife()
					&& !field.hasAmmo()) {
				field.setLife(true);
				count--;
			}
		}
	}

	/**
	 * Randomly places the given amount of ammunition on the tile.
	 * 
	 * @param count
	 *            amount of ammunition to place
	 */
	private void placeAmmo(int count) {
		Random rand = new Random();

		while (count > 0) {
			int pos = rand.nextInt(WIDTH_FIELDS * HEIGHT_FIELDS);
			IField field = fieldsMatrix[pos / WIDTH_FIELDS][pos % WIDTH_FIELDS];

			if (field.getType().equals("building") && !field.hasLife()
					&& !field.hasAmmo()) {
				field.setAmmo(true);
				count--;
			}
		}
	}

	/**
	 * Calls relevant methods to place life points, ammunition and zombies on
	 * the tile.
	 */
	@Override
	public final void setUp() {
		if (tileType.getName().equals("")) {
			placeZombieAtRoadLink();
		} else if (tileType.getName().equals("Helicopter")) {
			for (int x = 0; x < Tile.WIDTH_FIELDS; x++) {
				for (int y = 0; y < Tile.WIDTH_FIELDS; y++) {
					getField(y, x).setZombie(new Zombie());
				}
			}
		} else {
			placeZombies(tileType.getZombieCount());
			placeLives(tileType.getLifeCount());
			placeAmmo(tileType.getAmmoCount());
		}
	}

	/**
	 * Gets the tile's coordinates.
	 * 
	 * @return tile coordinates
	 */
	@Override
	public final ICoordinates getCoordinates() {
		return coords;
	}

	/**
	 * Sets the tile's coordinates.
	 * 
	 * @param coords
	 *            new coordinates
	 */
	@Override
	public final void setCoordinates(final ICoordinates coords) {
		this.coords = coords;
	}

	/**
	 * Checks whether the tile can be left in the given direction. A tile can be
	 * left in a direction if the field at the relevant edge can be left in that
	 * direction.
	 * 
	 * @param dir
	 *            direction to check
	 * @return true if tile can be left in given direction
	 */
	@Override
	public boolean hasDir(IDirection dir) {
		return getField(dir.getField()).hasDir(dir);
	}

	/**
	 * Gets the tile's tile type.
	 * 
	 * @return tile's tile type
	 */
	@Override
	public final ITileType getTileType() {
		return tileType;
	}

	/**
	 * Sets the tile's tile type.
	 * 
	 * @param tileType
	 *            new tile type
	 */
	@Override
	public final void setTileType(final ITileType tileType) {
		this.tileType = tileType;
	}

	/**
	 * Gets the current rotation of the tile. Default rotation is UP.
	 * 
	 * @return rotation expressed as IDirection
	 */
	@Override
	public final IDirection getRotation() {
		return rotation;
	}
}
