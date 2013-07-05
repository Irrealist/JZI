package jzi.view;

import javax.swing.*;
import java.awt.*;

/**
 * GUI class for all popups.
 * 
 * @author Buddy Jonte
 */
public class Popups {

    /**
     * Popup for awarding the winner of the game.
     * 
     * @param name
     *            winner's name
     */
    public static void showWinner(String name) {
        JOptionPane.showMessageDialog(null, "Congratulations " + name
                + ", you have won! Have a fucking cookie!", "Le Winner",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Popup for showing the rules.
     * 
     * @param window
     */
    public static void rules(JFrame window, String gameRules) {
        JDialog dialog = new JDialog(window);
        
        JTextArea rules = new JTextArea(gameRules);
        rules.setEditable(false);
        JScrollPane scroll = new JScrollPane(rules);

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(480, 570));

        rules.setLineWrap(true);
        rules.setWrapStyleWord(true);


        dialog.setSize(new Dimension(500, 600));
        dialog.getContentPane().add(scroll);
        dialog.getContentPane().setVisible(true);
        dialog.setVisible(true);
    }
}