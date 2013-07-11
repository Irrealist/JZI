package jzi.model.map;

import java.util.ArrayList;
import java.util.List;

import jzi.model.entities.IZombie;

/**
 * Field holds data for one field object.
 * 
 * @author MaiBot guest24
 * @version 0.2
 */
public class Field implements IField {
    /**
     * UID for serialization.
     */
    private static final long serialVersionUID = 4715775052443823737L;
    /**
     * Type of this Field.
     */
    private String type;
    /**
     * Zombie on this Field.
     */
    private IZombie zombie;
    /**
     * Whether this Field has ammunition.
     */
    private boolean hasAmmo;
    /**
     * Whether this Field has a life point.
     */
    private boolean hasLife;
    /**
     * List with accessible directions of this Field.
     */
    private List<IDirection> directionList = new ArrayList<IDirection>();
    /**
     * Position of Field on its Tile.
     */
    private IFieldPosition position;
    /**
     * Global Coordinates of this Field.
     */
    private ICoordinates coords;
    /**
     * Size of a Field.
     */
    public static final int FIELD_SIZE = 266;

    /**
     * Default constructor.
     */
    public Field() { 
    }

    /**
     * Copy constructor.
     * 
     * @param fieldOriginal
     *            - {@link IField} to copy.
     */
    public Field(final IField fieldOriginal) {
        for (Direction dir : Direction.values()) {
            if (fieldOriginal.hasDir(dir)) {
                directionList.add(dir);
            }
        }

        position = fieldOriginal.getPosition();
        type = fieldOriginal.getType();
    }

    /**
     * Get this Field's position on its Tile.
     * 
     * @return {@link IFieldPosition} of this Field
     */
    @Override
    public final IFieldPosition getPosition() {
        return position;
    }

    /**
     * Setter for position of field on a tile.
     * 
     * @param position
     *            - position of field
     */
    @Override
    public final void setPosition(final IFieldPosition position) {
        this.position = position;
    }

    /**
     * Get this Field's type.
     * 
     * @return Field type
     */
    @Override
    public final String getType() {
        return this.type;
    }

    /**
     * Set this Field's type.
     * 
     * @param type
     *            - Field type
     */
    @Override
    public final void setType(final String type) {
        this.type = type;
    }

    /**
     * Set this Field as leavable to the left.
     */
    @Override
    public final void setLeft() {
        directionList.add(Direction.LEFT);
    }

    /**
     * Set this Field as leavable to the right.
     */
    public final void setRight() {
        directionList.add(Direction.RIGHT);
    }

    /**
     * Set this Field as leavable to the top.
     */
    @Override
    public final void setUp() {
        directionList.add(Direction.UP);
    }

    /**
     * Set this Field as leavable to the bottom.
     */
    @Override
    public final void setDown() {
        directionList.add(Direction.DOWN);
    }

    /**
     * Get the Zombie on this Field.
     * 
     * @return {@link IZombie} on this Field or null if there is no Zombie
     */
    @Override
    public final IZombie getZombie() {
        return zombie;
    }

    /**
     * Set this Field's Zombie.
     * 
     * @param zombie
     *            - {@link IZombie} to be set on this Field
     */
    @Override
    public final void setZombie(IZombie zombie) {
        this.zombie = zombie;
    }

    /**
     * Check whether this Field has ammunition.
     * 
     * @return true if this Field has ammunition
     */
    @Override
    public final boolean hasAmmo() {
        return hasAmmo;
    }

    /**
     * Set whether this Field has ammunition.
     * 
     * @param ammo
     *            - whether or not this Field should have ammunition
     */
    @Override
    public final void setAmmo(boolean ammo) {
        hasAmmo = ammo;
    }

    /**
     * Check whether this Field has a life point.
     * 
     * @return true if this Field has a life point
     */
    @Override
    public final boolean hasLife() {
        return hasLife;
    }

    /**
     * Set whether this Field has a life point.
     * 
     * @param hasLife
     *            - whether or not this Field should have a life point
     */
    @Override
    public final void setLife(boolean hasLife) {
        this.hasLife = hasLife;
    }

    /**
     * Rotates direction attributes of a field to the right. Also changes the
     * Field's {@link IFieldPosition}.
     */
    @Override
    public final void rotateRight() {
        // Temporary list
        List<IDirection> newDirectionList = new ArrayList<>();

        for (IDirection d : directionList) {
            // rotateLeft() returns a new direction which is to be added in
            // temporary list
            newDirectionList.add(d.rotateRight());
        }

        // Discard old list
        directionList = newDirectionList;

        // rotateRightPosition() returns a new position for the field which is
        // to be set
        setPosition(position.rotateRightPosition());
    }

    /**
     * Rotates direction attributes of a field to the left. Also changes the
     * Field's {@link IFieldPosition}.
     */
    @Override
    public final void rotateLeft() {
        // temporary list
        List<IDirection> newDirectionList = new ArrayList<>();

        for (IDirection d : directionList) {
            // rotateLeft() returns a new direction which is to be added in
            // temporary list
            newDirectionList.add(d.rotateLeft());
        }

        // Discard old list
        directionList = newDirectionList;

        // rotateLeftPosition() returns a new position for the field which is to
        // be set
        setPosition(position.rotateLeftPosition());
    }

    /**
     * A reasonable representation of a field with all its attributes.
     * 
     * @return String representation of a field.
     * 
     */
    @Override
    public final String toString() {
        return "[Field : pos = " + position + ",type = " + type + ",left = "
                + directionList.contains(Direction.LEFT) + ",right = "
                + directionList.contains(Direction.RIGHT) + ",up = "
                + directionList.contains(Direction.DOWN) + ",down = "
                + directionList.contains(Direction.DOWN) + "]";
    }

    /**
     * Get this Field's global coordinates.
     * 
     * @return global field coordinates
     */
    @Override
    public ICoordinates getCoordinates() {
        return coords;
    }

    /**
     * Set this Field's global coordinates.
     * 
     * @param coords
     *            new coordinates
     */
    @Override
    public final void setCoordinates(final ICoordinates coords) {
        this.coords = coords;
    }

    /**
     * Check whether this Field can be left in the given Direction.
     * 
     * @param d
     *            - Direction to check
     * @return true if Field can be left in given Direction.
     */
    @Override
    public final boolean hasDir(final IDirection d) {
        return directionList.contains(d);
    }
    
    /**
     * Getter for the directionList(needed only by JUnit)
     * @return ArrayList filled with enumerations
     */
    public final List<IDirection> returnDirList() {
        return this.directionList;
    }
}
