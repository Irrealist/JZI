package jzi.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.DocumentListener;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
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
    private JMenu language;
    private JMenuItem loadItem;
    private JMenuItem saveItem;
    private JMenuItem german;
    private JMenuItem english;
    private JMenuItem rulesItem;
    private JMenuItem surrenderItem;
    private JMenuItem quitItem;
    private JMenuItem exitItem;
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
        setTitle("Java Zombie Invasion");
        setSize(new Dimension(1000, 800));
        setMinimumSize(new Dimension(640, 480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        menuBar = new JMenuBar();
        Iterator<String> words = Language.getCurrentLanguageWords(0).iterator();
        jziMenu = new JMenu(words.next());
        gameMenu = new JMenu(words.next());
        language = new JMenu(words.next());
        menuBar.add(jziMenu);
        menuBar.add(gameMenu);
        menuBar.add(language);

        loadItem = new JMenuItem(words.next());
        saveItem = new JMenuItem(words.next());
        rulesItem = new JMenuItem(words.next());
        surrenderItem = new JMenuItem(words.next());
        quitItem = new JMenuItem(words.next());
        exitItem = new JMenuItem(words.next());

        german = new JMenuItem(words.next());
        english = new JMenuItem(words.next());

        jziMenu.add(rulesItem);
        jziMenu.add(exitItem);

        gameMenu.add(loadItem);
        gameMenu.add(saveItem);
        gameMenu.add(surrenderItem);
        gameMenu.add(quitItem);

        language.add(english);
        language.add(german);

        saveItem.setEnabled(false);
        surrenderItem.setEnabled(false);
        quitItem.setEnabled(false);

        setJMenuBar(menuBar);
    }

    /**
     * set ActionListener for MenuItems.
     */
    private void setActionListener() {
        loadItem.addActionListener(getActionListener(Action.Load));
        english.addActionListener(getActionListener(Action.toEnglish));
        german.addActionListener(getActionListener(Action.toGerman));
        saveItem.addActionListener(getActionListener(Action.Save));
        rulesItem.addActionListener(getActionListener(Action.Rules));
        exitItem.addActionListener(getActionListener(Action.Exit));
        surrenderItem.addActionListener(getActionListener(Action.Surrender));
        quitItem.addActionListener(getActionListener(Action.Quit));
    }

    /* (non-Javadoc)
     * @see jzi.view.IWindow#setMenu(jzi.view.Menu)
     */
    public void setMenu(Menu menu) {
        this.currentMenu = menu;
        getContentPane().removeAll();
        getContentPane().add(currentMenu.getPanel());
        setVisible(true);
    }

    /* (non-Javadoc)
     * @see jzi.view.IWindow#getMenu()
     */
    public Menu getMenu() {
        return currentMenu;
    }

    /* (non-Javadoc)
     * @see jzi.view.IWindow#getSaveItem()
     */
    public JMenuItem getSaveItem() {
        return saveItem;
    }

    /* (non-Javadoc)
     * @see jzi.view.IWindow#getSurrenderItem()
     */
    public JMenuItem getSurrenderItem() {
        return surrenderItem;
    }

    /* (non-Javadoc)
     * @see jzi.view.IWindow#getQuitItem()
     */
    public JMenuItem getQuitItem() {
        return quitItem;
    }

    /* (non-Javadoc)
     * @see jzi.view.IWindow#setListeners(java.util.HashMap)
     */
    public void setListeners(HashMap<Action, EventListener> map) {
        actionMap = map;
        setActionListener();
    }

    /* (non-Javadoc)
     * @see jzi.view.IWindow#getListeners()
     */
    public HashMap<Action, EventListener> getListeners() {
        return actionMap;
    }

    /* (non-Javadoc)
     * @see jzi.view.IWindow#getActionListener(jzi.view.Action)
     */
    public ActionListener getActionListener(Action action) {
        return (ActionListener) actionMap.get(action);
    }

    /* (non-Javadoc)
     * @see jzi.view.IWindow#getDocumentListener(jzi.view.Action)
     */
    public DocumentListener getDocumentListener(Action action) {
        return (DocumentListener) actionMap.get(action);
    }

    /* (non-Javadoc)
     * @see jzi.view.IWindow#getMouseListener(jzi.view.Action)
     */
    public MouseListener getMouseListener(Action action) {
        return (MouseListener) actionMap.get(action);
    }

    /* (non-Javadoc)
     * @see jzi.view.IWindow#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable o, Object arg) {
        if (((Update) arg) == Update.ChangeLanguage) {
            changeLanguage();
        }
        currentMenu.update(o, arg);
    }

    /* (non-Javadoc)
     * @see jzi.view.IWindow#changeLanguage()
     */
    public final void changeLanguage() {
        Iterator<String> words = Language.getCurrentLanguageWords(0).iterator();
        jziMenu.setText(words.next());
        gameMenu.setText(words.next());
        language.setText(words.next());
        loadItem.setText(words.next());
        saveItem.setText(words.next());
        rulesItem.setText(words.next());
        surrenderItem.setText(words.next());
        quitItem.setText(words.next());
        exitItem.setText(words.next());
        german.setText(words.next());
        english.setText(words.next());
    }

    @Override
    public JFrame getFrame() {
        return this;
    }
}
