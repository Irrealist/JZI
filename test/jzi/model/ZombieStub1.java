package jzi.model;

import jzi.model.map.ICoordinates;

public class ZombieStub1 implements IZombie {

    /**
     * 
     */
    private static final long serialVersionUID = 7751115663462711110L;

    @Override
    public ICoordinates getCoordinates() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCoordinates(ICoordinates coord) {
        // TODO Auto-generated method stub

    }
    
    /**
     * @return 0 for GameTest
     */
    @Override
    public int getSteps() {
        return 0;
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
        return 0;
    }

}
