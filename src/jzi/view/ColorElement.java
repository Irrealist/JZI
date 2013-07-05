package jzi.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Tobias Groth
 *
 */
public class ColorElement extends JComboBox<Color> {
    /**
     * UID serilization.
     */
    private static final long serialVersionUID = 9081363761141579572L;

    /**
     * List of color.
     */

    private LinkedList<Color> colorList;

    /**
     * constructor to create list of color elements.
     *
     * @param list
     */

    public ColorElement(final LinkedList<Color> list) {
        super();

        colorList = list;

        setRenderer(new ColorRenderer());

        for (int i = 0; i < list.size(); i++) {
            addItem(list.get(i));
        }

        setPreferredSize(new Dimension(70, 20));
    }

    /**
     * Gets the current color.
     *
     * @return an integer specifying the currently selected list item.
     */
    public final Color getCurrentColor() {
        if (getSelectedIndex() >= colorList.size()
                || getSelectedIndex() == -1) {
            return null;
        }

        return colorList.get(getSelectedIndex());
    }

    /**
     * Removes color of the colorList.
     *
     * @param color
     */
    public final void removeColor(Color color) {
        colorList.remove(color);

        for (int i = 0; i < getItemCount(); i++) {
            if (getItemAt(i).equals(color)) {
                removeItemAt(i);
            }
        }
    }

    /**
     * Color Renderer of ColorElements.
     *
     * @author Tobias Groth
     *
     */
    private class ColorRenderer extends JLabel implements
            ListCellRenderer<Color> {
        /**
         * UID serilization.
         */
        private static final long serialVersionUID = 6285944714435596586L;

        /**
         * create new ColorRenderer.
         */
        public ColorRenderer() {
            super();
            setOpaque(true);
        }

        @Override
        public void setBackground(Color color) {
        }

        @Override
        public Component getListCellRendererComponent(
                JList<? extends Color> list, Color value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.setBackground(value);
            setMinimumSize(new Dimension(60, 20));
            setPreferredSize(new Dimension(60, 20));

            return this;
        }

    }
}
