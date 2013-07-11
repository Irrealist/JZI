package jzi.controller;

import java.util.LinkedList;
import java.util.Observer;
import java.util.HashMap;
import java.util.EventListener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import jzi.controller.state.FightState;
import jzi.controller.state.TileState;
import jzi.controller.state.ZombieMode;
import jzi.controller.state.ZombieState;
import jzi.model.Game;
import jzi.model.GameCoop;
import jzi.model.IGame;
import jzi.model.IPlayer;
import jzi.model.ParseXMLConfiguration;
import jzi.model.Player;
import jzi.model.map.Coordinates;
import jzi.model.map.ITile;
import jzi.view.Action;
import jzi.view.SetupMenu;
import jzi.view.DieGraphic;
import jzi.view.GameMenu;
import jzi.view.IWindow;
import jzi.view.MainMenu;
import jzi.view.Update;
import jzi.view.Window;
import jzi.view.Popups;

/**
 * Controls the game flow.
 * 
 * @author Buddy Jonte
 */
public class Controller implements IController {
	/**
	 * {@link IGame} instance.
	 */
	private IGame game;

	/**
	 * HashMap of ActionListeners that the Controller offers.
	 */
	private HashMap<Action, EventListener> actionMap;

	/**
	 * {@link Window} instance.
	 */
	private IWindow window;

	/**
	 * {@ParseXMLConfiguration} instance.
	 */
	private ParseXMLConfiguration parser;

	/**
	 * Contains the rules of the Game.
	 */
	private String gameRules;

