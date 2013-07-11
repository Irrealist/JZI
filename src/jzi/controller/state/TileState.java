package jzi.controller.state;

import java.awt.Color;

import javax.swing.BorderFactory;

import jzi.model.game.IGame;
import jzi.model.map.ICoordinates;
import jzi.view.GameMenu;
import jzi.view.IWindow;
import jzi.view.Update;

/**
 * Handles the placing of tiles.
 * 
 * @author Buddy Jonte
 */
public class TileState implements IState {
	/**
	 * UID for serialization.
	 */
	private static final long serialVersionUID = -6540691701684570512L;
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
	 * Constructor with window.
	 * 
	 * @param window
	 *            window handle
	 */
	public TileState(IWindow window) {
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
	public void setWindow(IWindow window) {
		this.window = window;
		menu = (GameMenu) window.getMenu();
	}

	/**
	 * Sets up the state. Moves to the player movement state if there are no
	 * tiles left to place and updates the GUI.
	 */
	@Override
	public void enter() {
		// check for empty tile stack
		if (game.noTiles() || game.getMap().getEmptyTiles().isEmpty()) {
			game.setState(new PlayerState(window));
			return;
		}

		menu.getDrawTileButton().setEnabled(true);
	}

	/**
	 * Tears down the state. Updates the GUI accordingly.
	 */
	@Override
	public void exit() {
		menu.getDrawTileButton().setEnabled(false);
		menu.getRotateLeftButton().setEnabled(false);
		menu.getRotateRightButton().setEnabled(false);
		menu.getTileStack().setIcon(null);
		menu.getTileStack().setBorder(null);
	}

	/**
	 * Empty because it's not relevant in this state.
	 */
	@Override
	public void rollAction() {
		// no rolling in this state
	}

	/**
	 * Continues to the player movement state, can only occur if the current
	 * tile is unplaceable.
	 */
	@Override
	public final void continueAction() {
		// only enabled if Tile is unplaceable
		game.setState(new PlayerState(window));
	}

	/**
	 * Occurs when the player clicks the map; tries to place the current tile
	 * where the player clicked.
	 * 
	 * @param coords
	 *            coordinates of the clicked field
	 */
	@Override
	public final void mapAction(final ICoordinates coords) {
		if (game.placeTile(coords.toTile())) {
			game.setState(new PlayerState(window));
			game.update(Update.Map);
		}
	}

	/**
	 * Gets called when the player draws a tile. If the drawn tile can be
	 * placed, the GUI is updated accordingly.
	 */
	@Override
	public void drawAction() {
		if (game.drawTile()) {
			return;
		}

		// If Tile is unplaceable, update GUI accordingly
		menu.getRotateLeftButton().setEnabled(false);
		menu.getRotateRightButton().setEnabled(false);
		menu.getContinueButton().setEnabled(true);
		menu.getDrawTileButton().setEnabled(false);
		menu.getTileStack().setBorder(
				BorderFactory.createLineBorder(Color.RED, 2));
	}
}
