package jzi.controller.state;

import java.awt.Color;

import javax.swing.BorderFactory;

import jzi.model.IGame;
import jzi.model.Zombie;
import jzi.model.map.ICoordinates;
import jzi.model.map.IField;
import jzi.model.map.ITile;
import jzi.model.map.ITileType;

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
	 * Number of zombies on the current tile.
	 */
	private int zombieCount;
	/**
	 * Number of lives on the current tile.
	 */
	private int lifeCount;
	/**
	 * Amount of ammunition on the current tile.
	 */
	private int ammoCount;
	/**
	 * Whether or not the current tile has been placed.
	 */
	private boolean placed;

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
		if (!placed) {
			placeTile(coords);
		} else {
			handleObjects(coords);
		}

		game.update(Update.Map);
	}

	/**
	 * Gets called when the player draws a tile. If the drawn tile can be
	 * placed, the GUI is updated accordingly.
	 */
	@Override
	public void drawAction() {
		if (game.drawTile()) {
			ITileType type = game.getCurrentTile().getTileType();

			zombieCount = type.getZombieCount();
			ammoCount = type.getAmmoCount();
			lifeCount = type.getLifeCount();

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

	/**
	 * Tries to place the current tile.
	 * 
	 * @param coords
	 *            Coordinates to place tile at
	 */
	private void placeTile(ICoordinates coords) {
		if (game.placeTile(coords.toTile())) {
			String name = game.getMap().getTile(coords.toTile()).getTileType()
					.getName();

			menu.getRotateLeftButton().setEnabled(false);
			menu.getRotateRightButton().setEnabled(false);

			placed = true;

			// no name tiles have automatic placement
			if (name.equals("") || name.equals("Helicopter")) {
				game.setState(new PlayerState(window));
			} else {
				game.update(Update.CurrentToPlaceUpdate);
			}
		}
	}

	/**
	 * Handles placing zombies, ammunition and life points on the newly placed
	 * tile.
	 * 
	 * @param coords
	 *            coordinates of clicked field
	 */
	private void handleObjects(ICoordinates coords) {
		ITile tile = game.getMap().getTile(coords.toTile());
		IField field = game.getMap().getField(coords);

		if (tile == null || field == null) {
			return;
		}

		if (tile.isFinalized()
				|| (!field.getType().equalsIgnoreCase("building") && !tile
						.getTileType().getName().equals("Helicopter"))) {
			return;
		}

		placeObjects(field);

		if (zombieCount == 0 && ammoCount == 0 && lifeCount == 0) {
			tile.setFinalized();
			game.update(Update.CurrentToPlaceReset);
			game.setState(new PlayerState(window));
		}
	}

	/**
	 * Places objects on the given fields and decrements counters.
	 * 
	 * @param field
	 *            Field to place objects on
	 */
	private void placeObjects(IField field) {
		// separated conditions so zombies, ammo and life are placed after
		// each other

		if (zombieCount > 0) {
			if (field.getZombie() == null) {
				game.addZombie(new Zombie(field.getCoordinates()));
				zombieCount--;
				game.update(Update.PlaceObjects);
				proofCount(zombieCount);
				checkAmmo();

			}
		} else if (ammoCount > 0) {
			if (!field.hasAmmo() && !field.hasLife()) {
				field.setAmmo(true);
				ammoCount--;
				game.update(Update.PlaceObjects);
				proofCount(ammoCount);
			}
		} else if (lifeCount > 0) {
			if (!field.hasLife() && !field.hasAmmo()) {
				field.setLife(true);
				lifeCount--;

				game.update(Update.PlaceObjects);
			}

		}

	}

	/**
	 * get count for Zombie to place on Tile
	 * 
	 * @return count
	 */
	public int getZombieCount() {
		return zombieCount;
	}

	/**
	 * get count for Ammo to place on Tile
	 * 
	 * @return count
	 */
	public int getAmmoCount() {
		return ammoCount;
	}

	/**
	 * get count for Life to place on Tile
	 * 
	 * @return count
	 */
	public int getLifeCount() {
		return lifeCount;
	}

	/**
	 * proof if count 0 and update GUI
	 * 
	 * @param count
	 */
	private void proofCount(int count) {
		if (count == 0) {

			game.update(Update.CurrentToPlaceUpdate);
		}
	}

	/**
	 * check if ammo and zombie count are 0
	 */
	private void checkAmmo() {
		if (ammoCount == 0 && zombieCount == 0) {
			game.update(Update.CurrentToPlaceUpdate);
		}
	}
}
