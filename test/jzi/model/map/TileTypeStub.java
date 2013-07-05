package jzi.model.map;

import jzi.model.map.IField;
import jzi.model.map.ITile;
import jzi.model.map.ITileType;

public class TileTypeStub implements ITileType {

    private static final long serialVersionUID = 1L;

    /**
     * @return String "Helicopter" for GameTest
     */
    @Override
    public String getName() {
        return "Helicopter";
    }

    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setCount(int count) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getZombieCount() {
        return 6;
    }

    @Override
    public void setZombieCount(int zombieCount) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getLifeCount() {
        return 6;
    }

    @Override
    public void setLifeCount(int lifeCount) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getAmmoCount() {
        return 6;
    }

    @Override
    public void setAmmoCount(int ammoCount) {
        // TODO Auto-generated method stub

    }

    @Override
    public IField getField(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setField(int rowIndex, int columnIndex, IField field) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getFileName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setFileName(String fileName) {
        // TODO Auto-generated method stub

    }

    /**
     * @return TileStub for GameTest
     */
    @Override
    public ITile createTile() {
        return new TileStub();
    }
}
