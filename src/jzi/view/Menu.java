package jzi.view;

import java.util.Observable;
import javax.swing.JPanel;

/**
 * Interface for all menu classes.
 * 
 * @author Buddy
 */
public interface Menu {
    /**
     * The {@link Window} calls this method in case an update occurs.
     * 
     * @param o
     *            Calling Observable class
     * @param arg
     *            Parameter provided by the Observable
     */
    void update(Observable o, Object arg);

    /**
     * Get the menu's panel.
     * 
     * @return JPanel for this menu
     */
    JPanel getPanel();
}
