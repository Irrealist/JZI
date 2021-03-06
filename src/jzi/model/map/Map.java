package jzi.model.map;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Contains the set of tiles that make up the game map and manages adding tiles
 * to the map.
 * 
 * @author Buddy Jonte
 */
public class Map implements IMap, java.io.Serializable {
	/**
	 * UID for serialization.
	 */
	private static final long serialVersionUID = -2675157579974092459L;
	/**
	 * Hash map with tiles on this map. Key: Coordinates XY on the map Value:
	 * Tile on the map
	 */
	private HashMap<ICoordinates, ITile> tiles = new HashMap<>();

	/**
	 * Gets the neighbors of a tile with the given coordinates.
	 * 
	 * @param coord
	 *            coordinates to get neighbors of
	 * @return HashMap that maps a direction to the neighbor in that direction
	 */
	@Override
	public final HashMap<IDirection, ITile> getNeighbors(
			final ICoordinates coord) {
		HashMap<IDirection, ITile> list = new HashMap<>();

		for (Direction dir : Direction.values()) {
			list.put(dir, tiles.get(coord.getDir(dir)));
		}

		return list;
	}

	/**
	 * Gets the neighbors of a field with the given coordinates.
	 * 
	 * @param coord
	 *            coordinates to get neighbors of
	 * @return HashMap that maps a direction to the neighbor in that direction
	 */
	@Override
	public final HashMap<IDirection, IField> getFieldNeighbors(
			final ICoordinates coord) {
		HashMap<IDirection, IField> list = new HashMap<>();

		for (Direction dir : Direction.values()) {
			list.put(dir, getField(coord.getDir(dir)));
		}

		return list;
	}

	/**
	 * Tries to add a tile to the map at the given coordinates.
	 * 
	 * @param coord
	 *            coordinates to add tile at
	 * @param tile
	 *            tile to add
	 * @return true if tile could be added
	 */
	@Override
	public final boolean addTile(final ICoordinates coord, final ITile tile) {
		if (getTile(coord) != null) {
			return false;
		}

		if (!checkTile(coord, tile)) {
			return false;
		}

		setCoordinates(coord, tile);
		tiles.put(coord, tile);

		return true;
	}

	/**
	 * Gets a list of all empty building fields.
	 * 
	 * @return linked list of empty buildings
	 */
	public LinkedList<ICoordinates> getEmptyBuildings() {
		LinkedList<ICoordinates> fields = new LinkedList<>();

		for (java.util.Map.Entry<ICoordinates, ITile> entry : tiles.entrySet()) {
			ITile tile = entry.getValue();

			for (int x = 0; x < Tile.WIDTH_FIELDS; x++) {
				for (int y = 0; y < Tile.HEIGHT_FIELDS; y++) {
					IField field = tile.getField(y, x);

					if (field.getType().equals("building")
							&& field.getZombie() == null) {
						fields.add(field.getCoordinates());
					}
				}
			}
		}

		return fields;
	}

	/**
	 * Gets a list of all empty spaces adjacent to at least one tile.
	 * 
	 * @return linked list of empty tiles
	 */
	public LinkedList<ICoordinates> getEmptyTiles() {
		LinkedList<ICoordinates> found = new LinkedList<>();
		LinkedList<ICoordinates> visited = new LinkedList<>();
		Queue<ICoordinates> queue = new LinkedList<>();

		queue.add(new Coordinates(0, 0));
		visited.add(new Coordinates(0, 0));

		// BFS
		while (!queue.isEmpty()) {
			ICoordinates current = queue.remove();

			if (getTile(current) == null) {
				found.add(current);
			} else {
				for (Direction dir : Direction.values()) {
					ICoordinates next = current.getDir(dir);

					if (!visited.contains(next) && getTile(current).hasDir(dir)) {
						visited.add(next);
						queue.add(next);
					}
				}
			}
		}

		return found;
	}

	/**
	 * Checks whether the given tile can be placed at the given coordinates at
	 * all. Iterates over rotations and checks placement.
	 * 
	 * @param coord
	 *            coordinates to check
	 * @param tile
	 *            tile to check
	 * @return true if tile can be placed at given coordinates in at least one
	 *         rotation
	 */
	public boolean checkTileRotations(ICoordinates coord, ITile tile) {
		// assume tile is not placeable
		boolean placeable = false;

		for (int i = 0; i < Direction.values().length; i++) {
			if (checkTile(coord, tile)) {
				placeable = true;
			}

			tile.rightRotation();
		}

		return placeable;
	}

