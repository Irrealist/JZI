package jzi.view;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.HashMap;

import net.miginfocom.swing.MigLayout;
import jzi.controller.state.FightState;
import jzi.controller.state.IState;
import jzi.controller.state.PlayerState;
import jzi.controller.state.TileState;
import jzi.controller.state.ZombieState;
import jzi.model.Game;
import jzi.model.IGame;
import jzi.model.IPlayer;
import jzi.model.map.ITile;

/**
 * GUI class for the game screen.
 * 
 * @author Buddy Jonte
 */
public class GameMenu implements Menu {
	/**
	 * The menu's game handle.
	 */
	private IGame game;
	/**
	 * JPanel for this menu.
	 */
	private JPanel gameMenu = new JPanel();
	/**
	 * Map pane for this menu.
	 */
	private MapPane mapPane;
	/**
	 * Control panel, contains components for controlling game flow.
	 */
	private JPanel controlPanel = new JPanel();
	/**
	 * Tile panel, contains the current tile and rotation button.
	 */
	private JPanel tilePanel = new JPanel();
	/**
	 * Roll panel, contains components related to rolling the die.
	 */
	private JPanel rollPanel = new JPanel();
	/**
	 * Player panel, contains player information.
	 */
	private JPanel playerPanel = new JPanel();
	/**
	 * Move panel, contains components related to zombie movement.
	 */
	private JPanel movePanel = new JPanel();
	/**
	 * Current player panel, contains information related to the current player.
	 */
	private JPanel currentPlayerPanel = new JPanel();
	/**
	 * All player panel, contains information about all players.
	 */
	private JPanel allPlayerPanel = new JPanel();
	/**
	 * Fight panel, contains components related to fights.
	 */
	private JPanel fightPanel = new JPanel();
	/**
	 * Label for the current player's name.
	 */
	private JLabel currentPlayer;
	/**
	 * Label for the current player's color.
	 */
	private JLabel currentPlayerColor;
	/**
	 * Scroll pane for all players.
	 */
	private JScrollPane scrollPane;
	/**
	 * Icon for life points.
	 */
	private JLabel lifeIcon;
	/**
	 * Icon for ammunition.
	 */
	private JLabel ammoIcon;
	/**
	 * Icon for zombie kills.
	 */
	private JLabel zombieIcon;
	/**
	 * Label for the current tile.
	 */
	private JLabel tileStack = new JLabel();
	/**
	 * Label for the number of points.
	 */
	private JLabel points;
	/**
	 * Label for the current state.
	 */
	private JLabel state = new JLabel();
	/**
	 * Button for rolling.
	 */
	private JButton rollDie;
	/**
	 * Button for drawing tiles.
	 */
	private JButton drawTile;
	/**
	 * Button for using ammunition.
	 */
	private JButton useAmmo;
	/**
	 * Button for using life points.
	 */
	private JButton useLife;
	/**
	 * Table that contains player information.
	 */
	private Table allPlayerTable;
	/**
	 * Button for left rotation.
	 */
	private JButton rotateLeft;
	/**
	 * Button for right rotation.
	 */
	private JButton rotateRight;
	/**
	 * Button for moving to the next state.
	 */
	private JButton cont;
	/**
	 * Radio button for zombie movement.
	 */
	private JRadioButton moveZombie;
	/**
	 * Radio button for zombie placement.
	 */
	private JRadioButton placeZombie;
	/**
	 * Label containing the die value.
	 */
	private JLabel die;
	/**
	 * Label for the player's ammunition.
	 */
	private JLabel ammo;
	/**
	 * Label for the player's life points.
	 */
	private JLabel life;
	/**
	 * Label for the number of revives the player has left.
	 */
	private JLabel revives;
	/**
	 * Panel containing player information.
	 */
	private JPanel infoPane = new JPanel();
	/**
	 * Image of an ammo.
	 */
	private BufferedImage ammoImage = null;
	/**
	 * Image of a life.
	 */
	private BufferedImage lifeImage = null;
	/**
	 * Image of a zombie.
	 */
	private BufferedImage zombieImage = null;
	/**
	 * The window of the program.
	 */
	private IWindow window;
	/**
	 * HashMap for update.
	 */
	private HashMap<Update, ViewUpdate> updateMap;

	private int ammoDelta = 0;

