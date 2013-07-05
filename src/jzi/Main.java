package jzi;

import jzi.controller.Controller;
import jzi.view.Window;

/**
 * Main class of JZI.
 * 
 * @author Buddy Jonte
 * 
 */
public class Main {
    /**
     * Main method. It starts the {@link Controller}.
     * 
     * @param args
     *            Command line arguments
     */
    public static void main(final String[] args) {
        new Controller(new Window());
    }
}
