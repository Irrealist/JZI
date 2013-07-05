package jzi.model.map;

import jzi.model.map.ICoordinates;
import jzi.model.map.IDirection;
import jzi.model.map.IField;
import jzi.model.map.IFieldPosition;
import jzi.model.map.ITile;
import jzi.model.map.ITileType;

/**
 * TileStub,used by other classes for testing their functionality. It implements
 * the ITile-Interface and the Implementation should be hard-coded.
 * 
 * @author Maibot guest24
 * 
 */
public class TileStub implements ITile {
    /**
     * 
     */
    private static final long serialVersionUID = 5235190189965346293L;
    
    /**
     * @return FieldStub for GameTest
     */
    @Override
    public IField getField(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return new FieldStub();
    }

    @Override
    public void setField(IField field, int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub

    }

    @Override
    public void rightRotation() {
        // TODO Auto-generated method stub

    }

    @Override
    public void leftRotation() {
        // TODO Auto-generated method stub

    }
    
    /**
     * returns a field stub object for map junit test
     * @return Field stub object
     */
    @Override
    public IField getField(ICoordinates coord) {
        return new FieldStub();
    }

    @Override
    public void setUp() {
        // TODO Auto-generated method stub

    }

    @Override
    public ICoordinates getCoordinates() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCoordinates(ICoordinates coords) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean hasDir(IDirection dir) {
        // TODO Auto-generated method stub
        return false;
    }
    
    /**
     * @return TileTypeStub for GameTest
     */
    @Override
    public ITileType getTileType() {
        return new TileTypeStub();
    }

    @Override
    public void setTileType(ITileType tileType) {
        // TODO Auto-generated method stub

    }

    @Override
    public IDirection getRotation() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    @Override
    public IField getField(IFieldPosition pos) {
     // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isFinalized() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setFinalized() {
        // TODO Auto-generated method stub
        
    }

}