	/**
	 * Constructor, initializes the menu.
	 * 
	 * @param window
	 *            window instance
	 * @param game
	 *            game instance
	 */
	public GameMenu(IWindow window, IGame game) {
		this.game = game;
		this.window = window;

		createUpdateMap();
		readImages();
		setLayouts();
		createLabelsandButtons(game);
		setIcons();
		addActionListener();

		rollDie.setEnabled(false);
		drawTile.setEnabled(false);
		rotateLeft.setEnabled(false);
		rotateRight.setEnabled(false);
		cont.setEnabled(false);
		moveZombie.setEnabled(false);
		placeZombie.setEnabled(false);

		tileStack.setPreferredSize(new Dimension(150, 150));
		die.setPreferredSize(new Dimension(100, 100));

		LinkedList<IPlayer> players = game.getPlayers();
		for (int n = 0; n < players.size(); n++) {
			playerPanel.add(new JLabel(players.get(n).getName()));
			JLabel colorLabel = new JLabel();
			colorLabel.setOpaque(true);
			colorLabel.setMinimumSize(new Dimension(60, 20));
			colorLabel.setBackground(players.get(n).getColor());
			playerPanel.add(colorLabel);
		}

		setText();

		addToPanel();

		mapPane = new MapPane(game, lifeImage, ammoImage, zombieImage);
		mapPane.addMouseListener(window.getMouseListener(Action.Map));

		gameMenu.add(mapPane, "span 1 8");
		gameMenu.add(currentPlayerPanel, "wrap");
		gameMenu.add(controlPanel, "wrap");
		gameMenu.add(infoPane, "wrap");
		gameMenu.add(fightPanel, "wrap");
		gameMenu.add(rollPanel, "wrap");
		gameMenu.add(movePanel, "wrap");
		gameMenu.add(tilePanel, "wrap");
		gameMenu.add(allPlayerPanel, "wrap");
		fightPanel.setVisible(false);
		mapPane.repaint();
	}

	/**
	 * set Layouts for all Panels.
	 */
	private void setLayouts() {
		gameMenu.setLayout(new MigLayout("hidemode 3"));
		controlPanel.setLayout(new MigLayout());
		tilePanel.setLayout(new MigLayout());
		playerPanel.setLayout(new MigLayout());
		currentPlayerPanel.setLayout(new MigLayout());
		rollPanel.setLayout(new MigLayout());
		infoPane.setLayout(new MigLayout());
		movePanel.setLayout(new MigLayout());
		allPlayerPanel.setLayout(new MigLayout());
		fightPanel.setLayout(new MigLayout());
	}

	/**
	 * add all Labels,Buttons to Panels.
	 */
	private void addToPanel() {
		currentPlayerPanel.add(currentPlayer);
		currentPlayerPanel.add(currentPlayerColor, "wrap");

		infoPane.add(life);
		infoPane.add(lifeIcon);
		infoPane.add(ammo);
		infoPane.add(ammoIcon);
		infoPane.add(revives);
		infoPane.add(points);
		infoPane.add(zombieIcon, "wrap");
		controlPanel.add(state);
		controlPanel.add(cont, "wrap");

		fightPanel.add(useAmmo, "wrap");
		fightPanel.add(useLife);

		rollPanel.add(rollDie, "wrap");
		rollPanel.add(die, "wrap");
		allPlayerPanel.setMinimumSize(new Dimension(250, 150));
		allPlayerPanel.add(scrollPane);

		movePanel.add(moveZombie);
		movePanel.add(placeZombie, "wrap");

		tilePanel.add(tileStack, "wrap");
		tilePanel.add(drawTile, "wrap");
		tilePanel.add(rotateLeft, "wrap");
		tilePanel.add(rotateRight, "wrap");
	}

	/**
	 * create Classes for update.
	 */
	private void createUpdateMap() {
		updateMap = new HashMap<>();
		updateMap.put(Update.TileDrawn, new TileDrawnUpdate());
		updateMap.put(Update.TilePlaced, new TilePlacedUpdate());
		updateMap.put(Update.TileRotated, new TileRotatedUpdate());
		updateMap.put(Update.DieRolled, new DieRolledUpdate());
		updateMap.put(Update.StateChanged, new StateUpdate());
		updateMap.put(Update.PlayerChange, new PlayerChangeUpdate());
		updateMap.put(Update.PlayerMoved, new MapUpdate());
		updateMap.put(Update.ZombieMoved, new MapUpdate());
		updateMap.put(Update.Map, new MapUpdate());
		updateMap
				.put(Update.PlayerAttributeUpdate, new PlayerAttributeUpdate());
		updateMap.put(Update.FightLost, new FightLostUpdate());
		updateMap.put(Update.FightWon, new FightWonUpdate());
		updateMap.put(Update.EnoughAmmo, new EnoughAmmoUpdate());
		updateMap.put(Update.EnoughLife, new DisableLifeButtonUpdate());
		updateMap.put(Update.DisableFightPanel, new DisableFightPanelUpdate());
		updateMap.put(Update.FightPanelUpdate, new FightPanelUpdate());
		updateMap.put(Update.ChangeLanguage, new ChangeLanguage());
	}