	/**
	 * Default constructor. Populates the ActionMap, parses {@link ITile} data
	 * and sets up the GUI.
	 * 
	 * @param test
	 */
	public Controller(IWindow window) {
		this.window = window;

		populateActionMap();

		parser = new ParseXMLConfiguration("./data/tiles.xml", false);
		TextData textdata = new TextData("./data/rules.txt");
		gameRules = textdata.read();

		new Thread(new Runnable() {
			public void run() {
				String[] die = { "one", "two", "three", "four", "five", "six" };

				for (int n = 0; n < die.length; n++) {
					try {
						new DieGraphic(ImageIO.read(new File("./data/img/die/"
								+ die[n] + ".png")), n + 1);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		window.setListeners(actionMap);
		window.setMenu(new MainMenu(window));
	}

	/**
	 * Returns the controller's game instance.
	 * 
	 * @return game instance
	 */
	public IGame getGame() {
		return game;
	}

	/**
	 * Sets the controller's game instance.
	 * 
	 * @param game
	 *            new game instance
	 */
	public void setGame(IGame game) {
		this.game = game;
	}

	/**
	 * Gets the game's current window instance.
	 * 
	 * @return window instance
	 */
	public IWindow getWindow() {
		return window;
	}

	/**
	 * Adds all ActionListeners offered by the Controller to its ActionMap.
	 */
	private void populateActionMap() {
		actionMap = new HashMap<>();
		actionMap.put(Action.NewGame, new NewGameAction());
		actionMap.put(Action.Coop, new CoopAction());
		actionMap.put(Action.Exit, new ExitAction());
		actionMap.put(Action.AddBack, new AddBackAction());
		actionMap.put(Action.Start, new StartAction());
		actionMap.put(Action.AddPlayer, new AddPlayerAction());
		actionMap.put(Action.Roll, new RollAction());
		actionMap.put(Action.Draw, new DrawAction());
		actionMap.put(Action.Rules, new RulesAction());
		actionMap.put(Action.Map, new MapAction());
		actionMap.put(Action.RotateLeft, new RotateLeftAction());
		actionMap.put(Action.RotateRight, new RotateRightAction());
		actionMap.put(Action.Continue, new ContinueAction());
		actionMap.put(Action.Quit, new QuitAction());
		actionMap.put(Action.PlayerUpdate, new PlayerUpdateAction());
		actionMap.put(Action.MoveZombie, new MoveZombieAction());
		actionMap.put(Action.PlaceZombie, new PlaceZombieAction());
		actionMap.put(Action.Surrender, new SurrenderAction());
		actionMap.put(Action.Save, new SaveAction());
		actionMap.put(Action.Load, new LoadAction());
		actionMap.put(Action.useAmmo, new UseAmmoAction());
		actionMap.put(Action.useLife, new UseLifeAction());
	}

	/**
	 * Writes the game object to the given file.
	 * 
	 * @param file
	 *            file to save game to
	 */
	public void saveGame(File file) {
		FileOutputStream fileStream;
		ObjectOutputStream objectStream;

		try {
			fileStream = new FileOutputStream(file);
			objectStream = new ObjectOutputStream(fileStream);

			// write IGame object to file
			objectStream.writeObject(game);
			objectStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Loads a game object from a file.
	 * 
	 * @param file
	 *            file to load game from
	 */
	public void loadGame(File file) {
		FileInputStream fileStream;
		ObjectInputStream objectStream;

		try {
			fileStream = new FileInputStream(file);
			objectStream = new ObjectInputStream(fileStream);

			// read IGame object from file
			game = (IGame) objectStream.readObject();

			objectStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Switch to GameMenu
		window.setMenu(new GameMenu(window, game));

		// Make sure the Game has an Observer
		if (game instanceof Game) {
			((Game) game).addObserver((Observer) window);
		} else if (game instanceof GameCoop) {
			((GameCoop) game).addObserver((Observer) window);
		}

		// Enable game specific menu items
		window.getSaveItem().setEnabled(true);
		window.getSurrenderItem().setEnabled(true);
		window.getQuitItem().setEnabled(true);

		// Make sure all Window and Game references are up to date
		game.setWindow(window);
		game.getCurrentState().setGame(game);
		game.getCurrentState().setWindow(window);
		game.getCurrentState().enter();

		// Update the View
		game.update(Update.PlayerChange);
		game.update(Update.StateChanged);
		game.update(Update.PlayerMoved);

		if (game.getCurrentTile() != null) {
			game.update(Update.TileDrawn);
		}
	}

	/**
	 * ActionListener for the "Show Rules" menu.
	 * 
	 * @author Buddy Jonte
	 */
	class RulesAction implements ActionListener {
		/**
		 * Opens the rules popup.
		 * 
		 * @param e
		 *            event that caused this method to be called
		 */
		@Override
		public void actionPerformed(final ActionEvent e) {
			Popups.rules(window.getFrame(), gameRules);
		}
	}

	/**
	 * ActionListener for the save menu.
	 * 
	 * @author Buddy Jonte
	 */
	class SaveAction implements ActionListener {
		/**
		 * Prompts the player to choose a file to save the game to. Writes the
		 * game object if a file is selected.
		 * 
		 * @param e
		 *            event that caused this method to be called
		 */
		@Override
		public void actionPerformed(final ActionEvent e) {
			JFileChooser chooser = new JFileChooser(new File("./saves/"));

			// only show .jzi files
			chooser.setFileFilter(new FileNameExtensionFilter("JZI Save Game",
					"jzi"));

			if (chooser.showSaveDialog(window.getFrame()) == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				String name = file.getAbsolutePath();

				// Append .jzi if missing
				if (!name.substring(name.lastIndexOf('.') + 1).toLowerCase()
						.equals("jzi")) {
					file = new File(name.concat(".jzi"));
				}

				saveGame(file);
			}
		}
	}

	/**
	 * ActionListener for the load menu.
	 * 
	 * @author Buddy Jonte
	 */
	class LoadAction implements ActionListener {
		/**
		 * Prompts the user to select a save file. Tries to load the game object
		 * and sets up the GUI if a file is selected.
		 * 
		 * @param e
		 *            event that caused this method to be called
		 */
		@Override
		public void actionPerformed(final ActionEvent e) {
			JFileChooser chooser = new JFileChooser(new File("./saves/"));

			// only show .jzi files
			chooser.setFileFilter(new FileNameExtensionFilter("JZI Save Game",
					"jzi"));

			if (chooser.showOpenDialog(window.getFrame()) == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();

				loadGame(file);
			}
		}
	}

	/**
	 * ActionListener for the surrender menu item.
	 * 
	 * @author Buddy Jonte
	 */
	class SurrenderAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			game.surrender();
		}
	}

	/**
	 * ActionListener for the quit menu item.
	 * 
	 * @author Buddy Jonte
	 */
	class QuitAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			game = null;

			window.setMenu(new MainMenu(window));

			// Game is finished, disable game menu items
			window.getSaveItem().setEnabled(false);
			window.getSurrenderItem().setEnabled(false);
			window.getQuitItem().setEnabled(false);
		}
	}

	/**
	 * ActionListener for the {@link MainMenu} start button.
	 * 
	 * @author Buddy Jonte
	 * 
	 */
	class NewGameAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			game = new Game(new LinkedList<>(parser.returnTileList()));
			game.setWindow(window);
			game.addObserver((Observer) window);
			window.setMenu(new SetupMenu(window, game));
		}
	}

	/**
	 * ActionListener for the {@link MainMenu} Co-Op button.
	 * 
	 * @author Buddy Jonte
	 * 
	 */
	class CoopAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			game = new GameCoop(new LinkedList<>(parser.returnTileList()));
			game.setWindow(window);
			game.addObserver((Observer) window);
			window.setMenu(new SetupMenu(window, game));
		}
	}

