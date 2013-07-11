package jzi.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;
import jzi.model.entities.IPlayer;
import jzi.model.game.GameCoop;
import jzi.model.game.IGame;

/**
 * GUI class for the player menu
 * 
 * @author Buddy Jonte
 */
public class SetupMenu extends JPanel implements Menu, ActionListener {
	/**
	 * UID serialization.
	 */
	private static final long serialVersionUID = -3515199553779455103L;

	/**
	 * the created window of the game.
	 */

	private IWindow window;

	/**
	 * Button to add player.
	 */

	private JButton addPlayer;

	/**
	 * Button to start playing.
	 */
	private JButton play;

	/**
	 * Button to go back to the previous window.
	 */
	private JButton back;

	/**
	 * Update the map after each action.
	 */

	private HashMap<Update, ViewUpdate> updateMap;

	/**
	 * List of color.
	 */

	private LinkedList<Color> listModel = new LinkedList<Color>();

	/**
	 * Panel for each Player.
	 */

	private JPanel playerPanel;

	/**
	 * New Panel.
	 */
	private JPanel newPanel;

	/**
	 * Setting panel.
	 */

	private JPanel settingsPanel;

	/**
	 * Controll panel.
	 */
	private JPanel controlPanel;

	/**
	 * the minimum number of
	 */

	private JLabel thresholdLabel;
	private SpinnerModel standardModel = new SpinnerNumberModel(20, 1, null, 1);
	private SpinnerModel coopModel = new SpinnerNumberModel(50, 1, null, 1);
	private JSpinner threshold;

	private JLabel revivesLabel;
	private SpinnerModel revivesModel = new SpinnerNumberModel(2, 0, null, 1);
	private JSpinner revives = new JSpinner(revivesModel);

	/**
	 * Zombification modus.
	 */

	private JCheckBox zombification;

	/**
	 * Hardcore modus.
	 */
	private JCheckBox hardcore;

	/**
	 * Ammo's label.
	 */

	private JLabel ammoLabel;
	private SpinnerModel ammoModel = new SpinnerNumberModel(3, 0, null, 1);
	private JSpinner ammo = new JSpinner(ammoModel);

	/**
	 * Text field of 20 signs for the player's name.
	 */

	private JTextField name = new JTextField(20);

	/**
	 * color for the player.
	 */
	private ColorElement color;

	/**
	 * Add player menu.
	 */
	public SetupMenu(IWindow window, IGame game) {
		this.window = window;

		createLabels();
		setColor();

		setLayout(new MigLayout("align center center, wrap 1", "[]0[]", "[]0[]"));

		settingsPanel = new JPanel(new MigLayout());
		playerPanel = new JPanel(new MigLayout("wrap 2"));
		newPanel = new JPanel(new MigLayout());
		controlPanel = new JPanel(new MigLayout());

		updateMap = new HashMap<>();
		updateMap.put(Update.PlayerAdded, new PlayerAddedUpdate());
		updateMap.put(Update.ChangeLanguage, new ChangeLanguageUpdate());

		color = new ColorElement(listModel);

		addPlayer.setEnabled(false);
		revives.setEnabled(false);
		play.setEnabled(false);

		setupListeners();

		newPanel.add(name);
		newPanel.add(color, "wrap");
		newPanel.add(addPlayer);

		if (game instanceof GameCoop) {
			threshold = new JSpinner(coopModel);
			settingsPanel.add(ammoLabel);
			settingsPanel.add(ammo, "width :60:, wrap");
		} else {
			threshold = new JSpinner(standardModel);
			settingsPanel.add(zombification, "wrap");
			settingsPanel.add(hardcore, "wrap");
			settingsPanel.add(revivesLabel);
			settingsPanel.add(revives, "width :60:, wrap");
		}

		settingsPanel.add(thresholdLabel);
		settingsPanel.add(threshold, "width :60:, wrap");

		controlPanel.add(back);
		controlPanel.add(play);
		
		setText();

		add(playerPanel);
		add(newPanel);
		add(settingsPanel);
		add(controlPanel);
	}

	private void setText() {
		back.setText(Language.get("setup.back"));
		thresholdLabel.setText(Language.get("setup.wins"));
		revivesLabel.setText(Language.get("setup.revives"));
		zombification.setText(Language.get("setup.zombification"));
		hardcore.setText(Language.get("setup.hardcore"));
		ammoLabel.setText(Language.get("setup.ammo"));
		addPlayer.setText(Language.get("setup.add"));
		play.setText(Language.get("setup.start"));
	}
	
