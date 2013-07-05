package jzi.controller.state;

import jzi.controller.state.FightState;
import jzi.model.IPlayer;
import jzi.view.IWindow;

public class FightStateStub extends FightState {
    /**
     * Generated UID for serialization.
     */
    private static final long serialVersionUID = 6397907442659921169L;
    private boolean changed;

    public FightStateStub(IWindow window) {
        super(window);
    }

    @Override
    public void fightWon(IPlayer player) {
        changed = true;
    }

    public boolean hasChanged() {
        return changed;
    }
}
