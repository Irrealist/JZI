package jzi.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.event.DocumentListener;

public interface IWindow {

    void setMenu(Menu menu);

    Menu getMenu();

    JMenuItem getSaveItem();

    JMenuItem getSurrenderItem();

    JMenuItem getQuitItem();

    void setListeners(HashMap<Action, EventListener> map);

    HashMap<Action, EventListener> getListeners();

    ActionListener getActionListener(Action action);

    DocumentListener getDocumentListener(Action action);

    MouseListener getMouseListener(Action action);

    void update(Observable o, Object arg);

    JFrame getFrame();
}