	/**
	 * Creates Labels.
	 */
	private void createLabels() {
		back = new JButton();
		thresholdLabel = new JLabel();
		revivesLabel = new JLabel();
		zombification = new JCheckBox();
		hardcore = new JCheckBox();
		ammoLabel = new JLabel();
		addPlayer = new JButton();
		play = new JButton();
	}

	/**
	 * Set color for the player.
	 */
	private void setColor() {
		listModel.add(Color.BLUE);
		listModel.add(Color.RED);
		listModel.add(Color.GREEN);
		listModel.add(Color.YELLOW);
		listModel.add(Color.WHITE);
		listModel.add(Color.ORANGE);
	}

	private void setupListeners() {
		back.addActionListener(window.getActionListener(Action.AddBack));
		play.addActionListener(window.getActionListener(Action.Start));
		addPlayer.addActionListener(window.getActionListener(Action.AddPlayer));
		name.addActionListener(window.getActionListener(Action.AddPlayer));
		name.getDocument().addDocumentListener(
				window.getDocumentListener(Action.PlayerUpdate));
		color.addActionListener(window.getActionListener(Action.PlayerUpdate));
		hardcore.addActionListener(this);
	}

	/**
	 * Gets the threshold's spinner.
	 * 
	 * @return threshold.
	 */

	public JSpinner getThresholdSpinner() {
		return threshold;
	}

	/**
	 * Gets the revive's spinner.
	 * 
	 * @return revive.
	 */

	public JSpinner getReviveSpinner() {
		return revives;
	}

	/**
	 * Gets the Ammo's spinner.
	 * 
	 * @return Ammo.
	 */
	public JSpinner getAmmoSpinner() {
		return ammo;
	}

	/**
	 * Gets the back button.
	 * 
	 * @return back.
	 */
	public JButton getBackButton() {
		return back;
	}

	/**
	 * Gets the zombification's checkbox.
	 * 
	 * @return zombification.
	 */

	public JCheckBox getZombificationBox() {
		return zombification;
	}

	/**
	 * Gets the hardcore's checkbox.
	 * 
	 * @return hardcore.
	 */

	public JCheckBox getHardcoreBox() {
		return hardcore;
	}

	/**
	 * Gets add player's button.
	 * 
	 * @return addPlayer.
	 */

	public JButton getAddPlayerButton() {
		return addPlayer;
	}

	/**
	 * Gets the play button.
	 * 
	 * @return play.
	 */

	public JButton getPlayButton() {
		return play;
	}

	/**
	 * Gets the model's color.
	 * 
	 * @return listModel.
	 */
	public LinkedList<Color> getModel() {
		return listModel;
	}

	/**
	 * Get the player menu handle
	 * 
	 * @return JPanel handle
	 */
	public JPanel getAddPlayerMenu() {
		return this;
	}

	/**
	 * 
	 * @param o
	 * @param arg
	 */

	@Override
	public void update(Observable o, Object arg) {
		updateMap.get((Update) arg).execute(o);
	}

	/**
     * 
     */

	@Override
	public JPanel getPanel() {
		return this;
	}

	/**
	 * Gets the field's name.
	 * 
	 * @return name
	 */

	public JTextField getNameField() {
		return name;
	}

	/**
	 * Gets the selected color.
	 * 
	 * @return color.
	 */
	public ColorElement getColorElement() {
		return color;
	}

	/**
	 * Gets new Panel.
	 * 
	 * @return newPanel.
	 */

	public JPanel getNewPanel() {
		return newPanel;
	}

	private class PlayerAddedUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			IGame game = (IGame) o;
			IPlayer player = game.getPlayers().getLast();

			JLabel newName = new JLabel(player.getName());
			JLabel newColor = new JLabel();

			newColor.setOpaque(true);

			newName.setMinimumSize(new Dimension(200, 20));
			newColor.setMinimumSize(new Dimension(40, 20));

			newColor.setBackground(player.getColor());

			playerPanel.add(newName, "width :240:");
			playerPanel.add(newColor, "width :60:");

			addPlayer.setEnabled(false);
			name.setText("");
			name.requestFocusInWindow();
			color.removeColor(player.getColor());
			revalidate();
			repaint();
		}
	}

	/**
	 * Change the language of a Game
	 */
	private class ChangeLanguageUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			setText();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		revives.setEnabled(hardcore.isSelected());
	}
}
