package jzi.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import jzi.model.GameCoop;
import jzi.model.IGame;
import jzi.model.IPlayer;
import net.miginfocom.swing.MigLayout;

/**
 * 
 * create WinnerMenu for the game.
 * 
 */
public class WinnerMenu extends JPanel implements Menu {
	/**
	 * serial ID.
	 */
	private static final long serialVersionUID = -2839932146818151358L;
	/**
	 * Current window instance.
	 */
	private IWindow window;
	/**
	 * win text.
	 */
	private JLabel text;
	/**
	 * Button back to MainMenu.
	 */
	private JButton back;
	/**
	 * Current game Instance.
	 */
	private IGame game;
	/**
	 * HashMap for updates.
	 */
	private HashMap<Update, ViewUpdate> updateMap;

	/**
	 * create a WinnerMenu for a specific type of game.
	 * 
	 * @param windowHandle
	 * @param game
	 */
	public WinnerMenu(IWindow windowHandle, IGame game) {
		this.window = windowHandle;
		this.game = game;

		updateMap = new HashMap<>();
		updateMap.put(Update.ChangeLanguage, new ChangeLanguageUpdate());

		setLayout(new MigLayout("align center center, wrap 1", "[center]"));

		text = new JLabel();
		back = new JButton();

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.setMenu(new MainMenu(window));
			}
		});

		add(text);

		if (game instanceof GameCoop) {
			for (IPlayer player : game.getPlayers()) {
				addPlayer(player);
			}
		} else if (game.getWinner() != null) {
			addPlayer(game.getWinner());
		}

		add(back);

		setText();
	}

	private void addPlayer(IPlayer player) {
		JLabel label = new JLabel(player.getName());
		Color color = player.getColor();
		Font font = label.getFont();

		label.setOpaque(true);
		label.setBackground(color);
		label.setPreferredSize(new Dimension(150, 30));
		label.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
		label.setHorizontalAlignment(SwingConstants.CENTER);

		if ((0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color
				.getBlue()) / 255 > 0.5) {
			label.setForeground(Color.BLACK);
		} else {
			label.setForeground(Color.WHITE);
		}

		add(label);
	}

	private void setText() {
		if (game instanceof GameCoop) {
			text.setText(Lang.get("win.coop"));
		} else {
			text.setText(Lang.get("win.normal"));
		}

		back.setText(Lang.get("win.back"));
	}

	@Override
	public void update(Observable o, Object arg) {
		ViewUpdate update = updateMap.get((Update) arg);

		if (update != null) {
			update.execute(o);
		}
	}

	/**
	 * change Language of the game.
	 * 
	 * @author Tobias Groth
	 * 
	 */
	private class ChangeLanguageUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			setText();
		}
	}

	@Override
	public JPanel getPanel() {
		return this;
	}
}
