package jzi.model;

import java.awt.Color;
import java.util.Observable;

import jzi.model.entities.IPlayer;
import jzi.model.map.CoordinatesStub;
import jzi.model.map.ICoordinates;

public class PlayerWinStub extends Observable implements IPlayer {
    private boolean rolledPlayer;

    /**
     * 
     */
    private static final long serialVersionUID = -6030012223288171823L;

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
    public int getPoints() {
        return 100;
    }

    @Override
    public void setPoints(int points) {
        setChanged();
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

    @Override
    public void setColor(Color color) {
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
    public void setZombies(int zombies) {
        // TODO Auto-generated method stub

    }

    /**
     * @return 0 for GameTest
     */
    @Override
    public int getZombies() {
        return 0;
    }

    @Override
    public boolean hasRolledPlayer() {
        return rolledPlayer;
    }

    @Override
    public void setRolledPlayer(boolean roll) {
        rolledPlayer = roll;
    }

    @Override
    public void revive() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setRolledZombie(boolean roll) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean hasRolledZombie() {
        return true;
    }

    @Override
    public int getRevives() {
        // TODO Auto-generated method stub
        return 0;
    }

}
