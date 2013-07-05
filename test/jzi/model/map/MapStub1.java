package jzi.model.map;

import java.util.HashMap;
import java.util.LinkedList;

import jzi.model.map.ICoordinates;
import jzi.model.map.IDirection;
import jzi.model.map.IField;
import jzi.model.map.IMap;
import jzi.model.map.ITile;

public class MapStub1 implements IMap {
    private IField field;

    /**
     * 
     */
    private static final long serialVersionUID = 3186363049111126029L;

    public MapStub1() {
        field = new FieldStub1();
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
     * @return FALSE for GameTest
     */
    @Override
    public boolean addTile(ICoordinates coord, ITile tile) {
        return false;
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
    
    public void setField(IField field) {
        this.field = field;
    }

    @Override
    public boolean checkTile(ICoordinates coord, ITile tile) {
        // TODO Auto-generated method stub
        return false;
    }
    
    /**
     * @return TRUE for GameTest.testDrawTileIfTrue()
     */
    @Override
    public boolean checkTile(ITile tile) {
        return true;
    }

    @Override
    public void setCoordinates(ICoordinates coord, ITile tile) {
        // TODO Auto-generated method stub

    }
    
    /**
     *Return TRUE for GameTest
     */
    @Override
    public boolean checkNeighbor(IField from, IField to) {
        return true;
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

}
