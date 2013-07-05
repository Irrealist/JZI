package jzi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import net.miginfocom.swing.MigLayout;

/**
 *
 * create a LoseMenu for the game.
 *
 */
public class LoseMenu extends JPanel implements Menu {
    /**
     * serial ID.
     */
    private static final long serialVersionUID = 4430431651717009397L;
    /**
     * Current window instance.
     */
    private IWindow window;
    /**
     * Tile of the Menu.
     */
    private JLabel title;
    /**
     * Text.
     */
    private JLabel text;
    /**
     * Button back to MainMenu.
     */
    private JButton back;
    /**
     * HashMap for updates.
     */
    private HashMap<Update, ViewUpdate> updateMap;

    /**
     * create Lose Menu.
     * @param windowHandle
     */
    public LoseMenu(final IWindow windowHandle) {
        window = windowHandle;
        updateMap = new HashMap<>();
        updateMap.put(Update.ChangeLanguage, new ChangeLanguageUpdate());

        setLayout(new MigLayout("align center center, wrap 1", "[center]"));
        Iterator<String> words = Language.getCurrentLanguageWords(6).iterator();
        title = new JLabel(words.next());
        text = new JLabel(words.next());
        back = new JButton(words.next());

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                window.setMenu(new MainMenu(window));
            }
        });

        add(title);
        add(text);
        add(back);
    }

    @Override
    public void update(final Observable o, final Object arg) {
        if (((Update) arg) == Update.ChangeLanguage) {
            updateMap.get((Update) arg).execute(o);
        }
    }

    /**
     * change Language of the game.
     * @author Tobias Groth
     *
     */
    private class ChangeLanguageUpdate implements ViewUpdate {
        @Override
        public void execute(Observable o) {
            Iterator<String> words = Language.getCurrentLanguageWords(6)
                    .iterator();
            title.setText(words.next());
            text.setText(words.next());
            back.setText(words.next());
        }
    }

    @Override
    public final JPanel getPanel() {
        return this;
    }

}
