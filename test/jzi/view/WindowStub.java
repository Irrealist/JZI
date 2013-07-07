package jzi.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.event.DocumentListener;

import jzi.model.GameStub;

public class WindowStub implements IWindow, Observer {
    private Menu menu;

    public WindowStub() {
        menu = new GameMenu(this, new GameStub());
    }
    
    @Override
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public Menu getMenu() {
        return menu;
    }

    @Override
    public JMenuItem getSaveItem() {
        return new JMenuItem();
    }

    @Override
    public JMenuItem getSurrenderItem() {
        return new JMenuItem();
    }

    @Override
    public JMenuItem getQuitItem() {
        return new JMenuItem();
    }

    @Override
    public void setListeners(HashMap<Action, EventListener> map) {
        // TODO Auto-generated method stub

    }

    @Override
    public HashMap<Action, EventListener> getListeners() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ActionListener getActionListener(Action action) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DocumentListener getDocumentListener(Action action) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MouseListener getMouseListener(Action action) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub

    }

    @Override
    public JFrame getFrame() {
        // TODO Auto-generated method stub
        return null;
    }

}