	/**
	 * Checks if a tile can be added to the map at all. Uses BFS to find all
	 * empty coordinates that have at least one neighboring tile and checks
	 * whether the given tile can be placed at those coordinates.
	 * 
	 * @param tile
	 *            tile to check
	 * @return true if tile can be added to the map
	 */
	@Override
	public final boolean checkTile(ITile tile) {
		// Check all empty tile coordinates
		for (ICoordinates coord : getEmptyTiles()) {
			// Try each rotation
			for (int i = 0; i < Direction.values().length; i++) {
				if (checkTileRotations(coord, tile)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Check whether a tile can be added at the given coordinates.
	 * 
	 * @param coord
	 *            coordinates to check
	 * @param tile
	 *            tile to check
	 * @return true if tile can be added at given coordinates
	 */
	@Override
	public final boolean checkTile(final ICoordinates coord, ITile tile) {
		HashMap<IDirection, ITile> neighbors = getNeighbors(coord);
		boolean foundNeighbor = false;
		boolean foundStreet = false;

		// First Tile has no neighbors -> fail
		if (tiles.isEmpty()) {
			foundNeighbor = true;
			foundStreet = true;
		}

		// Check if Coordinate has at least one neighbor
		// and connects at least one street
		for (Direction d : Direction.values()) {
			if (neighbors.get(d) != null) {
				foundNeighbor = true;

				if (tile.getField(d.getField()).getType()
						.equalsIgnoreCase("street")) {
					foundStreet = true;
					break;
				}
			}
		}

		// No neighbors found, return
		if (!foundNeighbor || !foundStreet) {
			return false;
		}

		for (Direction dir : Direction.values()) {
			if (neighbors.get(dir) != null) {
				IField here = tile.getField(dir.getField());
				IField there = neighbors.get(dir).getField(
						dir.getOpposite().getField());

				if (here.hasDir(dir) != there.hasDir(dir.getOpposite())) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Sets the coordinates of a tile and its fields.
	 * 
	 * @param coord
	 *            coordinates of the tile
	 * @param tile
	 *            tile to set coordinates of
	 */
	@Override
	public final void setCoordinates(final ICoordinates coord, final ITile tile) {
		ICoordinates base = coord.toField();

		tile.setCoordinates(coord);

		// Iterate through fields
		for (int x = 0; x < Tile.WIDTH_FIELDS; x++) {
			for (int y = 0; y < Tile.HEIGHT_FIELDS; y++) {
				ICoordinates rel = new Coordinates(x, y);
				tile.getField(rel).setCoordinates(base.add(rel));

				// if field has a zombie, set his coordinates, too
				if (tile.getField(rel).getZombie() != null) {
					tile.getField(rel).getZombie()
							.setCoordinates(base.add(rel));
				}
			}
		}
	}

	/**
	 * Gets a list of all tiles placed on the map.
	 * 
	 * @return Linked list of {@link ITile}s
	 */
	@Override
	public final LinkedList<ITile> getTiles() {
		return new LinkedList<ITile>(tiles.values());
	}

	/**
	 * Gets the tile at the given coordinates.
	 * 
	 * @param coordinates
	 *            coordinates to get tile at
	 * @return tile at given coordinates
	 */
	@Override
	public final ITile getTile(final ICoordinates coordinates) {
		return tiles.get(coordinates);
	}

	/**
	 * Gets the field at the given coordinates.
	 * 
	 * @param coords
	 *            coordinates of field
	 * @return field at given coordinates
	 */
	@Override
	public final IField getField(final ICoordinates coords) {
		if (tiles.get(coords.toTile()) == null) {
			return null;
		}

		return tiles.get(coords.toTile()).getField(coords.toRelativeField());
	}

	/**
	 * Checks whether the given fields are neighbors.
	 * 
	 * @param from
	 *            first field
	 * @param to
	 *            second field
	 * @return true if from and to are neighbors
	 */
	@Override
	public final boolean checkNeighbor(final IField from, final IField to) {
		HashMap<IDirection, IField> neighbors = getFieldNeighbors(from
				.getCoordinates());

		if (!neighbors.containsValue(to)) {
			return false;
		}

		for (Direction dir : Direction.values()) {
			if (neighbors.get(dir) != null && neighbors.get(dir).equals(to)) {
				if (!from.hasDir(dir) || !to.hasDir(dir.getOpposite())) {
					return false;
				}

				break;
			}
		}

		return true;
	}

	/**
	 * Only needed by JUnit tests
	 */
	public void setHashMap(HashMap<ICoordinates, ITile> tiles) {
		this.tiles = tiles;
	}
}