	/**
	 * add ActionListener to Buttons.
	 */
	private void addActionListener() {
		rollDie.addActionListener(window.getActionListener(Action.Roll));
		drawTile.addActionListener(window.getActionListener(Action.Draw));
		rotateLeft.addActionListener(window
				.getActionListener(Action.RotateLeft));
		rotateRight.addActionListener(window
				.getActionListener(Action.RotateRight));
		cont.addActionListener(window.getActionListener(Action.Continue));
		moveZombie.addActionListener(window
				.getActionListener(Action.MoveZombie));
		placeZombie.addActionListener(window
				.getActionListener(Action.PlaceZombie));
		useAmmo.addActionListener(window.getActionListener(Action.useAmmo));
		useLife.addActionListener(window.getActionListener(Action.useLife));
	}

	/**
	 * Loads images to be used in the game.
	 */
	private void readImages() {
		try {
			ammoImage = ImageIO.read(new File("./data/img/obj/Ammo.png"));
			lifeImage = ImageIO.read(new File("./data/img/obj//Life.png"));
			zombieImage = ImageIO.read(new File("./data/img/obj/Zombie.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * create Labels and Buttons.
	 * 
	 * @param game
	 */
	private void createLabelsandButtons(IGame game) {
		ButtonGroup zombieGroup = new ButtonGroup();

		rollDie = new JButton();
		drawTile = new JButton();
		rotateLeft = new JButton();
		rotateRight = new JButton();
		cont = new JButton();
		moveZombie = new JRadioButton("", true);
		placeZombie = new JRadioButton("", false);
		die = new JLabel();
		createTable();
		lifeIcon = new JLabel();
		ammoIcon = new JLabel();
		zombieIcon = new JLabel();
		useAmmo = new JButton();
		useLife = new JButton();
		currentPlayer = new JLabel();
		currentPlayerColor = new JLabel();
		currentPlayerColor.setMinimumSize(new Dimension(60, 20));
		currentPlayerColor.setOpaque(true);
		ammo = new JLabel("0");
		life = new JLabel("0");
		points = new JLabel("0");
		revives = new JLabel();

		zombieGroup.add(moveZombie);
		zombieGroup.add(placeZombie);
	}

	/**
	 * create a Table for specific game mode.
	 */
	private void createTable() {
		if (game.isHardcore()) {
			Object[] columnNames = { Language.get("game.player.name"),
					Language.get("game.player.color"), "", "", "",
					Language.get("game.player.revives") };
			allPlayerTable = new Table(new DefaultTableModel(columnNames, game
					.getPlayers().size()), ammoImage, lifeImage, zombieImage,
					game);

		} else {
			Object[] columnNames = { Language.get("game.player.name"),
					Language.get("game.player.color"), "", "", "" };
			allPlayerTable = new Table(new DefaultTableModel(columnNames, game
					.getPlayers().size()), ammoImage, lifeImage, zombieImage,
					game);
		}
		scrollPane = new JScrollPane(allPlayerTable);
	}

	/**
	 * set Icons to Lables.
	 */
	private void setIcons() {
		Image scaleAmmoImage = ammoImage.getScaledInstance(30, 30, 0);
		Image scaleLifeImage = lifeImage.getScaledInstance(30, 30, 0);
		Image scaleZombieImage = zombieImage.getScaledInstance(30, 30, 0);

		lifeIcon.setIcon(new ImageIcon(scaleLifeImage));
		ammoIcon.setIcon(new ImageIcon(scaleAmmoImage));
		zombieIcon.setIcon(new ImageIcon(scaleZombieImage));
	}

	/**
	 * Returns the game menu handle.
	 * 
	 * @return JPanel game menu
	 */
	public JPanel getGameMenu() {
		return gameMenu;
	}

	/**
	 * get the MapPane.
	 * 
	 * @return mapPane
	 */
	public MapPane getMapPane() {
		return mapPane;
	}

	/**
	 * Gets the zombie movement button
	 * 
	 * @return button
	 */
	public JRadioButton getMoveZombieButton() {
		return moveZombie;
	}

	/**
	 * Gets the zombie placement button.
	 * 
	 * @return button
	 */
	public JRadioButton getPlaceZombieButton() {
		return placeZombie;
	}

	/**
	 * Gets the "roll die" button.
	 * 
	 * @return button
	 */
	public JButton getRollDieButton() {
		return rollDie;
	}

	/**
	 * Gets the die value label.
	 * 
	 * @return label
	 */
	public JLabel getDie() {
		return die;
	}

	/**
	 * Gets the "draw tile" button.
	 * 
	 * @return button
	 */
	public JButton getDrawTileButton() {
		return drawTile;
	}

	public JLabel getTileStack() {
		return tileStack;
	}

	public JButton getRotateLeftButton() {
		return rotateLeft;
	}

	public JButton getRotateRightButton() {
		return rotateRight;
	}

	public JButton getContinueButton() {
		return cont;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (updateMap.get((Update) arg) != null) {
			updateMap.get((Update) arg).execute(o);
		}
	}

	/**
	 * Gets the current game panel.
	 * 
	 * @return panel
	 */
	@Override
	public JPanel getPanel() {
		return gameMenu;
	}

	/**
	 * Updates the current player.
	 * 
	 * @param game
	 *            calling game instance
	 * 
	 */
	private void updateCurrentPlayer(IGame game) {
		for (int i = 0; i < game.getPlayers().size(); i++) {
			allPlayerTable.setRow(i, game.getPlayers().get(i));
		}

		IPlayer player = game.getCurrentPlayer();
		currentPlayer.setText(player.getName());
		currentPlayerColor.setBackground(player.getColor());
		ammo.setText(new Integer(player.getAmmo()).toString());
		points.setText(new Integer(player.getPoints()).toString());

		if (player.getLives() == 1) {
			life.setText("1");
		} else {
			life.setText(new Integer(player.getLives()).toString());
		}

		if (game.isHardcore()) {
			revives.setText(String.format(Language.get("game.revives"),
					game.getRevives() - player.getRevives()));
		}

		if (game.getCurrentState() instanceof PlayerState) {
			die.setIcon(DieGraphic.getDiefromInt(player.getSteps()));
		} else if (game.getCurrentState() instanceof ZombieState) {
			die.setIcon(DieGraphic.getDiefromInt(player.getZombies()));
		}
	}

	/**
	 * Update that gets called when a tile is drawn.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class TileDrawnUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			ITile tile = ((Game) o).getCurrentTile();

			if (tile == null) {
				tileStack.setIcon(null);
			} else {
				tileStack.setIcon(TileGraphic.getIcon(tile));
			}

			drawTile.setEnabled(false);
			rotateLeft.setEnabled(true);
			rotateRight.setEnabled(true);
			gameMenu.revalidate();
			gameMenu.repaint();
		}
	}

	/**
	 * Update that gets called when the current tile is rotate.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class TileRotatedUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			ITile tile = ((Game) o).getCurrentTile();
			tileStack.setIcon(TileGraphic.getIcon(tile));
		}
	}

	/**
	 * Update that gets called when the state changes.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class StateUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			IState current = ((Game) o).getCurrentState();

			if (current instanceof TileState) {
				state.setText(Language.get("game.mode.tile"));
				tilePanel.setVisible(true);
				rollPanel.setVisible(false);
				fightPanel.setVisible(false);
				infoPane.setVisible(false);
				movePanel.setVisible(false);
			}

			if (current instanceof PlayerState) {
				state.setText(Language.get("game.mode.move"));
				tilePanel.setVisible(false);
				rollPanel.setVisible(true);
				fightPanel.setVisible(false);
				infoPane.setVisible(false);
				movePanel.setVisible(false);
			}

			if (current instanceof FightState) {
				mapPane.focus(game.getCurrentPlayer().getCoordinates());
				state.setText(Language.get("game.mode.fight"));
				tilePanel.setVisible(false);
				rollPanel.setVisible(true);
				game.update(Update.PlayerAttributeUpdate);
				revives.setVisible(true);
				infoPane.setVisible(true);
				movePanel.setVisible(false);
			}

			if (current instanceof ZombieState) {
				state.setText(Language.get("game.mode.zombie"));
				tilePanel.setVisible(false);
				rollPanel.setVisible(true);
				fightPanel.setVisible(false);
				infoPane.setVisible(false);
				movePanel.setVisible(true);
			}
		}
	}

	/**
	 * Update that gets called when the die is rolled.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class DieRolledUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			die.setIcon(DieGraphic.getDiefromInt(((Game) o).getDie()));
			rollDie.setEnabled(false);
		}
	}

	/**
	 * Update that gets called when the map needs an update.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class MapUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			mapPane.repaint();
		}
	}

	/**
	 * Update that gets called when a tile is placed.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class TilePlacedUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			// remove Tile preview
			tileStack.setIcon(null);
			tileStack.revalidate();
			mapPane.repaint();
		}
	}

	/**
	 * Update that gets called when the current player changes.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class PlayerChangeUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			updateCurrentPlayer((Game) o);
			mapPane.focus(game.getCurrentPlayer().getCoordinates());
			mapPane.repaint();
		}
	}

	/**
	 * Update that gets called when the player's attributes change.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class PlayerAttributeUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			updateCurrentPlayer((Game) o);
			mapPane.repaint();
		}
	}

	/**
	 * Update that gets called when the player loses a fight.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class FightLostUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			showFightButtons((Game) o);
		}
	}

	/**
	 * Update that gets called when the player wins a fight.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class FightWonUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			hideFightButtons((Game) o);
		}
	}

	/**
	 * Update that gets called when the player has enough ammunition.s
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class EnoughAmmoUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			enableAmmoButton((Game) o);
		}
	}

	/**
	 * Update that gets called when the fight panel needs to be removed.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class DisableFightPanelUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			disableFightPanel((Game) o);
		}
	}

	/**
	 * Update that gets called when the "use life" button needs to be updated.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class DisableLifeButtonUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			disableLifeButton((Game) o);
		}
	}

	/**
	 * Update that gets called when the fight panel needs to be updated.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class FightPanelUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			updateFightPanel((Game) o);
		}
	}

	/**
	 * Update that gets called when the language is changed.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class ChangeLanguage implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			setText();
		}
	}

	/**
	 * Shows the fight buttons.
	 * 
	 * @param game
	 *            game object calling the update
	 */
	private void showFightButtons(Game game) {
		ammoDelta = game.getDieDifference();

		useAmmo.setText(String.format(Language.get("game.fight.ammo"),
				ammoDelta));
		useAmmo.setEnabled(false);
		useLife.setText(Language.get("game.fight.life"));
		useLife.setEnabled(true);
		fightPanel.setVisible(true);
	}

	/**
	 * Disables the fight panel.
	 * 
	 * @param o
	 *            Game object calling the update
	 */
	private void disableFightPanel(Game o) {
		useAmmo.setEnabled(false);
		useLife.setEnabled(false);
	}

	/**
	 * Updates the fight panel.
	 * 
	 * @param game
	 *            game calling the update.
	 */
	private void updateFightPanel(Game game) {
		ammoDelta = game.getDieDifference();

		useAmmo.setText(String.format(Language.get("game.fight.ammo"),
				ammoDelta));
		useLife.setText(Language.get("game.fight.life"));
	}

	/**
	 * Disables the "use life" button.
	 * 
	 * @param o
	 *            Object calling the update
	 */
	private void disableLifeButton(Game o) {
		useLife.setEnabled(false);
	}

	/**
	 * Enables the ammunition button.
	 * 
	 * @param o
	 *            Object calling the update
	 */
	private void enableAmmoButton(Game o) {
		useAmmo.setEnabled(true);
	}

	/**
	 * Hides the fight buttons.
	 * 
	 * @param o
	 *            Object calling the update
	 */
	private void hideFightButtons(Game o) {
		fightPanel.setVisible(false);
	}

	private void setText() {
		rollDie.setText(Language.get("game.roll"));
		drawTile.setText(Language.get("game.draw"));
		allPlayerTable.changeLanguage();
		rotateLeft.setText(Language.get("game.rotate.left"));
		rotateRight.setText(Language.get("game.rotate.right"));
		cont.setText(Language.get("game.continue"));
		moveZombie.setText(Language.get("game.zombie.move"));
		placeZombie.setText(Language.get("game.zombie.place"));
		useAmmo.setText(String.format(Language.get("game.fight.ammo"),
				ammoDelta));
		useLife.setText(Language.get("game.fight.life"));
	}
}
