package jzi.controller.state;

import jzi.model.entities.IPlayer;
import jzi.model.entities.IZombie;
import jzi.model.game.Game;
import jzi.model.game.IGame;
import jzi.model.map.ICoordinates;
import jzi.view.GameMenu;
import jzi.view.IWindow;
import jzi.view.Update;
import jzi.view.WinnerMenu;

/**
 * Game state for fights.
 * 
 * @author Buddy
 */
public class FightState implements IState {
    /**
     * UID for serialization.
     */
    private static final long serialVersionUID = 2327723579024610536L;
    /**
     * Current game.
     */
    private transient IGame game;
    /**
     * Game's window.
     */
    private transient IWindow window;
    /**
     * Current game menu.
     */
    private transient GameMenu menu;

    /**
     * Constructor with window.
     * 
     * @param window
     *            window handle
     */
    public FightState(IWindow window) {
        this.window = window;
        menu = (GameMenu) window.getMenu();
    }

    /**
     * Sets the state's game.
     * 
     * @param game
     *            game handle
     */
    @Override
    public void setGame(IGame game) {
        this.game = game;
    }

    /**
     * Sets the state's window handle.
     * 
     * @param window
     *            new window handle
     */
    @Override
    public void setWindow(IWindow window) {
        this.window = window;
        menu = (GameMenu) window.getMenu();
    }

    /**
     * Performs fight state setup; makes sure the continue button is disabled
     * and the roll button is enabled.
     */
    @Override
    public void enter() {
        menu.getContinueButton().setEnabled(false);
        menu.getRollDieButton().setEnabled(true);
    }

    /**
     * Tear down, resets die.
     */
    @Override
    public void exit() {
    	game.setDie(0);
    }

    /**
     * Handles rolling the die. Calls relevant methods depending on the roll
     * result.
     */
    @Override
    public void rollAction() {
        IPlayer player = game.getCurrentPlayer();
        IZombie zombie = game.getMap().getField(player.getCoordinates())
                .getZombie();
        int value = game.getDie();

        if (game.hasAdditionalPoint()) {
            value = Math.min(value + 1, Game.MAX_ROLL);
        }

        game.setDie(value);

        if (value >= zombie.getWinThreshold()) {
            fightWon(player);
        } else {
            fightLost(player, zombie);
        }
    }

    /**
     * Removes the zombie and updates the player if the player wins the fight.
     * 
     * @param player
     *            current player
     */
    public void fightWon(IPlayer player) {
        player.setPoints(player.getPoints() + 1);

        game.removeZombie();
        game.update(Update.PlayerAttributeUpdate);
        game.update(Update.FightWon);
        game.setState(new PlayerState(window));

        if (game.checkWin()) {
            window.setMenu(new WinnerMenu(window, game));
        }
    }

    /**
     * Lets the user choose between using ammunition and life points after
     * losing a fight. If the player loses all life points he is revived.
     * 
     * @param player
     *            current player
     * @param zombie
     *            zombie being fought
     */
    private void fightLost(IPlayer player, IZombie zombie) {
        int delta = zombie.getWinThreshold() - game.getDie();

        if (player.getLives() <= 1 && delta > player.getAmmo()) {
            game.setState(new ZombieState(window));
            game.revive();

            return;
        }

        game.update(Update.FightLost);

        if (delta <= player.getAmmo()) {
            game.update(Update.EnoughAmmo);
        }
    }

    /**
     * Continues to player movement state if fight was won.
     */
    @Override
    public void continueAction() {
        // only enabled if fight is won
        game.setState(new PlayerState(window));
    }

    /**
     * Doesn't do anything as the map is not relevant to fighting.
     * 
     * @param coord
     *            irrelevant
     */
    @Override
    public void mapAction(ICoordinates coord) {
        // no mapAction for this state
    }

    /**
     * Doesn't do anything as tiles can't be drawn in this state.
     */
    @Override
    public void drawAction() {
        // No tile drawing in this state
    }
}
