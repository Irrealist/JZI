package jzi.model.map;

import jzi.model.ZombieStub;
import jzi.model.entities.IZombie;
import jzi.model.map.ICoordinates;
import jzi.model.map.IDirection;
import jzi.model.map.IField;
import jzi.model.map.IFieldPosition;

/**
 * FieldStub,used by other classes for testing their functionality.
 * It implements the IField-Interface and the
 * Implementation should be hard-coded.
 * 
 * @author Maibot guest24
 *
 */
public class FieldStub implements IField {
    /**
     * 
     */
    private static final long serialVersionUID = -1742349301217012113L;

    @Override
    public void setPosition(IFieldPosition position) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return new String("building");
    }

    @Override
    public void setType(String type) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void rotateRight() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void rotateLeft() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setLeft() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setRight() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setUp() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public IZombie getZombie() {
        // TODO Auto-generated method stub
        return new ZombieStub();
    }

    @Override
    public boolean hasAmmo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasLife() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setLife(boolean hasLife) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setAmmo(boolean ammo) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setZombie(IZombie zombie) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * returns a coordinates stub for map junit test
     * @return Coordinates stub object
     */
    @Override
    public ICoordinates getCoordinates() {
        return new CoordinatesStub();
    }

    @Override
    public void setCoordinates(ICoordinates coords) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public IFieldPosition getPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasDir(IDirection d) {
        // TODO Auto-generated method stub
        return false;
    }

}
