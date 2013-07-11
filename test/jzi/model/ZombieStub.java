package jzi.model;

import jzi.model.entities.IZombie;
import jzi.model.map.CoordinatesStub;
import jzi.model.map.ICoordinates;

public class ZombieStub implements IZombie {
    
    private static final long serialVersionUID = 2616847419335751377L;
    
    /**
     * @return CoordinatesStub for GameTest
     */
    @Override
    public ICoordinates getCoordinates() {
        return new CoordinatesStub();
    }

    @Override
    public void setCoordinates(ICoordinates coord) {
        // TODO Auto-generated method stub

    }
    
    /**
     * @return 1 for GameTest
     */
    @Override
    public int getSteps() {
        return 1;
    }

    @Override
    public void setSteps(int steps) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resetSteps() {
        // TODO Auto-generated method stub

    }

    @Override
    public int getWinThreshold() {
        // TODO Auto-generated method stub
        return 1;
    }

}
