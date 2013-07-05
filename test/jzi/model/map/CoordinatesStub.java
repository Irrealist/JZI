package jzi.model.map;

import jzi.model.map.ICoordinates;
import jzi.model.map.IDirection;

public class CoordinatesStub implements ICoordinates {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1885680333131751203L;
    private int x;
    private int y;
    
    /**
     * Default Constructor
     */
    public CoordinatesStub() {
        
    }
    /**
     * Constructor 
     * @param x coordinate
     * @param y coordinate
     */
    public CoordinatesStub(int x,int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public ICoordinates getRight() {
        return new CoordinatesStub();
    }

    @Override
    public ICoordinates getLeft() {
        return new CoordinatesStub();
    }

    @Override
    public ICoordinates getUp() {
        return new CoordinatesStub();
    }

    @Override
    public ICoordinates getDown() {
        return new CoordinatesStub();
    }

    @Override
    public ICoordinates translate(int dx, int dy) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    
    /**
     * returns a coordinates stub for map junit test.
     * @return Coordinates stub object
     */
    @Override
    public ICoordinates toTile() {
        return new CoordinatesStub();
    }

    @Override
    public ICoordinates toRelativeField() {
        // TODO Auto-generated method stub
        return new CoordinatesStub(1, 1);
    }
    
    /**
     * returns a coordinates stub for map junit test
     * @return Coordinates stub object
     */
    @Override
    public ICoordinates getDir(IDirection dir) {
        return new CoordinatesStub();
    }

    @Override
    public ICoordinates add(ICoordinates coord) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * return a coordinates stub object for map junit test
     * @return Coordinates stub object
     */
    @Override
    public ICoordinates toField() {
        return new CoordinatesStub();
    }
}
