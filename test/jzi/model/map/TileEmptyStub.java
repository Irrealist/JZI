package jzi.model.map;

public class TileEmptyStub implements ITile {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public IField getField(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IField getField(IFieldPosition pos) {
        // TODO Auto-generated method stub
        return new FieldEmptyStub();
    }

    @Override
    public IField getField(ICoordinates coord) {
        // TODO Auto-generated method stub
        return null;
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

    @Override
    public ITileType getTileType() {
        // TODO Auto-generated method stub
        return null;
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
}
