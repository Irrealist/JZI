package jzi.view;

import javax.swing.JOptionPane;

/**
 * GUI class that holds all warning windows.
 * 
 * @author Buddy Jonte
 */
public class Warnings {
    /**
     * Error/Warning window for invalid or not well-formed XML file.
     * 
     * @param window
     *            the main window of JZI
     */
    public static void xmlError() {
        JOptionPane.showMessageDialog(null,
                "Invalid or not well-formed XML file!", "XML Parsing Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
