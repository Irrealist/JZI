package jzi.model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import jzi.controller.state.IState;
import jzi.controller.state.StateStub;
import jzi.model.entities.IPlayer;
import jzi.model.entities.IZombie;
import jzi.model.entities.Player;
import jzi.model.game.IGame;
import jzi.model.map.ICoordinates;
import jzi.model.map.IField;
import jzi.model.map.IMap;
import jzi.model.map.ITile;
import jzi.model.map.MapStub;
import jzi.model.map.TileStub;
import jzi.view.IWindow;
import jzi.view.Update;

public class GameStub extends Observable implements IGame {
    private IState state;
    private int die;
    private boolean additionalPoint;
    private boolean coop;
    private IMap map;
    private IPlayer player;
    /**
     * 
     */
    private static final long serialVersionUID = -5406496008725907744L;

    public GameStub() {
        map = new MapStub();
        player = new PlayerStub();
    }

    public GameStub(boolean coop) {
        this();
        this.coop = coop;
    }

    @Override
    public void addPlayer(IPlayer player) {
        setChanged();
    }

    @Override
    public Player nextPlayer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMap getMap() {
        return map;
    }

    @Override
    public void setMap(IMap map) {
        this.map = map;
    }

    @Override
    public void setState(IState state) {
        this.state = state;
        setChanged();
    }

    @Override
    public IPlayer getCurrentPlayer() {
        return player;
    }
    
    public void setCurrentPlayer(IPlayer player) {
        this.player = player;
    }

    @Override
    public LinkedList<IPlayer> getPlayers() {
        return new LinkedList<IPlayer>();
    }

    @Override
    public int getDie() {
        hasChanged();
        return die;
    }

    @Override
    public void setDie(int val) {
        die = val;
        setChanged();
    }

    @Override
    public void setDieObject(IDie die) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean movePlayer(IField to) {
        return true;
    }

    @Override
    public boolean placeTile(ICoordinates currentCoordinates) {
        return true;
    }

    @Override
    public boolean checkWin() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public IPlayer getWinner() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isReady() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ITile getCurrentTile() {
        return new TileStub();
    }

    @Override
    public void setCurrentTile(ITile currentTile) {
        // TODO Auto-generated method stub

    }

    @Override
    public IZombie getCurrentZombie() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCurrentZombie(IZombie currentZombie) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean moveZombie(IField to) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean noTiles() {
        return true;
    }

    @Override
    public void setWinThreshold(int winThreshold) {
        // TODO Auto-generated method stub

    }

    @Override
    public IState getCurrentState() {
        if (state != null) {
            return state;
        }

        return new StateStub();
    }

    @Override
    public LinkedList<IZombie> getZombies() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addZombie(IZombie zombie) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeZombie() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean placeZombie(IField field) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isZombification() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setZombification(boolean zombification) {
        setChanged();
    }

    @Override
    public void revive() {
        setChanged();
    }

    @Override
    public boolean hasAdditionalPoint() {
        return additionalPoint;
    }

    public void setAdditionalPoint(boolean val) {
        additionalPoint = val;
    }

    @Override
    public void setUp() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean drawTile() {
        setChanged();
        return true;
    }

    @Override
    public void surrender() {
        setChanged();
    }

    @Override
    public boolean isHardcore() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setHardcore(boolean hardcore) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getRevives() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setRevives(int revives) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Update update) {
        setChanged();
    }

    @Override
    public boolean isPlayerValid(String name, Color color) {
        return true;
    }

    @Override
    public void setWindow(IWindow window) {
        // TODO Auto-generated method stub

    }

    @Override
    public IWindow getWindow() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean canZombieMove(IZombie zombie) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getWinThreshold() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void addObserver(Observer o) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getDieDifference() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isCoop() {
        return coop;
    }

    @Override
    public int getStartAmmo() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setStartAmmo(int startAmmo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void rollDie() {
        setChanged();
    }
}
