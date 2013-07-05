package jzi.model.map;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Interface for the game map.
 * 
 * @author Buddy Jonte
 * 
 */
public interface IMap extends java.io.Serializable {

    /**
     * Gets the neighbors of the tile with the given coordinates.
     * 
     * @param coord
     *            coordinates of tile
     * @return neighbors of tile
     */
    HashMap<IDirection, ITile> getNeighbors(ICoordinates coord);

    /**
     * Gets the neighbors of the field with the given coordinates.
     * 
     * @param coord
     *            coordinates of field
     * @return neighbors of field
     */
    HashMap<IDirection, IField> getFieldNeighbors(ICoordinates coord);

    /**
     * Tries to add a tile to the map.
     * 
     * @param coord
     *            coordinates to add tile at
     * @param tile
     *            tile to add
     * @return true if successful
     */
    boolean addTile(ICoordinates coord, ITile tile);

    /**
     * Checks whether a tile can be added at all.
     * 
     * @param tile
     *            tile to check
     * @return true if tile can be added
     */
    boolean checkTile(ITile tile);

    /**
     * Checks whether a tile can be added at the given coordinates.
     * 
     * @param coord
     *            coordinates to check
     * @param tile
     *            tile to check
     * @return true if tile can be added
     */
    boolean checkTile(ICoordinates coord, ITile tile);

    /**
     * Sets the coordinates of a tile and its fields.
     * 
     * @param coord
     *            coordinates to set
     * @param tile
     *            tile to set coordinates of
     */
    void setCoordinates(ICoordinates coord, ITile tile);

    /**
     * Gets a list of all tiles on the map.
     * 
     * @return list of tiles
     */
    LinkedList<ITile> getTiles();

    /**
     * Gets the tile at the given coordinates.
     * 
     * @param coordinates
     *            coordinates to check
     * @return tile at given coordinates
     */
    ITile getTile(ICoordinates coordinates);

    /**
     * Gets the field at the given coordinates.
     * 
     * @param field
     *            coordinates to check.
     * @return filed at given coordinates
     */
    IField getField(ICoordinates field);

    /**
     * Checks whether two fields are neighbors.
     * 
     * @param from
     *            first field
     * @param to
     *            second field
     * @return true if fields are neighbors
     */
    boolean checkNeighbor(IField from, IField to);
}
