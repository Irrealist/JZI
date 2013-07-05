package jzi.model.map;

import jzi.model.IZombie;

public class FieldEmptyStub implements IField {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public IFieldPosition getPosition() {
        return FieldPosition.TOP;
    }

    @Override
    public void setPosition(IFieldPosition position) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getType() {
        return "building";
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

    @Override
    public IZombie getZombie() {
        // TODO Auto-generated method stub
        return null;
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
        return true;
    }
}
