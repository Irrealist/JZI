package jzi.controller.state;

import jzi.model.Game;
import jzi.model.IGame;
import jzi.model.IPlayer;
import jzi.model.map.ICoordinates;
import jzi.model.map.IField;
import jzi.model.map.ITile;
import jzi.view.DieGraphic;
import jzi.view.GameMenu;
import jzi.view.IWindow;
import jzi.view.Update;
import jzi.view.WinnerMenu;

/**
 * Game state for player movement.
 * 
 * @author Buddy Jonte
 */
public class PlayerState implements IState {
    /**
     * UID for serialization.
     */
    private static final long serialVersionUID = 2323897615269396674L;
    /**
     * State's game handle.
     */
    private transient IGame game;
    /**
     * State's window handle.
     */
    private transient IWindow window;
    /**
     * State's game menu handle.
     */
    private transient GameMenu menu;

    /**
     * Constructor with window handle.
     * 
     * @param window
     *            window handle
     */
    public PlayerState(final IWindow window) {
        this.window = window;
        menu = (GameMenu) window.getMenu();
    }

    /**
     * Sets the state's game handle.
     * 
     * @param game
     *            new game handle
     */
    @Override
    public final void setGame(final IGame game) {
        this.game = game;
    }

    /**
     * Sets the state's window handle.
     * 
     * @param window
     *            window handle
     */
    @Override
    public void setWindow(final IWindow window) {
        this.window = window;
        menu = (GameMenu) window.getMenu();
    }

    /**
     * Sets up the state. Updates the GUI, checks player attributes and moves to
     * other states if necessary.
     */
    @Override
    public final void enter() {
        // cancel enter action if checkField caused a state change
        if (checkField()) {
            return;
        }

        menu.getContinueButton().setEnabled(true);

        IPlayer player = game.getCurrentPlayer();

        // Only enable rolling if player has not rolled for movement this round
        if (!player.hasRolledPlayer()) {
            menu.getRollDieButton().setEnabled(true);
        } else if (player.getSteps() > 0) {
            menu.getDie().setIcon(DieGraphic.getDiefromInt(player.getSteps()));
        }

        if (player.getSteps() == 0 && player.hasRolledPlayer()) {
            game.setState(new ZombieState(window));
        }
    }

    /**
     * Tears down the state. Updates the GUI accordingly.
     */
    @Override
    public final void exit() {
        menu.getContinueButton().setEnabled(false);
        menu.getRollDieButton().setEnabled(false);
        menu.getDie().setIcon(null);
    }

    /**
     * Occurs when the player rolls the die. Sets the player's steps to the die
     * value.
     */
    @Override
    public final void rollAction() {
        game.getCurrentPlayer().setRolledPlayer(true);
        game.getCurrentPlayer().setSteps(game.getDie());
    }

    /**
     * Occurs when the player presses the continue button. Moves to the zombie
     * movement state.
     */
    @Override
    public final void continueAction() {
        game.setState(new ZombieState(window));
    }

    /**
     * Occurs when the player clicks on the map. Tries to move the player if
     * everything is correct.
     * 
     * @param coord
     *            coordinates of the field the player clicked on
     */
    @Override
    public final void mapAction(final ICoordinates coord) {
        ITile tile = game.getMap().getTile(coord.toTile());
        IField field = game.getMap().getField(coord);
        IPlayer player = game.getCurrentPlayer();

        if (!player.hasRolledPlayer() || tile == null || field == null) {
            return;
        }

        if (game.movePlayer(field)) {
            menu.getDie().setIcon(DieGraphic.getDiefromInt(player.getSteps()));

            checkField();

            if (player.getSteps() == 0
                    && game.getCurrentState() instanceof PlayerState) {
                game.setState(new ZombieState(window));
            }
        }
    }

    /**
     * Checks the field the player is standing on for lives and ammunition; also
     * initates fighting if the player is on a field with a zombie.
     * 
     * @return true if a state change was initiated
     */
    private boolean checkField() {
        IPlayer player = game.getCurrentPlayer();
        IField field = game.getMap().getField(player.getCoordinates());

        if (field == null) {
            return false;
        }

        if (field.getZombie() != null) {
            game.setState(new FightState(window));
            return true;
        }

        if (game.checkWin()) {
            window.setMenu(new WinnerMenu(window, game));
            return true;
        }

        if (field.hasAmmo()) {
            player.setAmmo(player.getAmmo() + 1);
            game.update(Update.PlayerAttributeUpdate);
            field.setAmmo(false);
        }

        if (field.hasLife() && player.getLives() < Game.MAX_LIVES) {
            player.setLives(player.getLives() + 1);
            game.update(Update.PlayerAttributeUpdate);
            field.setLife(false);
        }

        return false;
    }

    /**
     * Doesn't do anything as tiles can't be drawn in this state.
     */
    @Override
    public void drawAction() {
        // no tile drawing in this state
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PlayerState) {
            return true;
        }

        return false;
    }
}
