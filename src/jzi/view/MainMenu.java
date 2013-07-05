package jzi.view;

import java.util.HashMap;
import java.util.Iterator;
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
    private JButton exit;
    private HashMap<Update, ViewUpdate> updateMap;

    public MainMenu(IWindow window) {
        
        updateMap = new HashMap<>();
        updateMap.put(Update.ChangeLanguage, new ChangeLanguageUpdate());
        Iterator<String> words=Language.getCurrentLanguageWords(1).iterator();
        setLayout(new MigLayout("align center center, wrap 1"));

        start = new JButton(words.next());
        start.addActionListener(window.getActionListener(Action.NewGame));
        add(start, "width :200:, height :40:");

        coop = new JButton(words.next());
        coop.addActionListener(window.getActionListener(Action.Coop));
        add(coop, "width :200:, height :40:");

        load = new JButton(words.next());
        load.addActionListener(window.getActionListener(Action.Load));
        add(load, "width :200:, height :40:");

        exit = new JButton(words.next());
        exit.addActionListener(window.getActionListener(Action.Exit));
        add(exit, "width :200:, height :40:");
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
        return exit;
    }

    @Override
    public void update(Observable o, Object arg) {
        updateMap.get((Update) arg).execute(o);
    }
    
    private class ChangeLanguageUpdate implements ViewUpdate {
        @Override
        public void execute(Observable o) {
            Iterator<String> words=Language.getCurrentLanguageWords(1).iterator();
            start.setText(words.next());
            coop.setText(words.next());
            load.setText(words.next());
            exit.setText(words.next());
        }
    }
}
