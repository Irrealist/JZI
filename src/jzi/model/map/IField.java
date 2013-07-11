package jzi.model.map;

import jzi.model.entities.IZombie;

/**
 * Should be implemented by the original class and the stub class. All the
 * method of the original class need in the stub class for testing should be
 * declared here
 * 
 * @author Maibot guest24
 * 
 */
public interface IField extends java.io.Serializable {
    /**
     * Getter for position of a field on a tile.
     * 
     * @return position of a field
     */
    IFieldPosition getPosition();

    /**
     * Setter for position of a field on a tile.
     * 
     * @param position
     *            - position of a field
     */
    void setPosition(IFieldPosition position);

    /**
     * Gets the field's type.
     * 
     * @return field type
     */
    String getType();

    /**
     * Sets the field's type.
     * 
     * @param type
     *            field's type
     */
    void setType(String type);
    
    /**
     * Sets the field as leavable to the left.
     */
    void setLeft();

    /**
     * Sets the field as leavable to the right.
     */
    void setRight();

    /**
     * Sets the field as leavable to the top.
     */
    void setUp();

    /**
     * Sets the field as leavable to the bottom.
     */
    void setDown();
    
    /**
     * Gets the zombie on the field.
     * 
     * @return zombie on the field
     */
    IZombie getZombie();
    
    /**
     * Sets the field's zombie.
     * 
     * @param zombie
     *            new zombie
     */
    void setZombie(IZombie zombie);
    
    /**
     * Checks whether the field has ammunition.
     * 
     * @return true if field has ammunition
     */
    boolean hasAmmo();
    
    /**
     * Sets whether or not the field has ammunition.
     * 
     * @param ammo
     *            whether or not the field has ammunition
     */
    void setAmmo(boolean ammo);
    
    /**
     * Checks whether the field has a life point.
     * 
     * @return true if the field has a life point
     */
    boolean hasLife();
    
    /**
     * Sets whether or not the field has a life point.
     * 
     * @param hasLife
     *            whether or not the field has a life point
     */
    void setLife(boolean hasLife);
    
    /**
     * Rotates the field to the right.
     */
    void rotateRight();

    /**
     * Rotates the field to the left.
     */
    void rotateLeft();
    
    /**
     * Gets the field's coordinates.
     * 
     * @return field coordinates
     */
    ICoordinates getCoordinates();

    /**
     * Sets the field's coordinates.
     * 
     * @param coords
     *            new field coordinates
     */
    void setCoordinates(ICoordinates coords);

    /**
     * Checks whether the field can be left in the given direction.
     * 
     * @param dir
     *            direction to check
     * @return true if field can be left in given direction
     */
    boolean hasDir(IDirection dir);
}
