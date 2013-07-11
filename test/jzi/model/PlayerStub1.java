package jzi.model;

import java.awt.Color;

import jzi.model.entities.IPlayer;
import jzi.model.map.CoordinatesStub;
import jzi.model.map.ICoordinates;

public class PlayerStub1 implements IPlayer {

    /**
     * 
     */
    private static final long serialVersionUID = -1621566182576705005L;

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public Color getColor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setColor(Color color) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getPoints() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setPoints(int points) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getLives() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setLives(int lives) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getAmmo() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setAmmo(int ammo) {
        // TODO Auto-generated method stub

    }
    
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
    public void setZombies(int zombies) {
        // TODO Auto-generated method stub

    }
    
    /**
     * @return 3 for GameTest
     */
    @Override
    public int getZombies() {
        return 3;
    }

    @Override
    public boolean hasRolledPlayer() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setRolledPlayer(boolean roll) {
        // TODO Auto-generated method stub

    }

    @Override
    public void revive() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean hasRolledZombie() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setRolledZombie(boolean roll) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getRevives() {
        // TODO Auto-generated method stub
        return 0;
    }

}
