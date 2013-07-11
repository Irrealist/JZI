package jzi.controller.state;

import java.util.Observable;

import jzi.model.game.IGame;
import jzi.model.map.ICoordinates;
import jzi.view.IWindow;

public class StateStub extends Observable implements IState {
    /**
     * Generated UID for serialization.
     */
    private static final long serialVersionUID = -5060419340888997793L;

    @Override
    public void enter() {
        // TODO Auto-generated method stub

    }

    @Override
    public void exit() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setGame(IGame game) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setWindow(IWindow window) {
        // TODO Auto-generated method stub

    }

    @Override
    public void rollAction() {
        setChanged();
    }

    @Override
    public void continueAction() {
        setChanged();
    }

    @Override
    public void mapAction(ICoordinates field) {
        setChanged();
    }

    @Override
    public void drawAction() {
        setChanged();
    }

}
