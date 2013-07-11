package jzi.model.map;

import java.awt.geom.Point2D;

/**
 * Represents a point on the game map. Can be coordinates of a Tile, global
 * coordinates of a Field, or coordinates of a Field relative to its Tile.
 * 
 * @author Buddy Jonte
 * 
 */
public class Coordinates implements ICoordinates {
	/**
	 * UID used for serialization.
	 */
	private static final long serialVersionUID = -8643795678360505042L;
	/**
	 * X component of the coordinates.
	 */
	private int x;
	/**
	 * Y component of the coordinates.
	 */
	private int y;

	/**
	 * Constructs a Coordinates object with given x and y coordinates.
	 * 
	 * @param x
	 *            x component
	 * @param y
	 *            y component
	 */
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Converts a mouse position on the map to global Field coordinates.
	 * 
	 * @param point
	 *            - mouse position
	 * @return Coordinates of Field at given position
	 */
	public static Coordinates fieldFromPoint(final Point2D point) {
		int width = Field.FIELD_SIZE;
		int height = Field.FIELD_SIZE;
		int newx = (int) Math.floor(point.getX() / width) - 1;
		int newy = (int) Math.floor(point.getY() / height) - 1;

		return new Coordinates(newx, newy);
	}

	/**
	 * Converts a mouse position on the map to tile coordinates.
	 * 
	 * @param point
	 *            - mouse position
	 * @return Coordinates of Tile at given position
	 */
	public static Coordinates tileFromPoint(final Point2D point) {
		int width = Tile.TILE_SIZE;
		int height = Tile.TILE_SIZE;
		int newx = (int) Math.floor(point.getX() / width);
		int newy = (int) Math.floor(point.getY() / height);

		return new Coordinates(newx, newy);
	}

	/**
	 * Gets the coordinates to the left.
	 * 
	 * @return left coordinates
	 */
	@Override
	public final ICoordinates getLeft() {
		return translate(-1, 0);
	}

	/**
	 * Gets the coordinates to the right.
	 * 
	 * @return right coordinates
	 */
	@Override
	public final ICoordinates getRight() {
		return translate(1, 0);
	}

	/**
	 * Gets the coordinates going up.
	 * 
	 * @return up coordinates
	 */
	@Override
	public final ICoordinates getUp() {
		return translate(0, -1);
	}

	/**
	 * Gets the coordinates going down.
	 * 
	 * @return down coordinates
	 */
	@Override
	public final ICoordinates getDown() {
		return translate(0, 1);
	}

	/**
	 * Gets coordinates in the given direction.
	 * 
	 * @param dir
	 *            direction to get new coordinates in
	 * @return coordinates in given direction
	 */
	@Override
	public ICoordinates getDir(final IDirection dir) {
		if (dir.equals(Direction.LEFT)) {
			return getLeft();
		}

		if (dir.equals(Direction.RIGHT)) {
			return getRight();
		}

		if (dir.equals(Direction.UP)) {
			return getUp();
		}

		if (dir.equals(Direction.DOWN)) {
			return getDown();
		}

		return null;
	}

	/**
	 * Translates x and y components of the coordinates.
	 * 
	 * @param dx
	 *            value to translate x component by
	 * @param dy
	 *            value to translate y component by
	 * @return translated coordinates
	 */
	@Override
	public ICoordinates translate(final int dx, final int dy) {
		return new Coordinates(x + dx, y + dy);
	}

	/**
	 * Creates new coordinates by adding the given coordinates.
	 * 
	 * @param coord
	 *            coordinates to add
	 * @return new added coordinates
	 */
	@Override
	public ICoordinates add(final ICoordinates coord) {
		return new Coordinates(x + coord.getX(), y + coord.getY());
	}

	/**
	 * Gets the x component of the coordinates.
	 * 
	 * @return x component
	 */
	@Override
	public final int getX() {
		return x;
	}

	/**
	 * Gets the y component of the coordinates.
	 * 
	 * @return y component
	 */
	@Override
	public final int getY() {
		return y;
	}

	/**
	 * Converts global field coordinates to tile coordinates.
	 * 
	 * @return new tile coordinates
	 */
	@Override
	public final ICoordinates toTile() {
		int newx = x;
		int newy = y;

		if (newx < 0) {
			newx -= 1;
		} else {
			newx += 1;
		}

		if (newy < 0) {
			newy -= 1;
		} else {
			newy += 1;
		}

		return new Coordinates(newx / Tile.WIDTH_FIELDS, newy
				/ Tile.HEIGHT_FIELDS);
	}

	/**
	 * Converts global field coordinates to coordinates relative to the field's
	 * tile.
	 * 
	 * @return relative field coordinates
	 */
	@Override
	public final ICoordinates toRelativeField() {
		// Mod for negative numbers (a % b + b) % b
		int relativeX = ((x + 1) % Tile.WIDTH_FIELDS + Tile.WIDTH_FIELDS)
				% Tile.WIDTH_FIELDS;
		int relativeY = ((y + 1) % Tile.HEIGHT_FIELDS + Tile.HEIGHT_FIELDS)
				% Tile.HEIGHT_FIELDS;

		return new Coordinates(relativeX, relativeY);
	}

	/**
	 * Converts tile coordinates to the global field coordinates of the tile's
	 * top left field.
	 * 
	 * @return global field coordinates
	 */
	@Override
	public final ICoordinates toField() {
		return new Coordinates((x * Tile.WIDTH_FIELDS) - 1,
				(y * Tile.HEIGHT_FIELDS) - 1);
	}

	/**
	 * Calculates a hash code for coordinates. Necessary for HashMaps that use
	 * coordinates as a key.
	 * 
	 * @return hash code
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/**
	 * Indicates whether the given object is "equal to" this one.
	 * 
	 * @param o
	 *            reference object with which to compare
	 * @return true if the given object is equal to this one
	 */
	@Override
	public final boolean equals(final Object o) {
		if (o == null) {
			return false;
		}

		if (o == this) {
			return true;
		}

		if (!(o instanceof ICoordinates)) {
			return false;
		}

		ICoordinates coord = (ICoordinates) o;

		return coord.getX() == x && coord.getY() == y;
	}

	/**
	 * Calculate the distance between two Coordinates objects.
	 * 
	 * @param a
	 *            - first coordinates
	 * @param b
	 *            - second coordinates
	 * @return distance between a and b
	 */
	public static double distance(final ICoordinates a, final ICoordinates b) {
		int dx = a.getX() - b.getX();
		int dy = a.getY() - b.getY();

		return Math.sqrt(dx * dx + dy * dy);
	}
}
