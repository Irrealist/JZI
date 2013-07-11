package jzi.model.map;

import java.util.HashMap;
import java.util.LinkedList;

import jzi.model.map.ICoordinates;
import jzi.model.map.IDirection;
import jzi.model.map.IField;
import jzi.model.map.IMap;
import jzi.model.map.ITile;

public class MapStub implements IMap {
    private IField field;

    /**
     * 
     */
    private static final long serialVersionUID = -8831574395423493130L;

    public MapStub() {
        field = new FieldStub();
    }

    @Override
    public HashMap<IDirection, ITile> getNeighbors(ICoordinates coord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HashMap<IDirection, IField> getFieldNeighbors(ICoordinates coord) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return TRUE for gameTestObj.setUP()
     */
    @Override
    public boolean addTile(ICoordinates coord, ITile tile) {
        return true;
    }

    @Override
    public LinkedList<ITile> getTiles() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ITile getTile(ICoordinates coordinates) {
        return new TileStub();
    }

    /**
     * @return FieldStub for GameTest
     */
    @Override
    public IField getField(ICoordinates field) {
        return this.field;
    }

    @Override
    public boolean checkTile(ICoordinates coord, ITile tile) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @return FALSE for GameTest.testDrawTileIfTrue()
     */
    @Override
    public boolean checkTile(ITile tile) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setCoordinates(ICoordinates coord, ITile tile) {
        // TODO Auto-generated method stub

    }

    /**
     * Return FALSE for GameTest
     */
    @Override
    public boolean checkNeighbor(IField from, IField to) {
        return false;
    }

    /**
     * Sets the field to be returned.
     * 
     * @param field
     *            new field
     */
    public void setField(IField field) {
        this.field = field;
    }

	@Override
	public boolean checkTileRotations(ICoordinates coord, ITile tile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LinkedList<ICoordinates> getEmptyTiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<ICoordinates> getEmptyBuildings() {
		// TODO Auto-generated method stub
		return null;
	}
}
