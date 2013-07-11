package jzi.model.map;

import jzi.model.ZombieStub;
import jzi.model.entities.IZombie;
import jzi.model.map.ICoordinates;
import jzi.model.map.IDirection;
import jzi.model.map.IField;
import jzi.model.map.IFieldPosition;

public class FieldStub1 implements IField {

    /**
     * 
     */
    private static final long serialVersionUID = -5868425804123423078L;

    @Override
    public IFieldPosition getPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPosition(IFieldPosition position) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setType(String type) {
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
    
    /**
     * @return ZombieStub for GameTest
     */
    @Override
    public IZombie getZombie() {
        return new ZombieStub();
    }

    @Override
    public void setZombie(IZombie zombie) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean hasAmmo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setAmmo(boolean ammo) {
        // TODO Auto-generated method stub

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
    public void rotateRight() {
        // TODO Auto-generated method stub

    }

    @Override
    public void rotateLeft() {
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
        return true;
    }

}
