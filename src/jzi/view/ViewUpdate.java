package jzi.view;

import java.util.Observable;

/**
 * Interface for updates of the view.
 *
 * @author Buddy Jonte
 *
 */
interface ViewUpdate {
    /**
     * Method that is executed when the update occurs.
     *
     * @param o
     *            calling observable object
     */
    void execute(Observable o);
}
