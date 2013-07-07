package jzi.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentListener;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.EventListener;

/**
 * Implements the main window of JZI.
 * 
 * @author Tobias
 */
public class Window extends JFrame implements Observer, IWindow {
	/**
	 * Generated UID.
	 */
	private static final long serialVersionUID = 6020138328544972728L;
	private JMenuBar menuBar;
	private JMenu jziMenu;
	private JMenu gameMenu;
	private JMenu languageMenu;
	private JMenuItem jziRules;
	private JMenuItem jziQuit;
	private JMenuItem gameLoad;
	private JMenuItem gameSave;
	private JMenuItem gameSurrender;
	private JMenuItem gameQuit;
	private HashMap<Action, EventListener> actionMap;
	/**
	 * Menu currently being displayed.
	 */
	private Menu currentMenu;

	/**
	 * Draw a window for the content.
	 * 
	 * @param actionMap
	 */
	public Window() {
		File[] languages;

		setTitle("Java Zombie Invasion");
		setSize(new Dimension(1000, 800));
		setMinimumSize(new Dimension(640, 480));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		menuBar = new JMenuBar();
		jziMenu = new JMenu();
		gameMenu = new JMenu();
		languageMenu = new JMenu();

		menuBar.add(jziMenu);
		menuBar.add(gameMenu);
		menuBar.add(languageMenu);

		jziRules = new JMenuItem();
		jziQuit = new JMenuItem();

		gameLoad = new JMenuItem();
		gameSave = new JMenuItem();
		gameSurrender = new JMenuItem();
		gameQuit = new JMenuItem();

		jziQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				Event.CTRL_MASK));

		jziMenu.add(jziRules);
		jziMenu.add(jziQuit);

		gameMenu.add(gameLoad);
		gameMenu.add(gameSave);
		gameMenu.add(gameSurrender);
		gameMenu.add(gameQuit);

		languages = new File("data/lang").listFiles();

		for (final File l : languages) {
			JMenuItem langItem = new JMenuItem();

			Language.load(l.getName());

			langItem.setText(Language.get("language.name"));
			langItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Language.load(l.getName());
					update(null, Update.ChangeLanguage);
				}
			});

			languageMenu.add(langItem);
		}

		Language.load("en.properties");

		gameSave.setEnabled(false);
		gameSurrender.setEnabled(false);
		gameQuit.setEnabled(false);

		setText();

		setJMenuBar(menuBar);
	}

	private void setText() {
		jziMenu.setText(Language.get("menu.jzi.title"));
		jziRules.setText(Language.get("menu.jzi.rules"));
		jziQuit.setText(Language.get("menu.jzi.quit"));

		gameMenu.setText(Language.get("menu.game.title"));
		gameLoad.setText(Language.get("menu.game.load"));
		gameSave.setText(Language.get("menu.game.save"));
		gameSurrender.setText(Language.get("menu.game.surrender"));
		gameQuit.setText(Language.get("menu.game.quit"));

		languageMenu.setText(Language.get("menu.lang.title"));
	}

	/**
	 * set ActionListener for MenuItems.
	 */
	private void setActionListener() {
		gameLoad.addActionListener(getActionListener(Action.Load));
		gameSave.addActionListener(getActionListener(Action.Save));
		jziRules.addActionListener(getActionListener(Action.Rules));
		jziQuit.addActionListener(getActionListener(Action.Exit));
		gameSurrender.addActionListener(getActionListener(Action.Surrender));
		gameQuit.addActionListener(getActionListener(Action.Quit));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jzi.view.IWindow#setMenu(jzi.view.Menu)
	 */
	public void setMenu(Menu menu) {
		this.currentMenu = menu;
		getContentPane().removeAll();
		getContentPane().add(currentMenu.getPanel());
		setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jzi.view.IWindow#getMenu()
	 */
	public Menu getMenu() {
		return currentMenu;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jzi.view.IWindow#getSaveItem()
	 */
	public JMenuItem getSaveItem() {
		return gameSave;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jzi.view.IWindow#getSurrenderItem()
	 */
	public JMenuItem getSurrenderItem() {
		return gameSurrender;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jzi.view.IWindow#getQuitItem()
	 */
	public JMenuItem getQuitItem() {
		return gameQuit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jzi.view.IWindow#setListeners(java.util.HashMap)
	 */
	public void setListeners(HashMap<Action, EventListener> map) {
		actionMap = map;
		setActionListener();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jzi.view.IWindow#getListeners()
	 */
	public HashMap<Action, EventListener> getListeners() {
		return actionMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jzi.view.IWindow#getActionListener(jzi.view.Action)
	 */
	public ActionListener getActionListener(Action action) {
		return (ActionListener) actionMap.get(action);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jzi.view.IWindow#getDocumentListener(jzi.view.Action)
	 */
	public DocumentListener getDocumentListener(Action action) {
		return (DocumentListener) actionMap.get(action);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jzi.view.IWindow#getMouseListener(jzi.view.Action)
	 */
	public MouseListener getMouseListener(Action action) {
		return (MouseListener) actionMap.get(action);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jzi.view.IWindow#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (((Update) arg).equals(Update.ChangeLanguage)) {
			setText();
		}

		currentMenu.update(o, arg);
	}

	@Override
	public JFrame getFrame() {
		return this;
	}
}
