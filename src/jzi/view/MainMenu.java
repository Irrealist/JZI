package jzi.view;

import java.util.HashMap;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

/**
 * GUI class for the main menu.
 * 
 * @author Buddy Jonte
 */
public class MainMenu extends JPanel implements Menu {
	/**
     * 
     */
	private static final long serialVersionUID = 3646887163783890588L;
	private JButton start;
	private JButton coop;
	private JButton load;
	private JButton quit;
	private HashMap<Update, ViewUpdate> updateMap;

	public MainMenu(IWindow window) {
		updateMap = new HashMap<>();
		updateMap.put(Update.ChangeLanguage, new ChangeLanguageUpdate());

		setLayout(new MigLayout("align center center, wrap 1"));

		start = new JButton();
		start.addActionListener(window.getActionListener(Action.NewGame));
		add(start, "width :200:, height :40:");

		coop = new JButton();
		coop.addActionListener(window.getActionListener(Action.Coop));
		add(coop, "width :200:, height :40:");

		load = new JButton();
		load.addActionListener(window.getActionListener(Action.Load));
		add(load, "width :200:, height :40:");

		quit = new JButton();
		quit.addActionListener(window.getActionListener(Action.Exit));
		add(quit, "width :200:, height :40:");

		setText();
	}

	private void setText() {
		start.setText(Language.get("main.normal"));
		coop.setText(Language.get("main.coop"));
		load.setText(Language.get("main.load"));
		quit.setText(Language.get("main.quit"));
	}

	/**
	 * Return menu panel.
	 * 
	 * @return JPanel for this menu
	 */
	public JPanel getPanel() {
		return this;
	}

	public JButton getStartButton() {
		return start;
	}

	public JButton getExitButton() {
		return quit;
	}

	@Override
	public void update(Observable o, Object arg) {
		updateMap.get((Update) arg).execute(o);
	}

	private class ChangeLanguageUpdate implements ViewUpdate {
		@Override
		public void execute(Observable o) {
			setText();
		}
	}
}