	/**
	 * ActionListener for the {@link MainMenu} Exit button.
	 * 
	 * @author Buddy Jonte
	 * 
	 */
	class ExitAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			System.exit(0);
		}
	}

	/**
	 * ActionListener for the {@link SetupMenu} back button.
	 * 
	 * @author Buddy Jonte
	 * 
	 */
	class AddBackAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			window.setMenu(new MainMenu(window));
		}
	}

	/**
	 * ActionListener for the {@link SetupMenu} play button.
	 * 
	 * @author Buddy Jonte
	 */
	class StartAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			SetupMenu menu = (SetupMenu) window.getMenu();

			// Set game parameters according to GUI values
			game.setZombification(menu.getZombificationBox().isSelected());

			if (game.isCoop()) {
				game.setWinThreshold((int) menu.getThresholdSpinner()
						.getValue());
				game.setStartAmmo((int) menu.getAmmoSpinner().getValue());

				window.getSurrenderItem().setEnabled(false);
			} else {
				game.setWinThreshold((int) menu.getThresholdSpinner()
						.getValue());
				game.setRevives((int) menu.getReviveSpinner().getValue());
				game.setHardcore(menu.getHardcoreBox().isSelected());

				window.getSurrenderItem().setEnabled(true);
			}

			window.setMenu(new GameMenu(window, game));

			// Enable game specific menu items
			window.getSaveItem().setEnabled(true);
			window.getQuitItem().setEnabled(true);

			// and start game
			game.setUp();
			game.setState(new TileState(window));
			game.update(Update.PlayerChange);
		}
	}

	/**
	 * ActionListener for the {@link SetupMenu} "Add Player" button.
	 * 
	 * @author Buddy Jonte
	 * 
	 */
	class AddPlayerAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			SetupMenu menu = (SetupMenu) window.getMenu();
			String name = menu.getNameField().getText();
			Color color = menu.getColorElement().getCurrentColor();
			IPlayer player = new Player(name, color);

			if (!game.isPlayerValid(name, color)) {
				return;
			}

			game.addPlayer(player);

			// enable the "play" button if game is ready
			menu.getPlayButton().setEnabled(game.isReady());

			// update the GUI if max players are reached
			if (game.getPlayers().size() == Game.MAX_PLAYERS) {
				menu.getAddPlayerMenu().remove(menu.getNewPanel());
				menu.getAddPlayerButton().setEnabled(false);
			}
		}
	}

	/**
	 * ActionListener for the {@link GameMenu} "Roll Die" button.
	 * 
	 * @author Buddy Jonte
	 */
	class RollAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			game.rollDie();
			game.getCurrentState().rollAction();
			game.update(Update.Map);
		}
	}

	/**
	 * ActionListener for the {@link GameMenu} "Move" radio button.
	 * 
	 * @author Buddy Jonte
	 */
	class MoveZombieAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			((ZombieState) game.getCurrentState())
					.setZombieMode(ZombieMode.Move);
			game.update(Update.Map);
		}
	}

	/**
	 * ActionListener for the {@link GameMenu} "Place" radio button.
	 * 
	 * @author Buddy Jonte
	 */
	class PlaceZombieAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			((ZombieState) game.getCurrentState())
					.setZombieMode(ZombieMode.Place);
			game.setCurrentZombie(null);
			game.update(Update.Map);
		}
	}

	/**
	 * ActionListener for the {@link GameMenu} "Draw Tile" button.
	 * 
	 * @author Buddy Jonte
	 */
	class DrawAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			game.getCurrentState().drawAction();
		}
	}

	/**
	 * ActionListener for the {@link GameMenu} "Continue" button.
	 * 
	 * @author Buddy Jonte
	 */
	class ContinueAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			game.getCurrentState().continueAction();
		}
	}

	/**
	 * ActionListener for the {@link GameMenu} "Rotate Left" button.
	 * 
	 * @author Buddy Jonte
	 */
	class RotateLeftAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			ITile tile = game.getCurrentTile();

			if (tile != null) {
				tile.leftRotation();
				game.update(Update.TileRotated);
			}
		}
	}

	/**
	 * ActionListener for the {@link GameMenu} "Rotate Right" button.
	 * 
	 * @author Buddy Jonte
	 */
	class RotateRightAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			ITile tile = game.getCurrentTile();

			if (tile != null) {
				tile.rightRotation();
				game.update(Update.TileRotated);
			}
		}
	}

	/**
	 * MouseAdapter for the {@link GameMenu} map.
	 * 
	 * @author Buddy Jonte
	 * 
	 */
	class MapAction extends MouseAdapter {
		/**
		 * Transforms the clicked point to field coordinates and calles the
		 * current state's map action.
		 * 
		 * @param e
		 *            Mouse event that caused this method to be called
		 */
		@Override
		public void mouseClicked(final MouseEvent e) {
			Point2D point = ((GameMenu) window.getMenu()).getMapPane()
					.getLastPoint();
			game.getCurrentState().mapAction(Coordinates.fieldFromPoint(point));
		}
	}

	/**
	 * ActionListener/DocumentListener for the {@link SetupMenu}.
	 * 
	 * @author Bud
	 * 
	 */
	class PlayerUpdateAction implements ActionListener, DocumentListener {

		@Override
		public void actionPerformed(final ActionEvent arg0) {
			SetupMenu menu = (SetupMenu) window.getMenu();
			String name = menu.getNameField().getText();
			Color color = menu.getColorElement().getCurrentColor();

			menu.getAddPlayerButton().setEnabled(
					game.isPlayerValid(name, color));
		}

		@Override
		public void changedUpdate(final DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(final DocumentEvent arg0) {
			SetupMenu menu = (SetupMenu) window.getMenu();
			String name = menu.getNameField().getText();
			Color color = menu.getColorElement().getCurrentColor();

			menu.getAddPlayerButton().setEnabled(
					game.isPlayerValid(name, color));
		}

		@Override
		public void removeUpdate(final DocumentEvent arg0) {
			insertUpdate(arg0);
		}
	}

	/**
	 * ActionListener for the "Use Ammo" button.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	class UseAmmoAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			game.getCurrentPlayer()
					.setAmmo(
							game.getCurrentPlayer().getAmmo()
									- game.getDieDifference());
			((GameMenu) window.getMenu()).getDie().setIcon(
					DieGraphic.getDiefromInt(game.getDie()
							+ game.getDieDifference()));

			((FightState) game.getCurrentState()).fightWon(game
					.getCurrentPlayer());

		}
	}

	/**
	 * ActionListener for the "Use Life" button.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	class UseLifeAction implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {

			game.getCurrentPlayer().setLives(
					game.getCurrentPlayer().getLives() - 1);
			game.update(Update.PlayerAttributeUpdate);

			if (game.getCurrentPlayer().getLives() == 0) {
				game.setState(new ZombieState(window));
				game.revive();
			} else {
				((GameMenu) window.getMenu()).getDie().setIcon(null);
				((GameMenu) window.getMenu()).getRollDieButton().setEnabled(
						true);
				game.update(Update.FightPanelUpdate);
				game.update(Update.DisableFightPanel);
			}
		}
	}

	/**
	 * Reads in the game rules.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class TextData extends File {
		/**
		 * UID for serialization.
		 */
		private static final long serialVersionUID = -5120743210052767035L;

		/**
		 * constructor of test data.
		 * 
		 * @param path
		 */

		/**
		 * Constructor with path, calls appropriate super class constructor.
		 * 
		 * @param path
		 *            path of file to read
		 */
		public TextData(final String path) {
			super(path);
		}

		/**
		 * reads the data.
		 * 
		 * @return gameRules
		 */

		/**
		 * Reads the file line by line.
		 * 
		 * @return read text
		 */
		public String read() {
			String text = "";

			try {
				String row = "";

				BufferedReader reader = new BufferedReader(new FileReader(
						getPath()));

				while ((row = reader.readLine()) != null) {
					text = text + row + "\n";
				}
				reader.close();

			} catch (IOException e) {
				e.printStackTrace();

			}

			return text;
		}
	}
}
