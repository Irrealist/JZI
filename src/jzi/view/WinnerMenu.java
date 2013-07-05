package jzi.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jzi.model.GameCoop;
import jzi.model.IGame;
import jzi.model.IPlayer;

import net.miginfocom.swing.MigLayout;

/**
 *
 * create WinnerMenu for the game.
 *
 */
public class WinnerMenu extends JPanel implements Menu {
    /**
     * serial ID.
     */
    private static final long serialVersionUID = -2839932146818151358L;
    /**
     * Current window instance.
     */
    private IWindow window;
    /**
     * win text.
     */
    private JLabel text;
    /**
     * color of the winner.
     */
    private JLabel color;
    /**
     * additional win text.
     */
    private JLabel continueText;
    /**
     * Button back to MainMenu.
     */
    private JButton back;
    /**
     * Current game Instance.
     */
    private IGame game;
    /**
     * HashMap for updates.
     */
    private HashMap<Update, ViewUpdate> updateMap;

    /**
     * create a WinnerMenu for a specific type of game.
     *
     * @param windowHandle
     * @param game
     */
    public WinnerMenu(IWindow windowHandle, IGame game) {
        window = windowHandle;
        this.game = game;
        updateMap = new HashMap<>();
        updateMap.put(Update.ChangeLanguage, new ChangeLanguageUpdate());
        setLayout(new MigLayout("align center center", "[center]"));
        if (game instanceof GameCoop) {
            setCoopGameWinnerMenu();
        } else {
            setNormalWinnerMenu();
        }
    }

    /**
     * set a WinnerMenu for a normal Game.
     */
    private void setNormalWinnerMenu() {
        LinkedList<String> wordList = Language.getCurrentLanguageWords(7);
        if (game.getWinner() == null) {
            text = new JLabel(wordList.get(0));
        } else {
            text = new JLabel(wordList.get(1) + " "
                    + game.getWinner().getName());
            color = new JLabel();
            color.setPreferredSize(new Dimension(60, 20));
            color.setOpaque(true);
            color.setBackground(game.getWinner().getColor());
            continueText = new JLabel(wordList.get(2));
        }

        back = new JButton(wordList.get(3));

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                window.setMenu(new MainMenu(window));
            }
        });

        add(text);
        add(color);
        add(continueText, "wrap");
        add(back);
    }

    /**
     * set a WinnerMenu for a Coop Game.
     */
    private void setCoopGameWinnerMenu() {
        LinkedList<String> wordList = Language.getCurrentLanguageWords(7);

        text = new JLabel(wordList.get(4));
        add(text, "wrap");
        Iterator<IPlayer> playerIterator = game.getPlayers().iterator();
        while (playerIterator.hasNext()) {
            IPlayer player = playerIterator.next();
            JLabel playerName = new JLabel(player.getName());
            JLabel playerColor = new JLabel();
            playerColor.setPreferredSize(new Dimension(60, 20));
            playerColor.setOpaque(true);
            playerColor.setBackground(player.getColor());
            add(playerName);
            add(playerColor, "wrap");
        }
        back = new JButton(wordList.get(3));

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                window.setMenu(new MainMenu(window));
            }
        });
        add(back);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateMap.get((Update) arg).execute(o);
    }

    /**
     * change Language of the game.
     * @author Tobias Groth
     *
     */
    private class ChangeLanguageUpdate implements ViewUpdate {
        @Override
        public void execute(Observable o) {
            LinkedList<String> wordList = Language.getCurrentLanguageWords(7);
            if (game instanceof GameCoop) {
                text.setText(wordList.get(4));
                back.setText(wordList.get(3));
            } else {

                if (game.getWinner() == null) {
                    text.setText(wordList.get(0));
                } else {
                    text.setText(wordList.get(1) + " "
                            + game.getWinner().getName());
                    continueText.setText(wordList.get(2));
                }
                back.setText(wordList.get(3));
            }

        }
    }

    @Override
    public JPanel getPanel() {
        return this;
    }
}
